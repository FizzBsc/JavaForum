package Main;
import Model.Databases;
import Model.Event;
import Model.Job;
import Model.Sale;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static Model.ConnectionTest.linkDB;
import static Model.Databases.insertTable;
import static Model.Databases.linkDBPosts;

public class Start extends Application {

    public static void main(String[] args) throws SQLException {

Model.ConnectionTest.linkDB();
        linkDBPosts();

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            Controller.Login.startLogin();

        } catch (IOException e) {
            System.out.println("Fail");
        }


    }
}


