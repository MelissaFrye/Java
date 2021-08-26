package DBAccess;

import Model.Contacts;
import Model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils_Database.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Contains methods to query the database.
 */
public class DBUsers {

    /**
     * Uses a prepared statement to get a result set of users from the database.
     * @return an observable list of all users.
     */
    public static ObservableList<Users> getAllUsers(){

        ObservableList<Users> usersList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM users";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                int User_ID = rs.getInt("User_ID");
                String User_Name = rs.getString("User_Name");
                Users U = new Users(User_ID, User_Name);
                usersList.add(U);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return usersList;
    }

    /**
     * Returns a name and total appointments for each user ID.
     * @return an observable list.
     */
    public static ObservableList<Users> getUserStats() {
        ObservableList userStats = FXCollections.observableArrayList();
        try {
            String sqls = "SELECT User_Name, count(*)  FROM appointments, users Where appointments.User_ID = users.User_ID group by appointments.User_ID";

            PreparedStatement pss = DBConnection.getConnection().prepareStatement(sqls);

            ResultSet rss = pss.executeQuery();

            while (rss.next()){
                String name = rss.getString("User_Name");
                int count = rss.getInt("count(*)");
                Users S = new Users(name, count);
                userStats.add(S);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userStats;
    }

    /**
     * Checks user entered login credentials against the users table.
     * @param userName user types in username.
     * @param password user types in password.
     * @return the user's ID if sucessful login, otherwise 0.
     */
    public static int validateUser(String userName, String password) {
        try {
            String sql = "SELECT * FROM users WHERE User_Name = ? and Password = ?;";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.setString(1, userName);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                return rs.getInt("User_ID"); // already returns user ID
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return 0; //bad case
    }

}
