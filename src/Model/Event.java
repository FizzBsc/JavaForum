package Model;

import java.io.Serializable;

public class Event extends Post implements Serializable {

    private static final long serialVersionUID = 020260503421L;
    private String venue;
    private String date;
    private int capacity;
    private int attendeeCount;

    static int sharedCounter = 0;
    public Event(String postID, String title, String description, String creatorID, String venue, String date, int capacity, int attendeeCount, boolean status) {
        super(postID, title, description, creatorID, status);

        this.venue = venue;
        this.date = date;
        this.capacity = capacity;
        this.attendeeCount = attendeeCount;
        this.postID = "EVE" + String.format("%03d", ++sharedCounter);
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
