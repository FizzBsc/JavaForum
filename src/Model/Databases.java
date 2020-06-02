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


    }

    public static void createTable(String DB_NAME, String TABLE_NAME) throws SQLException {

        try (Connection con = ConnectionTest.getConnection(DB_NAME);
             Statement stmt = con.createStatement();
        ) {
            int result = stmt.executeUpdate("CREATE TABLE Job ("
                    + "postID VARCHAR(20) NOT NULL,"
                    + "title VARCHAR(20) NOT NULL,"
                    + "description VARCHAR(2000) NOT NULL,"
                    + "creatorID VARCHAR(20) NOT NULL,"
                    + "pPrice DOUBLE NOT NULL,"
                    + "lowOffer DOUBLE NOT NULL,"
                    + "PRIMARY KEY (postID))");
            if (result == 0) {
                System.out.println("Table " + TABLE_NAME + " has been created successfully");
            } else {
                System.out.println("Table " + TABLE_NAME + " is not created");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertTable(Post post) throws SQLException, ClassNotFoundException {
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

    public static Connection getConnection(String dbName)
            throws SQLException, ClassNotFoundException {
        //Registering the HSQLDB JDBC driver
        Class.forName("org.hsqldb.jdbc.JDBCDriver");
        System.out.println(dbName);
        /* Database files will be created in the "database"
         * folder in the project. If no username or password is
         * specified, the default SA user and an empty password are used */
        Connection con = DriverManager.getConnection
                ("jdbc:hsqldb:file:database/" + dbName, "SA", "");
        return con;
    }
}

