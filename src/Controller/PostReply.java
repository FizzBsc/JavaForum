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
import java.util.InputMismatchException;
import java.util.ResourceBundle;

public class PostReply implements Initializable {

    @FXML public Label postIDLbl;
    @FXML public Label valueLbl;
    @FXML public TextField valueField;
    @FXML public Button submitBut;
    @FXML public Button cancelBut;
    private Post selectedPost;
    private double value;

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
        Stage stage = (Stage) submitBut.getScene().getWindow();

        Databases db = new Databases();

        double offer = 0;
        String hL = null;
        boolean k = false;
        try {
            if (selectedPost instanceof Sale) {
                while (k == false) {
                    value = Double.parseDouble(valueField.getText());
                    k = db.checkSaleHOffer(selectedPost, value);
                    if (k == false) {
                        offer = ((Sale) selectedPost).getHighOffer() + ((Sale) selectedPost).getMinRaise();
                        hL = "higher";
                        throw new OfferException("");
                    }
                }
            } else if (selectedPost instanceof Job) {
                while (k == false) {
                    value = Double.parseDouble(valueField.getText());
                    k = db.checkJobLOffer(selectedPost, value);
                    if (k == false) {
                        offer = ((Job) selectedPost).getLowOffer();
                        hL = "lower";
                        throw new OfferException("");
                    }
                }
            } else if (selectedPost instanceof Event) {
                while (k == false) {
                    value = 1;
                    k = db.checkEventCap(selectedPost, value);
                    if (k == false) {
                        throw new AlreadyAttendingException("");
                    }
                }
            }

            Model.Reply r1 = new Reply(selectedPost.getPostID(), value, Login.studentID);

            Databases.reply.add(r1);
            Databases.insertReplyTable(r1);

            MainMenu menu = new MainMenu();
            menu.startMenu();
            stage.close();
        } catch (InputMismatchException e) {
            AlertBox.alert("Wrong input Error", "Wrong input for offer", "Please enter a valid value");
            k = false;
        }
        catch (NumberFormatException e){
            AlertBox.alert("Wrong input Error","Wrong input for offer", "Please enter a valid value");
            k = false;
        } catch (OfferException e){
            AlertBox.alert("Offer Error","Offer must be "+hL+ " than " +offer, "Please enter a valid offer");
            k = false;
        }catch (AlreadyAttendingException e){
            AlertBox.alert("Attending Error","You're already attending", "");
            MainMenu menu = new MainMenu();
            menu.startMenu();
            stage.close();
        }
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
