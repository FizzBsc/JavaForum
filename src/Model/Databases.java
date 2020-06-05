package Model;

import Controller.Login;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class Databases {
    public static ArrayList<String> SID = new ArrayList<String>();
    public static ArrayList<Post> post = new ArrayList<Post>();
    public static ArrayList<Reply> reply = new ArrayList<>();
    public static ArrayList<Photo> pics = new ArrayList<>();

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
                    + "ID INT IDENTITY,"
                    + "PRIMARY KEY (ID))");
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
                        " VALUES ('" + k + "','" + post.getTitle() + "','" + post.getDescription() + "','" + post.getCreatorID() + "','" + ((Sale) post).getAskPrice() + "','" + ((Sale) post).getHighOffer() + "','" + ((Sale) post).getMinRaise() + "','" + post.getStatus() + "')";
                result = stmt.executeUpdate(query);
                name = "Sale";
            } else if (post instanceof Event) {
                String query = "INSERT INTO Event" +
                        " VALUES ('" + k + "','" + post.getTitle() + "','" + post.getDescription() + "','" + post.getCreatorID() + "','" + ((Event) post).getVenue() + "','" + ((Event) post).getDate() + "','" + ((Event) post).getCapacity() + "','" + ((Event) post).getAttendeeCount() + "','" + post.getStatus() + "')";
                result = stmt.executeUpdate(query);
                name = "Event";
            } else if (post instanceof Job) {
                String query = "INSERT INTO Job" +
                        " VALUES ('" + k + "','" + post.getTitle() + "','" + post.getDescription() + "','" + post.getCreatorID() + "','" + ((Job) post).getpPrice() + "','" + ((Job) post).getLowOffer() + "','" + post.getStatus() + "')";
                result = stmt.executeUpdate(query);
                name = "Job";
            }

            System.out.println(result + " row(s) affected");

            con.commit();
            System.out.println("Insert into table "+ name +" executed successfully");
        }
    }
    public static void insertReplyTable(Reply reply) throws SQLException, ClassNotFoundException {
        String k = reply.getPostID();
        String name = null;
        int result = 0;
        try (Connection con = ConnectionTest.getConnection("Reply");
             Statement stmt = con.createStatement();
        ) {
                String query = "INSERT INTO Replies" +
                        " VALUES ('" + k + "','" + reply.getValue() + "','" + reply.getResponderID() + "',NULL)";
                name = "Replies";

            System.out.println(result + " row(s) affected");
            result = stmt.executeUpdate(query);
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
                    System.out.printf(" %s | %s | %s | %s | %s | %s | %d | %d | %b\n",
                            resultSet.getString("postID"),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),
                            resultSet.getString(5),resultSet.getString(6),resultSet.getInt(7),resultSet.getInt(8),resultSet.getBoolean(9));
                    post.add(new Event(resultSet.getString("postID"),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),
                            resultSet.getString(5),resultSet.getString(6),resultSet.getInt(7),resultSet.getInt(8),resultSet.getBoolean(9)));
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
                            resultSet.getDouble(5),resultSet.getDouble(6),resultSet.getDouble(7),resultSet.getBoolean(8));
                    post.add(new Sale(resultSet.getString("postID"),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),
                            resultSet.getDouble(5),resultSet.getDouble(6),resultSet.getDouble(7),resultSet.getBoolean(8)));
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
                            resultSet.getInt(5),resultSet.getInt(6),resultSet.getBoolean(7));
                    post.add(new Job(resultSet.getString("postID"),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),
                            resultSet.getInt(5),resultSet.getInt(6),resultSet.getBoolean(7)));
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public boolean checkSaleHOffer(Post sale,double offer) throws SQLException {

        if(((Sale) sale).getHighOffer() == 0){
            System.out.println("only valid at 0");
            ((Sale) sale).setHighOffer(offer);
            updateSale("Posts",sale.getPostID(),offer);
            return true;
        } else if ((((Sale) sale).getHighOffer()+((Sale) sale).getMinRaise()) < offer) {
            System.out.println("at least its here");
            ((Sale) sale).setHighOffer(offer);
            updateSale("Posts",sale.getPostID(),offer);
            return true;
        }else if ((((Sale) sale).getHighOffer()+((Sale) sale).getMinRaise()) > offer) {
            System.out.println("yup");
           return false;
        }

    return false;
    }
    public boolean checkJobLOffer(Post job,double offer) throws SQLException {
        if (((Job) job).getLowOffer() == 0){
            ((Job) job).setLowOffer(offer);
            updateJob("Posts",job.getPostID(),offer);
            return true;
        }else if(((Job) job).getLowOffer() > offer){
            ((Job) job).setLowOffer(offer);
            updateJob("Posts",job.getPostID(),offer);
            return true;
        }
        else if(((Job) job).getLowOffer() < offer){
            return false;
        }
        return false;

    }
    public boolean checkEventCap(Post event, double value) throws SQLException {
        double calcCap = 0;
        for ( int i = 0; i<reply.size(); i++){
            if (event.getPostID().equals(reply.get(i).getPostID())){
                calcCap += reply.get(i).getValue();
            }
            if (reply.get(i).getResponderID().equals(Login.studentID)){
                return false;
            }
        }
        if (((Event) event).getCapacity() <= (calcCap + value)){
            event.setStatus(false);
            ((Event) event).setAttendeeCount((int)(calcCap + value));
            updateEvent("Posts",event.getPostID(),(calcCap + value));
            return true;
        }else if (((Event) event).getCapacity() > (calcCap + value)){
            ((Event) event).setAttendeeCount((int)(calcCap + value));
            updateEvent("Posts",event.getPostID(),(calcCap + value));
            return true;
        }
        return false;
    }
    public static void updateSale(String DB_NAME, String id, double newHigh) throws SQLException {
        try (Connection con = ConnectionTest.getConnection(DB_NAME);
             PreparedStatement pS = con.prepareStatement("UPDATE Sale SET highOffer = ? WHERE postID = ?");
        ) {
            pS.setDouble(1,newHigh);
            pS.setString(2,id);

            pS.executeUpdate();
            pS.close();
            System.out.println("Update complete.");
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void updateJob(String DB_NAME, String id, double newLow) throws SQLException {
        try (Connection con = ConnectionTest.getConnection(DB_NAME);
             PreparedStatement pS = con.prepareStatement("UPDATE Job SET lowOffer = ? WHERE postID = ?");
        ) {
            pS.setDouble(1,newLow);
            pS.setString(2,id);

            pS.executeUpdate();
            pS.close();
            System.out.println("Update complete.");
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void updateEvent(String DB_NAME, String id, double cap) throws SQLException {
        try (Connection con = ConnectionTest.getConnection(DB_NAME);
             PreparedStatement pS = con.prepareStatement("UPDATE Event SET attendeeCount = ? WHERE postID = ?");
        ) {
            pS.setDouble(1,cap);
            pS.setString(2,id);
            pS.executeUpdate();
            pS.close();
            System.out.println("Update complete.");
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void writeToFile(File selectedDirectory) throws IOException {

        FileOutputStream fos = new FileOutputStream(selectedDirectory);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(post);
        oos.writeObject(reply);
        oos.writeObject(pics);
        oos.close();
        fos.close();
    }
    public static void readFromFile(File newFile) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(newFile);
        ObjectInputStream ois = new ObjectInputStream(fis);
        post = (ArrayList<Post>) ois.readObject();
        reply = (ArrayList<Reply>) ois.readObject();
        pics = (ArrayList<Photo>) ois.readObject();
        System.out.println("load Successful");
//        for (int i = 0; i<post.size(); i++){
//            post.get(i).setStatus(true);
//        }
        ois.close();
        fis.close();

    }

    public void deletingArr(Post p){
        for (int i = 0; i < post.size(); i++){
            if (post.get(i).getPostID().equals(p.getPostID()))
                post.remove(i);
        }
    }
    public void deleteDB(Post p, String tbName){
        try (Connection con = ConnectionTest.getConnection("Posts");
             PreparedStatement pS = con.prepareStatement("DELETE FROM "+tbName+" WHERE postID = ?");
        ) {
            pS.setString(1,p.getPostID());
            pS.executeUpdate();
            pS.close();
            System.out.println("Deleted");
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
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

