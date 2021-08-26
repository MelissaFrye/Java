package DBAccess;

import Model.Countries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils_Database.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Contains methods to query the database.
 */
public class DBCountries {

    /**
     * Uses a prepared statement to get a result set from the database.
     * @return an observable list of all countries.
     */
    public static ObservableList<Countries> getAllCountries(){

        ObservableList<Countries> clist = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM countries";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                int countryId = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");
                Countries C = new Countries(countryId, countryName);
                clist.add(C);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return  clist;
    }

}
