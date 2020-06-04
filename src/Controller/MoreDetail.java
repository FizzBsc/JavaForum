package Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class MoreDetail implements Initializable {
    @FXML public Label title;
    @FXML public Button backBut;
    @FXML public TableColumn column1;
    @FXML public TableColumn column2;
    @FXML public Label description;
    @FXML public Label info1;
    @FXML public Label postID;
    @FXML public Button closeBut;
    @FXML public Button deleteBut;
    @FXML public Button saveBut;
    @FXML public Button upload;
    @FXML public Label info2;
    @FXML public Label capacity;
    @FXML public Label atendeeCount;
    @FXML public ImageView image;
    @FXML private TableView<Reply> tableView;


    ObservableList<Reply> reply = FXCollections.observableArrayList(Databases.reply);
    private Post selectedPost;

    public void initData(Post post) {
        selectedPost = post;
        title.setText(selectedPost.getTitle());
        description.setText("Description: " + selectedPost.getDescription());
        postID.setText("Post ID: " + selectedPost.getPostID());
        closeBut.setText("close");
        upload.setText("Upload Image");
        for(int i = 0; i < Databases.pics.size(); i++){
            if (selectedPost.getPostID().equals(Databases.pics.get(i).getPostID())){
                Image pic = null;
                try {
                    pic = new Image(new FileInputStream(Databases.pics.get(i).getFile()));
                } catch (FileNotFoundException e) {
                    pic = new Image(String.valueOf(new File(String.valueOf(Databases.pics.get(i).getFile()))));
                }
                image.setImage(pic);
            }
        }
        if (selectedPost.getStatus() == false){
            closeBut.setDisable(true);
            closeBut.setText("Post Closed");
        }

        if (selectedPost instanceof Sale){
            info1.setText("Asking Price: $" + Double.toString(((Sale) selectedPost).getAskPrice()));
            info2.setText("Highest Offer: $" +Double.toString(((Sale) selectedPost).getHighOffer()));
            capacity.setVisible(false);
            atendeeCount.setVisible(false);
            column1.setText("Name");
            column2.setText("Offer");

        } else if (selectedPost instanceof Job){
            info1.setText("Asking Wage: $" + Double.toString(((Job) selectedPost).getpPrice()));
            info2.setText("Lowest Offer: $" +Double.toString(((Job) selectedPost).getLowOffer()));
            capacity.setVisible(false);
            atendeeCount.setVisible(false);
            column1.setText("Name");
            column2.setText("Offer");

        }else if (selectedPost instanceof Event){
            capacity.setText("Capacity: " + Integer.toString(((Event) selectedPost).getCapacity()));
            atendeeCount.setText("Number of Attendees: " + Integer.toString(((Event) selectedPost).getAttendeeCount()));
            info1.setText("Venue: " + ((Event) selectedPost).getVenue());
            info2.setText("Date: " + ((Event) selectedPost).getDate());
            column1.setText("Attendees");
            column2.setText(" ");
        }
        column1.setCellValueFactory(new PropertyValueFactory<Reply, String>("responderID"));
        column2.setCellValueFactory(new PropertyValueFactory<Reply, String>("value"));

        FilteredList<Reply> filteredData = new FilteredList<>(reply, p -> true);

        filteredData.setPredicate(reply -> {
            if (reply.getPostID().equals(selectedPost.getPostID())) {
                return true;
            }
            return false;
        });

        SortedList<Reply> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(tableView.comparatorProperty());

        tableView.setItems(sortedData);


    }



    @FXML
    public void backToMain(ActionEvent actionEvent) throws Exception {

        Stage stage = (Stage) backBut.getScene().getWindow();
        MainMenu menu = new MainMenu();
        menu.startMenu();
        stage.close();


    }
    @FXML
    public void closePostHandler(ActionEvent actionEvent) throws Exception {

        selectedPost.setStatus(false);
        Stage stage = (Stage) closeBut.getScene().getWindow();
        MainMenu menu = new MainMenu();
        menu.startMenu();
        stage.close();


    }

    @FXML
    public void uploadClickHandler(ActionEvent actionEvent) throws Exception {
        Stage primaryStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("src"));
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        System.out.println(selectedFile);
        Image pic = new Image(new FileInputStream(selectedFile));


        for (int i = 0; i<Databases.pics.size(); i++){
            if (selectedPost.getPostID().equals(Databases.pics.get(i).getPostID())){
                System.out.println("look here");

                Databases.pics.get(i).setFile(selectedFile);
            }
        }
        image.setImage(pic);


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
