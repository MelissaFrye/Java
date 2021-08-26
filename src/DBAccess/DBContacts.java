package DBAccess;

import Model.Contacts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils_Database.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Contains methods to query the database.
 */
public class DBContacts {

    /**
     * Uses a prepared statement to get a result set from the database.
     * @return an observable list of all contacts.
     */
    public static ObservableList<Contacts> getAllContacts(){

        ObservableList<Contacts> contactsList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM contacts";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                int Contact_ID = rs.getInt("Contact_ID");
                String Contact_Name = rs.getString("Contact_Name");
                Contacts C = new Contacts(Contact_ID, Contact_Name);
                contactsList.add(C);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return contactsList;
    }
}
