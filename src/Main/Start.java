package Main;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

import static Model.Databases.linkDBPosts;

public class Start extends Application {

    public static void main(String[] args) throws SQLException {
        Model.ConnectionTest.linkDB();
        //createTable("Reply", "Replies");
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


