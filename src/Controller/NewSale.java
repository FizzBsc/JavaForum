package Controller;

import Model.Databases;
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
import java.util.InputMismatchException;

import static Model.Databases.insertTable;

public class NewSale {
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
    static int sharedCounter = 0;




    @FXML
    protected void initialize() throws Exception {
        welcomeID.setText("New Sale Post");
    }


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


    public void SubmitForm(ActionEvent actionEvent) throws Exception {
        Stage stage = (Stage) submitBut.getScene().getWindow();
        String title = null;
        String description = null;
        double askPrice = 0;
        double minRaise = 0;

        boolean pass = false;
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
            System.out.println(s1.postID);//delete before submit
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
