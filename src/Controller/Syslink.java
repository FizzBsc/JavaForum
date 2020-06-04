package Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Model.Post;
import Model.Event;
import Model.Reply;

public class Syslink {

    public static String studentID;
    static int pCount = 0;
    ArrayList<Post> posts= new ArrayList<Post>();
    ArrayList<Reply> replies = new ArrayList<Reply>();

    void NewEvent(){

        String title = null;
        String description = null;
        String venue = null;
        boolean retryInput = true;
        String date = null;

        while(retryInput)
            try {																//retry Date input exception
                System.out.println("Date in dd/MM/yyyy: ");
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date2 = dateFormat.parse(date);
                retryInput = false;
            } catch (ParseException e) {
                System.out.println("Wrong date format, please enter");
            }
        System.out.println("Capacity");

        int capacity = 0;

        posts.add(new Event(null, title, description, studentID, venue, date, capacity, 0, true));
    }
    void NewReply() {

        String pstID = null;
        String postID = pstID.toUpperCase();

        for (int i = 0; i<pCount; i++) {
            String post = posts.get(i).postID;

            if (postID.equals(post)){
                replies.add(new Reply(postID, 0, studentID));

            }
            else if (posts.get(i).delete!=false) {
                    System.out.println("Post not found");
            }
        }
    }
}


