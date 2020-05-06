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
import java.util.ArrayList;

public class Start extends Application {

    public static void main(String[] args) {
        Databases.SID.add("s1");
        Databases.SID.add("s2");
        Databases.SID.add("s3");
        Databases.post.add(new Event(null,"EVENT1","def","s1","rmit","12/12/2020",10,1));
        Databases.post.add(new Sale(null, "sale1", "monies", "s2", 20, 50, 15));
        for (int i = 0 ; i<15; i++ )
            Databases.post.add(new Job(null, "JOB1", "Im rich", "s3", 30, 10));
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


