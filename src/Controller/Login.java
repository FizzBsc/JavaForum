package Controller;

import Model.Databases;
import javafx.application.Platform;
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

public class Login {
    public static String studentID;
    @FXML public Button loginButton;
    @FXML public Button quitButton;
    @FXML private Label iDIndicator;
    @FXML private TextField inputSID;


    @FXML private void initialize()
    {
        iDIndicator.setText(" ");
    }
    public static void startLogin() throws Exception {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(Login.class.getResource("/View/Login.fxml"));
            Scene scene = new Scene(root, 365, 181);
            stage.setTitle("Student Syslink");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            System.out.println("Fail");
        }
    }

    @FXML public void LoginClickHandler(ActionEvent actionEvent) throws Exception
    {
        iDIndicator.setText(" ");
        for(int i = 0; i < Databases.SID.size(); i++)
        {
            Stage stage = (Stage) loginButton.getScene().getWindow();
            studentID = Databases.SID.get(i);
            if (inputSID.getText().equals(studentID))
            {
                MainMenu menu = new MainMenu();
                menu.startMenu();
                stage.close();
                break;

            } else if (inputSID.getText() != studentID)
                {
                    iDIndicator.setText("ID Not Found");
                }
        }


    }
    @FXML
    public void quitClickHandler(ActionEvent actionEvent) throws Exception
    {
        Platform.exit();
    }

}
