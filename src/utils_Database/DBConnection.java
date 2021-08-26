package utils_Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Opens and closes the connection to the database.
 */
public class DBConnection {

    // jdbc url parts
    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress = "//wgudb.ucertify.com:3306/";
    private static final String dbName = "WJ07M8O"; // NOTE: Adding the dbName

    // jdbc url;
    private static final String jdbcURL = protocol + vendorName + ipAddress + dbName;

    // driver and connection interface reference
    private static final String mysqlJDBCDriver = "com.mysql.cj.jdbc.Driver";
    private static Connection conn = null;

    private static final String username = "U07M8O";  // user name
    private static String password = "53689066022";  // password

    /**
     * start connection, just once, called in Controller.Main.
     * @return the connection.
     */
    public static Connection startConnection() {

        try {
            Class.forName(mysqlJDBCDriver);
            conn = DriverManager.getConnection(jdbcURL, username, password);

            System.out.println("Connection successful");
        } catch (ClassNotFoundException e) { //Use printStackTrace for outputting exceptions
            e.printStackTrace();

        } catch (SQLException e) { //Use printStackTrace for outputting exceptions
            e.printStackTrace();
        }

        return conn;
    }

    /**
     * Gets the connection.
     * @return the connection.
     */
    public static Connection getConnection() {
        return conn;
    }

    /**
     * Closes the connection.
     */
    public static void closeConnection()
    {
        try{
            conn.close();
            System.out.println("-Connection closed-"); }
        catch (Exception e){} // do nothing
    }



}

