package Controller;

import Model.Databases;
import Model.Event;
import Model.Post;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.*;

import java.io.IOException;

public class MainMenu {

    @FXML public Button logoutButton;
    @FXML public Label welcomeID;
    @FXML public static ScrollPane scrollPane1;
    @FXML public Button newEvent;
    @FXML private TableView<Post> tableView;
    @FXML private TableColumn<Post, String> postIDColumn;
    @FXML private TableColumn<Post, String> titleColumn;
    @FXML private TableColumn<Post, String> descriptionColumn;
    @FXML private TableColumn<Post, String> creatorColumn;
    @FXML private TableColumn<Post, Void> replyColumn;
    @FXML private void initialize()
    {
        welcomeID.setText("Welcome " + Login.studentID);
        postIDColumn.setCellValueFactory(new PropertyValueFactory<Post, String>("postID"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Post, String>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Post, String>("description"));
        creatorColumn.setCellValueFactory(new PropertyValueFactory<Post, String>("creatorID"));

        tableView.setItems(getPost());

    }

    public ObservableList<Post> getPost()
    {
        ObservableList<Post> post = FXCollections.observableArrayList(Databases.post);
        return post;
    }

    public static void startMenu() throws Exception {
        try {

            Stage stage = new Stage();
            Parent root = FXMLLoader.load(MainMenu.class.getResource("/View/Main_Menu.fxml"));
            Scene scene = new Scene(root, 600, 482);
            stage.setTitle("Menu");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();


        } catch (IOException e) {
            System.out.println("Fail");
        }
    }
    private void addReplyButton(){

    }

    public void logOut() throws Exception {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        try {
           Login.startLogin();
           stage.close();
       }
       catch (IOException e) {
           System.out.println("Fail");
       }
    }
    @FXML
    public void newPostClickHandler(ActionEvent actionEvent) throws Exception{
        Stage stage = (Stage) newEvent.getScene().getWindow();
        NewEvent.newPost();
        stage.close();
    }
    @FXML
    public void logoutClickHandler(ActionEvent actionEvent) throws Exception{
        logOut();
    }
}
