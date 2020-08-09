package Controller;

import Model.Databases;
import Model.Event;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.InputMismatchException;
import java.util.ResourceBundle;

import static Model.Databases.insertTable;

public class NewJob implements Initializable {

    @FXML public TextField proField;
    @FXML public TextField descField;
    @FXML public Label title;
    @FXML public Label description;
    @FXML public Label propPrice;
    @FXML public Button submitBut;
    @FXML public Button cancelBut;
    @FXML public TextField titleField;
    @FXML public Label welcomeID;

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

    @Override
    public void initialize(URL u, ResourceBundle rb) {
        welcomeID.setText("New Job Post");
    }

    public void SubmitForm(ActionEvent actionEvent) throws Exception {
        Stage stage = (Stage) submitBut.getScene().getWindow();
        String title;
        String description;
        double propPrice;
        boolean pass;
        try {
            do {
                title = titleField.getText();
                if (titleField.getText().isBlank()) {
                    throw new EmptyInputException("");
                }
                description = descField.getText();
                if (descField.getText().isBlank()) {
                    throw new EmptyInputException("");
                }
                propPrice = Double.parseDouble(proField.getText());
                if (proField.getText().isBlank()) {
                    throw new EmptyInputException("");
                }
                pass = true;
            }while (pass == false);

            Model.Job j1 = new Model.Job(null, title, description, Login.studentID, propPrice, 0, true);
            Databases.post.add(j1);
            insertTable(j1);
            Databases.pics.add(new Model.Photo(j1.postID));
            MainMenu menu = new MainMenu();
            menu.startMenu();
            stage.close();
        }catch (EmptyInputException e) {
            AlertBox.alert("Empty Field Error", "Entry cannot be empty", "Please do not leave fields blank");
            pass = false;
        }
        catch (InputMismatchException e) {
            AlertBox.alert("Wrong input Error", "Wrong input for proposed price", "Enter a value for proposed price");
            pass = false;
        }
        catch (NumberFormatException e){
            AlertBox.alert("Wrong input Error", "Wrong input for proposed price", "Enter a value for proposed price");
            pass = false;
        }

    }
    public void CancelForm(ActionEvent actionEvent) throws Exception {

        Stage stage = (Stage) cancelBut.getScene().getWindow();
        MainMenu menu = new MainMenu();
        menu.startMenu();
        stage.close();

    }
}
