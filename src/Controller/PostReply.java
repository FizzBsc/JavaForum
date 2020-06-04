package Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
        Databases db = new Databases();
        boolean k = false;
        if (selectedPost instanceof Sale){
            System.out.println("1");
            while (k == false) {
                System.out.println(2);
                value = Double.parseDouble(valueField.getText());
                k = db.checkSaleHOffer(selectedPost,value);
                if (k == true) {
                    System.out.println("this will change array");
                } else {
                    System.out.println("this wont");
                    k = true;
                }
            }
        } else if (selectedPost instanceof Job){
            while (k == false) {
                System.out.println(3);
                value = Double.parseDouble(valueField.getText());
                k = db.checkJobLOffer(selectedPost,value);
                if (k == true) {
                    System.out.println("this will change array");
                } else {
                    System.out.println("this throw exception here");
                    k = true;
                }
            }
        }else if (selectedPost instanceof Event){
            while (k == false) {
                System.out.println(4);
                value = 1;
                k = db.checkEventCap(selectedPost,value);
                if (k == true) {
                    System.out.println("this will change array");
                } else {
                    System.out.println("this throw exception here");
                    k = true;
                }
            }

        }

        Model.Reply r1 = new Reply (selectedPost.getPostID(), value, Login.studentID);

        Databases.reply.add(r1);
        //Databases.insertReplyTable(r1);

        for (int i = 0; i < Databases.reply.size(); i++)
            System.out.println(Databases.reply.get(i).getResponderID());
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
