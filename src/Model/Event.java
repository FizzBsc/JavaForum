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

    public void setAttendeeCount(int attendeeCount) {
        this.attendeeCount = attendeeCount;
    }

    @Override
    public boolean handleReply(String cellID) {
        for (int i = 0; i < Databases.post.size(); i++)
            if (cellID.equals(Databases.post.get(i).getPostID())) {

                    return true;
            }
        return false;
    }

    @Override
    public String getReplyDetails() {
        return date;
    }

    @Override
    public String postID() {
        return null;
    }
}
