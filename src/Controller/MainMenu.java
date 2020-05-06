package Controller;

import Model.Databases;
import Model.Event;
import Model.Post;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.*;

import java.io.IOException;
import java.util.function.Function;

public class MainMenu {

    @FXML public Button logoutButton;
    @FXML public Label welcomeID;
    @FXML public Button newEvent;
    @FXML public Button newSale;
    @FXML public Button newJob;
    @FXML private TableView<Post> tableView;
    @FXML private TableColumn<Post, String> postIDColumn;
    @FXML private TableColumn<Post, String> titleColumn;
    @FXML private TableColumn<Post, String> descriptionColumn;
    @FXML private TableColumn<Post, String> creatorColumn;
    @FXML private TableColumn<Post, Post> replyColumn;
    @FXML private void initialize()
    {
        welcomeID.setText("Welcome " + Login.studentID);
        postIDColumn.setCellValueFactory(new PropertyValueFactory<Post, String>("postID"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Post, String>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Post, String>("description"));
        creatorColumn.setCellValueFactory(new PropertyValueFactory<Post, String>("creatorID"));

        replyColumn.setCellFactory(col -> {
            Button replyButton = new Button("Reply");
            TableCell<Post, Post> cell = new TableCell<Post, Post>() {
                @Override
                public void updateItem(Post person, boolean empty) {
                    super.updateItem(person, empty);
                        setGraphic(replyButton);
                }
            };
            replyButton.setOnAction(e -> newReplyClickHandler(cell.getTableRow().getItem().getPostID()));

            return cell ;
        });
        tableView.setItems(getPost());

    }

    public ObservableList<Post> getPost()
    {
        ObservableList<Post> post = FXCollections.observableArrayList(Databases.post);

        return post;
    }

    public void startMenu() throws Exception {

        try {


            Stage stage = new Stage();
            Parent root = FXMLLoader.load(MainMenu.class.getResource("/View/Main_Menu.fxml"));
            Scene scene = new Scene(root, 600, 482);
            stage.setTitle("Menu");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();


        } catch (IOException e) {
            System.out.println("Fail");
        }
    }
    private static <S,T> TableColumn<S,T> column(String title, Function<S, ObservableValue<T>> property, double width) {
        TableColumn<S,T> col = new TableColumn<>(title);
        col.setCellValueFactory(cellData -> property.apply(cellData.getValue()));
        col.setPrefWidth(width);
        return col ;
    }

    public void logOut() throws Exception {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        try {
           Login.startLogin();
           stage.close();
       }
       catch (IOException e) {
           System.out.println("Fail");
       }
    }

    public void newReplyClickHandler(String cellID){
        for (int i = 0; i < Databases.post.size(); i++)
            if (cellID.equals(Databases.post.get(i).getPostID())){
                Post post = Databases.post.get(i);
                if (post instanceof Model.Sale) {
                    Model.Sale sale = (Model.Sale) post;
                    sale.handleReply(cellID);
                }
                else if (post instanceof Model.Event) {
                    Model.Event event = (Model.Event) post;
                    event.handleReply(cellID);
                }
                else if (post instanceof Model.Job) {
                    Model.Job job = (Model.Job) post;
                    job.handleReply(cellID);
                }
            }


    }

    @FXML
    public void newEventClickHandler(ActionEvent actionEvent) throws Exception{
        Stage stage = (Stage) newEvent.getScene().getWindow();
        NewEvent.newPost();
        stage.close();
    }
    @FXML
    public void newSaleClickHandler(ActionEvent actionEvent) throws Exception{
        Stage stage = (Stage) newSale.getScene().getWindow();
        NewSale.newPost();
        stage.close();
    }
    @FXML
    public void newJobClickHandler(ActionEvent actionEvent) throws Exception{
        Stage stage = (Stage) newJob.getScene().getWindow();
        NewJob.newPost();
        stage.close();
    }
    @FXML
    public void logoutClickHandler(ActionEvent actionEvent) throws Exception{
        logOut();
    }
}
