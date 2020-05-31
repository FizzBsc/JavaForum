package Model;

import javafx.beans.value.ObservableValue;

public abstract class Post {

    public String postID;
    private String title;
    private String description;
    public String creatorID;
    private boolean status;
    public boolean delete; //delete post should still be accessible if there was an admin extension



    public Post (String postID, String title, String description, String creatorID) {
        this.postID = postID;
        this.title = title;
        this.description = description;
        this.creatorID = creatorID;
        status = true;
        delete = false;

    }

    public String getPostID() {
        return postID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCreatorID() {
        return creatorID;
    }


    public boolean getStatus() {
        return status;
    }

    public abstract void handleReply(String cell);
    public abstract String getReplyDetails();

    public abstract String postID();
}