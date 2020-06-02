package Controller;

import Model.Event;
import Model.Job;
import Model.Post;
import Model.Sale;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

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

    private Post selectedPost;

    public void initData(Post post) {
        selectedPost = post;
        title.setText(selectedPost.getTitle());
        description.setText("Description: " + selectedPost.getDescription());
        postID.setText("Post ID: " + selectedPost.getPostID());
        closeBut.setText("close");

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}