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
    public boolean handleReply(String cell) {
        for (int i = 0; i < Databases.post.size(); i++)
            if (cell.equals(Databases.post.get(i).getPostID())) {
                Post post = Databases.post.get(i);
                if (post.getStatus() == true)
                    return true;
            }

        return false;
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
