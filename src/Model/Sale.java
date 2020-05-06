package Model;

public class Sale extends Post {

    private double askPrice;
    private double highOffer;
    private double minRaise;
    static Reply[] r = new Reply[100];
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
    public void handleReply(Reply reply) {// HandleReply used to store to DB extension

    }

    @Override
    public String getReplyDetails() {
        return creatorID;
    }
}