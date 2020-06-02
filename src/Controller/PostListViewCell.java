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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PostListViewCell extends ListCell<Post> {
    @FXML Label label1;
    @FXML Label label2;
    @FXML Label label3;
    @FXML Label label4;
    @FXML Button replyButton;
    @FXML Button moreButton;
    @FXML private GridPane gridPane;
    private FXMLLoader mLLoader;

    @Override
    protected void updateItem(Post post, boolean empty) {
        super.updateItem(post, empty);
        if (empty || post == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/View/ListCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if (post instanceof Event)
                gridPane.setStyle("-fx-background-color: #C0FFEE");
            else if (post instanceof Sale)
                gridPane.setStyle("-fx-background-color: #fbfbaf");
            else if (post instanceof Job)
                gridPane.setStyle("-fx-background-color: #fedbea");

            label1.setText("Post ID: " + post.getPostID());
            label2.setText("Title: " +post.getTitle());
            label3.setText("Description: " + post.getDescription());
            label4.setText("Creator: " + post.getCreatorID());
            if (post.getCreatorID().equals(Login.studentID) || post.getStatus() == false)
                replyButton.setVisible(false);
            else
                replyButton.setOnAction(e -> {
                    try {
                        newReplyClickHandler(post.getPostID());
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                });

            if (post.getCreatorID().equals(Login.studentID))
                moreButton.setOnAction(e -> {
                    try {
                        moreDeetClickHandler(post.getPostID());
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                });
            else
                moreButton.setVisible(false);


            setText(null);
            setGraphic(gridPane);
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
                    if(event.handleReply(cellID) == true){
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
