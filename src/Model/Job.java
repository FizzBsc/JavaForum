package Model;

import java.io.Serializable;

public class Job extends Post implements Serializable {

    private static final long serialVersionUID = 121216154421L;
    private double pPrice;
    private double lowOffer;
    static int sharedCounter = 0;

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
    public void getPostDetails() {

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
    public double calcHighOffer(){
        double j = lowOffer;
        for (int i = 0; i<Databases.reply.size(); i++)
            if (Databases.reply.get(i).getPostID().equals(postID))
                if (Databases.reply.get(i).getValue() < j)
                    j=Databases.reply.get(i).getValue();

        return j;
    }

    @Override
    public String getReplyDetails() {
        return creatorID;
    }

    @Override
    public String postID() {
        return null;
    }
}
