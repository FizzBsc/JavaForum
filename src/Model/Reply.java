package Model;

import java.io.Serializable;

public class Reply implements Serializable {


    private static final long serialVersionUID = 626266657515L;
    public String postID;
    public double value;
    public String responderID;

    public Reply (String postID, double value, String responderID)
    {
        this.postID = postID;
        this.value = value;
        this.responderID = responderID;

    }

    public String getPostID() {
        return postID;
    }

    public double getValue() {
        return value;
    }

    public String getResponderID() {
        return responderID;
    }


}
