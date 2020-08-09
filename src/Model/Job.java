package Model;

import java.io.Serializable;

public class Job extends Post implements Serializable {

    private static final long serialVersionUID = 121216154421L;
    static int sharedCounter = 0;
    private double pPrice;
    private double lowOffer;

    public Job(String postID, String title, String description, String creatorID, double pPrice, double lowOffer, boolean status) {
        super(postID, title, description, creatorID, status);

        this.pPrice =pPrice;
        this.lowOffer = lowOffer;
        this.postID = "JOB" + String.format("%03d", ++sharedCounter);
    }

    public double getpPrice() {
        return pPrice;
    }

    public double getLowOffer() {
        return lowOffer;
    }

    public void setLowOffer(double lowOffer) {
        this.lowOffer = lowOffer;
    }

    @Override
    public boolean handleReply(String cell) {
        for (int i = 0; i < Databases.post.size(); i++)
            if (cell.equals(Databases.post.get(i).getPostID())) {
                    return true;
            }

        return false;
    }


    @Override
    public String postID() {
        return null;
    }
}
