package Controller;

import Model.Databases;
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

public class NewSale implements Initializable {

    @FXML public Label welcomeID;
    @FXML public TextField titleField;
    @FXML public TextField askPriField;
    @FXML public TextField descField;
    @FXML public Label title;
    @FXML public TextField minRaiField;
    @FXML public Label description;
    @FXML public Label askPrice;
    @FXML public Label minRaise;
    @FXML public Button submitBut;
    @FXML public Button cancelBut;

    public static void newPost() throws Exception {
        try {

            Stage stage = new Stage();
            Parent root = FXMLLoader.load(MainMenu.class.getResource("/View/New_Sale.fxml"));
            Scene scene = new Scene(root, 321, 233);
            stage.setTitle("New Sale Post");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();


        } catch (IOException e) {
            System.out.println("Fail");
        }
    }

    @Override
    public void initialize(URL u, ResourceBundle rb){
        welcomeID.setText("New Sale Post");
    }

    @FXML
    public void SubmitForm(ActionEvent actionEvent) throws Exception {
        Stage stage = (Stage) submitBut.getScene().getWindow();
        String title;
        String description;
        double askPrice;
        double minRaise;
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
                askPrice = Double.parseDouble(askPriField.getText());
                if (askPriField.getText().isBlank()) {
                    throw new EmptyInputException("");
                }
                minRaise = Double.parseDouble(minRaiField.getText());
                if (minRaiField.getText().isBlank()) {
                    throw new EmptyInputException("");
                }
                pass = true;
            }while (pass == false);

            Model.Sale s1 = new Model.Sale(null, title, description, Login.studentID, askPrice, 0, minRaise, true);
            Databases.post.add(s1);
            insertTable(s1);
            Databases.pics.add(new Model.Photo(s1.postID));
            MainMenu menu = new MainMenu();
            menu.startMenu();
            stage.close();
        }catch (EmptyInputException e) {
            AlertBox.alert("Empty Field Error", "Entry cannot be empty", "Please do not leave fields blank");
            pass = false;
        }
        catch (InputMismatchException e) {
            AlertBox.alert("Wrong input Error", "Wrong input for asking price or minimum raise", "Please enter a valid value");
            pass = false;
        }
        catch (NumberFormatException e){
            AlertBox.alert("Wrong input Error","Wrong input for asking price or minimum raise", "Please enter a valid value");
            pass = false;
        }

    }

    @FXML
    public void CancelForm(ActionEvent actionEvent) throws Exception {

        Stage stage = (Stage) cancelBut.getScene().getWindow();
        MainMenu menu = new MainMenu();
        menu.startMenu();
        stage.close();


    }
}
