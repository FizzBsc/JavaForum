package Model;

public class Sale extends Post {

    private double askPrice;
    private double highOffer;
    private double minRaise;
    int count = 0;


    static int sharedCounter = 0;

    public Sale(String postID, String title, String description, String creatorID, double askPrice, double highOffer, double minRaise) {
        super(postID, title, description, creatorID);

        this.postID = "SAL" + String.format("%03d", ++sharedCounter);
        this.askPrice = askPrice;
        this.highOffer = highOffer;
        this.minRaise = minRaise;

    }

    public double getAskPrice() {
        return askPrice;
    }

    public double getHighOffer() {
        return highOffer;
    }

    public double getMinRaise() {
        return minRaise;
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