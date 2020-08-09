package Model;

import java.io.Serializable;

public abstract class Post implements Serializable {

    private static final long serialVersionUID = 202006052421L;
    public String postID;
    private String title;
    private String description;
    public String creatorID;

    public void setStatus(boolean status) {
        this.status = status;
    }

    private boolean status;
    public boolean delete; //delete post should still be accessible if there was an admin extension

    public Post (String postID, String title, String description, String creatorID, Boolean status) {
        this.postID = postID;
        this.title = title;
        this.description = description;
        this.creatorID = creatorID;
        this.status = true;
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

    public abstract boolean handleReply(String cell) throws Exception;

    public abstract String postID();
}