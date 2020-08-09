package Controller;

import Model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PostListViewCell extends ListCell<Post> {
    @FXML Label label1;
    @FXML Label label2;
    @FXML Label label3;
    @FXML Label label4;
    @FXML Label label5;
    @FXML Label label6;
    @FXML Button replyButton;
    @FXML Button moreButton;
    @FXML GridPane gridPane;
    @FXML Label aCount;
    @FXML Label capacity;
    @FXML ImageView newPhoto;

    private FXMLLoader mLLoader;

    @Override
    protected void updateItem(Post post, boolean empty) {
        super.updateItem(post, empty);
        if (empty || post == null) {

            setText(null);
            setGraphic(null);

        } else {

            loadDetail(post);

            label1.setText("Post ID: " + post.getPostID());
            label2.setText("Title: " +post.getTitle());
            label3.setText("Description: " + post.getDescription());
            label4.setText("Creator: " + post.getCreatorID());

            if (post.getCreatorID().equals(Login.studentID) || post.getStatus() == false) {
                replyButton.setVisible(false);
            } else {

                replyButton.setOnAction(e -> {
                    try {
                        newReplyClickHandler(post.getPostID());
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                });
            }

            if (post.getCreatorID().equals(Login.studentID)) {
                moreButton.setOnAction(e -> {
                    try {
                        moreDeetClickHandler(post.getPostID());
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                });
            }else {
                moreButton.setVisible(false);
            }

            Image image = null;
            for(int i = 0; i < Databases.pics.size(); i++){
                if (post.getPostID().equals(Databases.pics.get(i).getPostID())){
                    System.out.println("found");
                    try {
                        image = new Image(new FileInputStream(Databases.pics.get(i).getFile()));
                    } catch (FileNotFoundException e) {
                        image = new Image(String.valueOf(new File(String.valueOf(Databases.pics.get(i).getFile()))));
                    }
                }else{
                    image = new Image("images/Default1.jpg");
                }
                newPhoto.setImage(image);
            }

            setText(null);
            setGraphic(gridPane);
        }

    }

    private void loadDetail(Post post){
        if (mLLoader == null) {
            mLLoader = new FXMLLoader(getClass().getResource("/View/ListCell.fxml"));
            mLLoader.setController(this);

            try {
                mLLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (post instanceof Event) {
            gridPane.setStyle("-fx-background-color: #C0FFEE");
            label5.setText("Date: " + ((Event) post).getDate());
            label6.setText("Venue: " + ((Event) post).getVenue());
            aCount.setText("Attendee Count: " + ((Event) post).getAttendeeCount());
            capacity.setText("Capacity: " +((Event) post).getCapacity());

        }
        else if (post instanceof Sale){
            gridPane.setStyle("-fx-background-color: #fbfbaf");
            label5.setText("Price: $" + ((Sale) post).getAskPrice());
            label6.setText("Highest offer: $" + ((Sale) post).getHighOffer());
            aCount.setVisible(false);
            capacity.setVisible(false);
        }
        else if (post instanceof Job){
            gridPane.setStyle("-fx-background-color: #fedbea");
            label5.setText("Offer: $" + ((Job) post).getpPrice());
            label6.setText("Minimum offer: $" + ((Job) post).getLowOffer());
            aCount.setVisible(false);
            capacity.setVisible(false);
        }
    }

    private void moreDeetClickHandler(String cellID) throws Exception {
        Stage stage = (Stage) moreButton.getScene().getWindow();
        for (int i = 0; i < Databases.post.size(); i++)
            if (cellID.equals(Databases.post.get(i).getPostID())){
                Post post = Databases.post.get(i);
                if (post instanceof Model.Sale) {
                    Model.Sale sale = (Model.Sale) post;
                    if (sale.handleReply(cellID)==true) {
                        startMoreDeet(post);
                        stage.close();
                    }
                }
                else if (post instanceof Model.Event) {
                    Model.Event event = (Model.Event) post;
                    if(event.handleReply(cellID) == true){
                        startMoreDeet(post);
                        stage.close();
                    }
                }
                else if (post instanceof Model.Job) {
                    Model.Job job = (Model.Job) post;
                    if (job.handleReply(cellID) == true){
                        startMoreDeet(post);
                        stage.close();
                    }
                }
            }


    }

    private void startMoreDeet(Post post) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View/More_Details.fxml"));
            Parent view = loader.load();

            Scene scene = new Scene(view);

            MoreDetail md = loader.getController();
            md.initData(post);

            Stage stage = new Stage();
            stage.setTitle("Reply");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            System.out.println("Fail");
        }
    }

    @FXML
    public void newReplyClickHandler(String cellID) throws Exception {
        Stage stage = (Stage) replyButton.getScene().getWindow();
        for (int i = 0; i < Databases.post.size(); i++)
            if (cellID.equals(Databases.post.get(i).getPostID())){
                Post post = Databases.post.get(i);
                if (post instanceof Model.Sale) {
                    Model.Sale sale = (Model.Sale) post;
                    if (sale.handleReply(cellID)==true) {
                        startReply(post);
                        stage.close();
                    }
                }
                else if (post instanceof Model.Event) {
                    Model.Event event = (Model.Event) post;
                        if (event.handleReply(cellID) == true) {
                                    startReply(post);
                                    stage.close();
                        }
                }
                else if (post instanceof Model.Job) {
                    Model.Job job = (Model.Job) post;
                    if (job.handleReply(cellID) == true){
                        startReply(post);
                        stage.close();
                    }
                }
            }


    }
    public void startReply(Post post) throws Exception {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View/Reply.fxml"));
            Parent view = loader.load();

            Scene scene = new Scene(view);

            PostReply pr = loader.getController();
            pr.initData(post);

            Stage stage = new Stage();
            stage.setTitle("Reply");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            System.out.println("Fail");
        }

    }


}
