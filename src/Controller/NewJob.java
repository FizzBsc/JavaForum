package Controller;

import Model.Databases;
import Model.Event;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class NewJob {

    @FXML public TextField proField;
    @FXML public TextField descField;
    @FXML public Label title;
    @FXML public Label description;
    @FXML public Label propPrice;
    @FXML public Button submitBut;
    @FXML public Button cancelBut;
    @FXML public TextField titleField;
    @FXML public Label welcomeID;


    @FXML
    protected void initialize() throws Exception {
        welcomeID.setText("New Job Post");
    }


    public static void newPost() throws Exception {
        try {

            Stage stage = new Stage();
            Parent root = FXMLLoader.load(MainMenu.class.getResource("/View/New_Job.fxml"));
            Scene scene = new Scene(root, 321, 233);
            stage.setTitle("New Job Post");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();


        } catch (IOException e) {
            System.out.println("Fail");
        }
    }


    public void SubmitForm(ActionEvent actionEvent) throws Exception {
        Stage stage = (Stage) submitBut.getScene().getWindow();
        String title = titleField.getText();
        String description = descField.getText();
        int propPrice = Integer.parseInt(proField.getText());


        Model.Job j1 = new Model.Job(null, title, description, Login.studentID,propPrice,0);
        Databases.post.add(j1);
        MainMenu menu = new MainMenu();
        menu.startMenu();
        stage.close();

        for (int i = 0; i<Databases.post.size(); i++){
            System.out.println(Databases.post.get(i).postID + Databases.post.get(i).getTitle());
        }
    }
    public void CancelForm(ActionEvent actionEvent) throws Exception {

        Stage stage = (Stage) cancelBut.getScene().getWindow();
        MainMenu menu = new MainMenu();
        menu.startMenu();
        stage.close();


    }
}
