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
        String title = titleField.getText();
        String description = descField.getText();
        int askPrice = Integer.parseInt(askPriField.getText());
        int minRaise = Integer.parseInt(minRaiField.getText());



        Model.Sale s1 = new Model.Sale(null, title, description, Login.studentID,askPrice,0,minRaise);
        Databases.post.add(s1);
        System.out.println(s1.postID);//delete before submit
        insertTable(s1);
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
