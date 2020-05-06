package Model;

public class Job extends Post {

    private double pPrice;
    private double lowOffer;


    static int sharedCounter = 0;

    public Job(String postID, String title, String description, String creatorID, double pPrice, double lowOffer) {
        super(postID, title, description, creatorID);

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

    @Override
    public void handleReply(Reply reply) {

    }

    @Override
    public String getReplyDetails() {
        return creatorID;
    }
}
