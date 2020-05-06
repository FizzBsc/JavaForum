package Model;

public class Event extends Post {

    private String venue;
    private String date;
    private int capacity;
    private int attendeeCount;
    public boolean status;

    static int sharedCounter = 0;
    public Event(String postID, String title, String description, String creatorID,  String venue, String date,int capacity,int attendeeCount) {
        super(postID, title, description, creatorID);

        this.venue = venue;
        this.date = date;
        this.capacity = capacity;
        this.attendeeCount = attendeeCount;
        this.postID = "EVE" + String.format("%03d", ++sharedCounter);
        boolean status = true;
    }


    public String getVenue() {
        return venue;
    }

    public String getDate() {
        return date;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAttendeeCount() {
        return attendeeCount;
    }


    @Override
    public void handleReply(Reply reply) {

    }

    @Override
    public String getReplyDetails() {
        return date;
    }
}
