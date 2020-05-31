package Model;

import java.sql.*;
import java.util.ArrayList;

public class Databases {
    public static ArrayList<String> SID = new ArrayList<String>();
    public static ArrayList<Post> post = new ArrayList<Post>();

    public static void linkDBPosts() throws SQLException {


        final String DB_NAME = "Posts";
        final String TABLE_NAME = "Sale";
createTable(DB_NAME,TABLE_NAME);
        checkSaleTable("Posts", "Sale");


    }

    public static void createTable(String DB_NAME, String TABLE_NAME) throws SQLException {

        try (Connection con = ConnectionTest.getConnection(DB_NAME);
             Statement stmt = con.createStatement();
        ) {
            int result = stmt.executeUpdate("CREATE TABLE Sale ("
                    + "postID VARCHAR(20) NOT NULL,"
                    + "title VARCHAR(20) NOT NULL,"
                    + "description VARCHAR(2000) NOT NULL,"
                    + "creatorID VARCHAR(20) NOT NULL,"
                    + "askPrice DOUBLE NULL,"
                    + "highOffer DOUBLE NULL,"
                    + "minRaise DOUBLE NULL,"
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

    public static void insertTable(Post post) throws SQLException {
        String k = post.postID;
        try (Connection con = ConnectionTest.getConnection("Posts");
             Statement stmt = con.createStatement();
        ) {
            if (post instanceof  Sale) {
                String query = "INSERT INTO Sale" +
                        " VALUES ('" + k + "','" + post.getTitle() + "','" + post.getDescription() + "','" + post.getCreatorID() + "','" + ((Sale) post).getAskPrice() + "','" + ((Sale) post).getHighOffer() + "','" + ((Sale) post).getMinRaise() + "')";
                int result = stmt.executeUpdate(query);

                System.out.println(result + " row(s) affected");

                con.commit();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Insert into table Sale " + " executed successfully");

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

                for (int i = 0; i<post.size(); i++)
                    System.out.println(post.get(i).postID+ post.get(i).getTitle()
                            + post.get(i).getDescription()+ post.get(i).getCreatorID()
                            + ((Sale) post.get(i)).getAskPrice()+ ((Sale) post.get(i)).getHighOffer()
                            + ((Sale) post.get(i)).getMinRaise());
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

