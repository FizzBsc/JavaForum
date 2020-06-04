package Controller;

import Model.Databases;
import Model.Post;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static Model.Databases.checkSaleTable;

public class MainMenu implements Initializable {

    @FXML public Button logoutButton;
    @FXML public Label welcomeID;
    @FXML public Button newEvent;
    @FXML public Button newSale;
    @FXML public Button newJob;
    @FXML public MenuItem save;
    @FXML public MenuItem quit;
    @FXML public MenuItem About;
    @FXML public MenuItem load;

    @FXML private ListView<Post> listView;

    public ObservableList<Post> getPost()//look here first if not loading
    {
        ObservableList<Post> post = FXCollections.observableArrayList(Databases.post);

        return post;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        welcomeID.setText("Welcome " + Login.studentID);
        listView.setItems(getPost());
        listView.setCellFactory(postListView -> new PostListViewCell());

    }

    public void startMenu() throws Exception {

        try {


            Stage stage = new Stage();
            Parent root = FXMLLoader.load(MainMenu.class.getResource("/View/Main_Menu.fxml"));
            Scene scene = new Scene(root, 663, 520);
            stage.setTitle("Menu");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();


        } catch (IOException e) {
            System.out.println("Fail");
        }
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
    public void aboutClickHandler(ActionEvent actionEvent) throws Exception {
        DevInfo.startDevInfo();
    }

    @FXML
    public void newSaveClickHandler(ActionEvent actionEvent) throws Exception{
        Stage primaryStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("src"));
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        Databases.writeToFile(selectedFile);
    }@FXML
    public void newLoadClickHandler(ActionEvent actionEvent) throws Exception{

        Stage primaryStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("src"));
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        Databases.readFromFile(selectedFile);
    }
    @FXML
    public void newEventClickHandler(ActionEvent actionEvent) throws Exception{
        Stage stage = (Stage) newEvent.getScene().getWindow();
        NewEvent.newPost();
        stage.close();
    }
    @FXML
    public void newSaleClickHandler(ActionEvent actionEvent) throws Exception{
        Stage stage = (Stage) newSale.getScene().getWindow();
        NewSale.newPost();
        stage.close();
    }
    @FXML
    public void newJobClickHandler(ActionEvent actionEvent) throws Exception{
        Stage stage = (Stage) newJob.getScene().getWindow();
        NewJob.newPost();
        stage.close();
    }
    @FXML
    public void logoutClickHandler(ActionEvent actionEvent) throws Exception{
        logOut();
    }
    @FXML
    public void quitClickHandler(ActionEvent actionEvent) throws Exception
    {
        Platform.exit();
    }
}
