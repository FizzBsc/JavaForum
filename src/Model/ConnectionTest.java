package Model;

import java.sql.*;


public class ConnectionTest {
    private static ConnectionTest n;


    public static void linkDB() throws SQLException {


        final String DB_NAME = "Students";
        final String TABLE_NAME = "STUDENT";
        checkTable(DB_NAME,TABLE_NAME);


    }

    public static void createTable(String DB_NAME, String TABLE_NAME) throws SQLException{

        try (Connection con = ConnectionTest.getConnection(DB_NAME);
             Statement stmt = con.createStatement();
        ) {
            int result = stmt.executeUpdate("CREATE TABLE student ("
                    + "id VARCHAR(20) NOT NULL,"
                    + "PRIMARY KEY (id))");
            if(result == 0) {
                System.out.println("Table " + TABLE_NAME + " has been created successfully");
            } else {
                System.out.println("Table " + TABLE_NAME + " is not created");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertTable(String DB_NAME, String TABLE_NAME){
        try (Connection con = ConnectionTest.getConnection(DB_NAME);
             Statement stmt = con.createStatement();
        ) {
            for (int i = 0; i<Databases.SID.size(); i++) {
                String query = "INSERT INTO " + TABLE_NAME +

                        " VALUES ('" + Databases.SID.get(i)+"')";
                int result = stmt.executeUpdate(query);
                System.out.println(result + " row(s) affected");
            }

            con.commit();

            System.out.println("Insert into table " + TABLE_NAME + " executed successfully");


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void checkTable(String DB_NAME, String TABLE_NAME){
        try (Connection con = ConnectionTest.getConnection(DB_NAME);
             Statement stmt = con.createStatement();
        ) {
            String query = "SELECT * FROM " + TABLE_NAME;

            try (ResultSet resultSet = stmt.executeQuery(query)) {
                while(resultSet.next()) {
                    System.out.printf("Id: %s |\n",
                            resultSet.getString("id")
                    );
                    Databases.SID.add(resultSet.getString("id"));
                }

                for (int i = 0; i<Databases.SID.size(); i++)
                    System.out.println(Databases.SID.get(i));
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
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
