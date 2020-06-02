package Controller;

import Model.*;
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
import java.util.ResourceBundle;

public class PostReply implements Initializable {

    private Post selectedPost;
    private double value;

    @FXML public Label postIDLbl;
    @FXML public Label valueLbl;
    @FXML public TextField valueField;
    @FXML public Button submitBut;
    @FXML public Button cancelBut;


    public void initData(Post post) {
        selectedPost = post;
        if (selectedPost instanceof Sale){
            postIDLbl.setText(selectedPost.getPostID());
            valueLbl.setText("enter price:");
            submitBut.setText("Submit");
            cancelBut.setText("Cancel");
        } else if (selectedPost instanceof Job){
            postIDLbl.setText(selectedPost.getPostID());
            valueLbl.setText("enter proposed wage:");
            submitBut.setText("Submit");
            cancelBut.setText("Cancel");
        }else if (selectedPost instanceof Event){
            postIDLbl.setText(selectedPost.getPostID());
            valueLbl.setText("Will you be attending?");
            valueField.setVisible(false);
            submitBut.setText("Yes");
            cancelBut.setText("No");
        }
    }

    @FXML
    public void submitHandler(ActionEvent actionEvent) throws Exception{

        if (selectedPost instanceof Sale || selectedPost instanceof Job){
            value = Double.parseDouble(valueField.getText());
            //need to get exception here
        } else if (selectedPost instanceof Event){
            value = 1;
        }

        Databases.reply.add(new Reply (selectedPost.getPostID(), value, Login.studentID));

        Stage stage = (Stage) submitBut.getScene().getWindow();

        MainMenu menu = new MainMenu();
        menu.startMenu();
        stage.close();

    }
    @FXML
    public void cancelForm(ActionEvent actionEvent) throws Exception {

        Stage stage = (Stage) cancelBut.getScene().getWindow();
        MainMenu menu = new MainMenu();
        menu.startMenu();
        stage.close();


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
