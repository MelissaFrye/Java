package DBAccess;

import Model.Divisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils_Database.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Contains methods to query the database.
 */
public class DBDivisions {

    /**
     * Uses a prepared statement to get a result set from the database.
     * @param countryID selected country ID.
     * @return an observable list of all divisions.
     */
    public static ObservableList<Divisions> getDivisionsByCountryID(int countryID){

        ObservableList<Divisions> dlist = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM first_level_divisions WHERE Country_ID = ?";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, countryID);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                int divisionId = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");
                int countryId = rs.getInt("COUNTRY_ID");
                Divisions D = new Divisions(divisionId, divisionName, countryId);
                dlist.add(D);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return  dlist;
    }


}
