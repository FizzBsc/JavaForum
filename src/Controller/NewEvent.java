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

public class NewEvent {



    @FXML public Label welcomeID;
    @FXML public Label title;
    @FXML public Label description;
    @FXML public TextField titleField;
    @FXML public TextField descField;
    @FXML public TextField capField;
    @FXML public TextField dateField;
    @FXML public TextField venField;
    @FXML public Button submitBut;
    @FXML protected void initialize() throws Exception {
        welcomeID.setText("Welcome " + Login.studentID);
    }


    public static void newPost() throws Exception {
        try {

            Stage stage = new Stage();
            Parent root = FXMLLoader.load(MainMenu.class.getResource("/View/New_Event.fxml"));
            Scene scene = new Scene(root, 321, 354);
            stage.setTitle("New Event Post");
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
        int capacity = Integer.parseInt(capField.getText());
        String date = dateField.getText();
        String venue = venField.getText();

        Model.Event e1 = new Event(null, title, description, Login.studentID, venue, date, capacity, 0);
        Databases.post.add(e1);
        MainMenu.startMenu();
        stage.close();

        for (int i = 0; i<Databases.post.size(); i++){
            System.out.println(Databases.post.get(i).postID + Databases.post.get(i).getTitle());
        }
    }
}
