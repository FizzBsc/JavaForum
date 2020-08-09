package Model;

import java.io.File;
import java.io.Serializable;

public class Photo implements Serializable {

    private static final long serialVersionUID = 939369653291L;
    private String postID;
    private File file;

    public Photo (String postID) {
        this.postID = postID;
        file = new File(("images/Default1.jpg"));
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public File getFile() {
        return file;
    }


    public void setFile(File file) {
        this.file = file;
    }

}
