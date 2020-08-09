package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class DevInfo {

    @FXML public Label name;
    @FXML public Label studentNo;
    @FXML public Label email;
    @FXML public Button closeBut;
    @FXML public Label devInfo;

    public static void startDevInfo() throws Exception {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(DevInfo.class.getResource("/View/Dev_info.fxml"));
            Scene scene = new Scene(root, 265, 254);
            stage.setTitle("Developer Information");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            System.out.println("Fail");
        }
    }

    @FXML
    private void initialize()
    {
        devInfo.setText("Developer Information");
        name.setText("Name: Abdul Hafiz Abdul Halid");
        studentNo.setText("Student Number: S3623311");
        email.setText("S3623311@student.rmit.edu.au");
        closeBut.setText("Close");
    }

    @FXML
    public void closeClickHandler(ActionEvent actionEvent) throws Exception
    {
        Stage stage = (Stage) closeBut.getScene().getWindow();
        stage.close();
    }
}
