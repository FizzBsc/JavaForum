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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.zip.DataFormatException;

import static Model.Databases.insertTable;

public class NewEvent implements Initializable {


    @FXML public Label welcomeID;
    @FXML public Label title;
    @FXML public Label description;
    @FXML public TextField titleField;
    @FXML public TextField descField;
    @FXML public TextField capField;
    @FXML public DatePicker dateField;
    @FXML public TextField venField;
    @FXML public Button submitBut;
    @FXML public Button cancelBut;

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

    @Override
    public void initialize(URL u, ResourceBundle rb){
        welcomeID.setText("New Event Post");
        dateField.getEditor().setDisable(true);
    }

    public void SubmitForm(ActionEvent actionEvent) throws Exception {

        Stage stage = (Stage) submitBut.getScene().getWindow();
        String title;
        String description;
        int capacity;
        String date = "";
        String venue;
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

                capacity = Integer.parseInt(capField.getText());
                if (capField.getText().isBlank()) {
                    throw new EmptyInputException("");
                } else if (capacity == 0) {
                    throw new InputMismatchException("");
                }

                venue = venField.getText();
                if (venField.getText().isBlank()) {
                    throw new EmptyInputException("");
                }

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate d =dateField.getValue();
                if (d != null) {
                    date = formatter.format(d);
                }else if (d == null) {
                    throw new WrongDateException("");
                }

                pass = true;

            } while (pass = false);

            Model.Event e1 = new Event(null, title, description, Login.studentID, venue, date, capacity, 0, true);
            Databases.post.add(e1);
            insertTable(e1);
            Databases.pics.add(new Model.Photo(e1.postID));

            MainMenu menu = new MainMenu();
            menu.startMenu();
            stage.close();
        } catch (EmptyInputException e) {
            AlertBox.alert("Empty Field Error", "Entry cannot be empty", "Please do not leave fields blank");
            pass = false;
        }
        catch (InputMismatchException e) {
            AlertBox.alert("Wrong input Error", "Wrong input for capacity", "capacity need to be 1 or more");
            pass = false;
        }
        catch (NumberFormatException e){
            AlertBox.alert("Wrong input Error", "Wrong input for capacity", "capacity need to be 1 or more");
            pass = false;
        }catch (WrongDateException e){
            AlertBox.alert("Wrong Date Error", "Wrong input for Date", "Please pick a date");
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
