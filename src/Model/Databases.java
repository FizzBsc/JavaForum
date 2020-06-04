package Model;

import java.sql.*;
import java.util.ArrayList;

public class Databases {
    public static ArrayList<String> SID = new ArrayList<String>();
    public static ArrayList<Post> post = new ArrayList<Post>();
    public static ArrayList<Reply> reply = new ArrayList<>();

    public static void linkDBPosts() throws SQLException {


        final String DB_NAME = "Posts";
        final String TABLE_NAME = "Job";
        checkSaleTable("Posts", "Sale");
        checkEventTable("Posts", "Event");
        checkJobTable("Posts", "Job");
        checkReplytTable("Reply","Replies");

    }

    public static void createTable(String DB_NAME, String TABLE_NAME) throws SQLException {

        try (Connection con = ConnectionTest.getConnection(DB_NAME);
             Statement stmt = con.createStatement();
        ) {
            int result = stmt.executeUpdate("CREATE TABLE Replies ("
                    + "postID VARCHAR(20) NOT NULL,"
                    + "value DOUBLE NOT NULL,"
                    + "responderID VARCHAR(20) NOT NULL,"
                    + "PRIMARY KEY (responderID))");
            if (result == 0) {
                System.out.println("Table " + TABLE_NAME + " has been created successfully");
            } else {
                System.out.println("Table " + TABLE_NAME + " is not created");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertTable(Post post) throws SQLException, ClassNotFoundException { //insert to post table
        String k = post.postID;
        String name = null;
        int result = 0;
        try (Connection con = ConnectionTest.getConnection("Posts");
             Statement stmt = con.createStatement();
        ) {
            if (post instanceof Sale) {
                String query = "INSERT INTO Sale" +
                        " VALUES ('" + k + "','" + post.getTitle() + "','" + post.getDescription() + "','" + post.getCreatorID() + "','" + ((Sale) post).getAskPrice() + "','" + ((Sale) post).getHighOffer() + "','" + ((Sale) post).getMinRaise() + "')";
                result = stmt.executeUpdate(query);
                name = "Sale";
            } else if (post instanceof Event) {
                String query = "INSERT INTO Event" +
                        " VALUES ('" + k + "','" + post.getTitle() + "','" + post.getDescription() + "','" + post.getCreatorID() + "','" + ((Event) post).getVenue() + "','" + ((Event) post).getDate() + "','" + ((Event) post).getCapacity() + "','" + ((Event) post).getAttendeeCount() + "')";
                result = stmt.executeUpdate(query);
                name = "Event";
            } else if (post instanceof Job) {
                String query = "INSERT INTO Job" +
                        " VALUES ('" + k + "','" + post.getTitle() + "','" + post.getDescription() + "','" + post.getCreatorID() + "','" + ((Job) post).getpPrice() + "','" + ((Job) post).getLowOffer() + "')";
                result = stmt.executeUpdate(query);
                name = "Job";
            }

            System.out.println(result + " row(s) affected");

            con.commit();
            System.out.println("Insert into table "+ name +" executed successfully");
        }
    }
    public static void insertReplyTable(Reply reply) throws SQLException, ClassNotFoundException {
        String k = reply.postID;
        String name = null;
        int result = 0;
        try (Connection con = ConnectionTest.getConnection("Reply");
             Statement stmt = con.createStatement();
        ) {
                String query = "INSERT INTO Replies" +
                        " VALUES ('" + k + "','" + reply.getValue() + "','" + reply.getResponderID() + "')";
                name = "Replies";

            System.out.println(result + " row(s) affected");

            con.commit();
            System.out.println("Insert into table "+ name +" executed successfully");
        }
    }

    public static void checkReplytTable(String DB_NAME, String TABLE_NAME){
        try (Connection con = ConnectionTest.getConnection(DB_NAME);
             Statement stmt = con.createStatement();
        ) {
            String query = "SELECT * FROM " + TABLE_NAME;

            try (ResultSet resultSet = stmt.executeQuery(query)) {
                while(resultSet.next()) {
                    System.out.printf(" %s | %f | %s \n",
                            resultSet.getString("postID"),resultSet.getDouble(2),resultSet.getString(3));
                    reply.add(new Reply( resultSet.getString("postID"),resultSet.getDouble(2),resultSet.getString(3)));
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void checkEventTable(String DB_NAME, String TABLE_NAME){
        try (Connection con = ConnectionTest.getConnection(DB_NAME);
             Statement stmt = con.createStatement();
        ) {
            String query = "SELECT * FROM " + TABLE_NAME;

            try (ResultSet resultSet = stmt.executeQuery(query)) {
                while(resultSet.next()) {
                    System.out.printf(" %s | %s | %s | %s | %s | %s | %d | %d\n",
                            resultSet.getString("postID"),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),
                            resultSet.getString(5),resultSet.getString(6),resultSet.getInt(7),resultSet.getInt(8));
                    post.add(new Event(resultSet.getString("postID"),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),
                            resultSet.getString(5),resultSet.getString(6),resultSet.getInt(7),resultSet.getInt(8)));
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void checkSaleTable(String DB_NAME, String TABLE_NAME){
        try (Connection con = ConnectionTest.getConnection(DB_NAME);
             Statement stmt = con.createStatement();
        ) {
            String query = "SELECT * FROM " + TABLE_NAME;

            try (ResultSet resultSet = stmt.executeQuery(query)) {
                while(resultSet.next()) {
                    System.out.printf(" %s | %s | %s | %s | %f | %f | %f\n",
                            resultSet.getString("postID"),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),
                            resultSet.getDouble(5),resultSet.getDouble(6),resultSet.getDouble(7));
                    post.add(new Sale(resultSet.getString("postID"),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),
                            resultSet.getDouble(5),resultSet.getDouble(6),resultSet.getDouble(7)));
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void checkJobTable(String DB_NAME, String TABLE_NAME){
        try (Connection con = ConnectionTest.getConnection(DB_NAME);
             Statement stmt = con.createStatement();
        ) {
            String query = "SELECT * FROM " + TABLE_NAME;

            try (ResultSet resultSet = stmt.executeQuery(query)) {
                while(resultSet.next()) {
                    System.out.printf(" %s | %s | %s | %s | %d | %d\n",
                            resultSet.getString("postID"),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),
                            resultSet.getInt(5),resultSet.getInt(6));
                    post.add(new Job(resultSet.getString("postID"),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),
                            resultSet.getInt(5),resultSet.getInt(6)));
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public boolean checkSaleHOffer(Post sale,double offer){

        if(((Sale) sale).getHighOffer() == 0){
            ((Sale) sale).setHighOffer(offer);
            return true;
        } else if ((((Sale) sale).getMinRaise()+((Sale) sale).getMinRaise()) < offer) {
            ((Sale) sale).setHighOffer(offer);
            return true;
        }

    return false;
    }
    public boolean checkJobLOffer(Post job,double offer){
        if (((Job) job).getLowOffer() == 0){
            ((Job) job).setLowOffer(offer);
            return true;
        }else if(((Job) job).getLowOffer() > offer){
            ((Job) job).setLowOffer(offer);
            return true;
        }
        return false;

    }
    public boolean checkEventCap(Post event, double value){
        double calcCap = 0;
        for ( int i = 0; i<reply.size(); i++){
            if (event.getPostID().equals(reply.get(i).getPostID())){
                calcCap += reply.get(i).getValue();
            }
        }
        if (((Event) event).getCapacity() <= (calcCap + value)){
            event.setStatus(false);
            ((Event) event).setAttendeeCount((int)(calcCap + value));
            return true;
        }else if (((Event) event).getCapacity() > (calcCap + value)){
            ((Event) event).setAttendeeCount((int)(calcCap + value));
            return true;
        }
        return false;
    }

    public static Connection getConnection(String dbName)
            throws SQLException, ClassNotFoundException {
        Class.forName("org.hsqldb.jdbc.JDBCDriver");
        System.out.println(dbName);

        Connection con = DriverManager.getConnection
                ("jdbc:hsqldb:file:database/" + dbName, "SA", "");
        return con;
    }
}

