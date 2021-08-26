package DBAccess;

import Model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils_Database.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *  Contains methods to query the database.
 */
public class DBCustomers {

    /**
     * Uses a prepared statement to get a result set from the database.
     * @return an observable list of all customers.
     */
    public static ObservableList<Customers> getCustomersTable() {

        ObservableList<Customers> customersTableList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, customers.Division_ID, first_level_divisions.Division, countries.Country, countries.Country_ID FROM customers, first_level_divisions, countries WHERE customers.Division_ID = first_level_divisions.Division_ID AND first_level_divisions.Country_ID = countries.Country_ID";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int Customer_ID = rs.getInt("Customer_ID");
                String Customer_Name = rs.getString("Customer_Name");
                String Address = rs.getString("Address");
                String Postal_Code = rs.getString("Postal_Code");
                String Phone = rs.getString("Phone");
                int Division_ID = rs.getInt("Division_ID");
                String Division = rs.getString("Division");
                String Country = rs.getString("Country");
                int Country_ID = rs.getInt("Country_ID");
                Customers C = new Customers(Customer_ID, Customer_Name, Address, Postal_Code, Phone, Division_ID, Division, Country_ID, Country);
                customersTableList.add(C);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customersTableList;
    }

    /**
     * A prepared statement is used to insert a new record into the customers table.
     * @param name customer name.
     * @param address customer address.
     * @param postal customer postal code.
     * @param phone customer phone number.
     * @param divID customer division ID.
     */
    public static void createCustomer(String name, String address, String postal, String phone, int divID) {
        try {
            String sqlci = "INSERT INTO customers VALUES (NULL, ?, ?, ?, ?, now(), 'MF', now(), 'MF', ?)";

            // create a prepared statement
            PreparedStatement psci = DBConnection.getConnection().prepareStatement(sqlci);
            // question marks
            psci.setString(1, name);
            psci.setString(2, address);
            psci.setString(3, postal);
            psci.setString(4, phone);
            psci.setInt(5, divID);

            psci.execute();

        } catch (SQLException e) {
            e.printStackTrace(); //always print stack trace unless hiding exceptions
        }
    }

    /**
     * Updates an existing record in the customers table using a prepared statement.
     * @param cusID customer ID.
     * @param name customer name.
     * @param address customer address.
     * @param postal customer postal code.
     * @param phone customer phone number.
     * @param divID customer division ID.
     */
    public static void modifyCustomer(int cusID, String name, String address, String postal, String phone, int divID) {
        try {
            //sql
            String sqlcm = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?";

            //create prepared statement
            PreparedStatement pscm = DBConnection.getConnection().prepareStatement(sqlcm);

            //question marks
            pscm.setString(1, name);
            pscm.setString(2, address);
            pscm.setString(3, postal);
            pscm.setString(4, phone);
            pscm.setInt(5, divID);
            pscm.setInt(6, cusID);

            //execute!
            pscm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * A selected customer is deleted from the DB using a prepared statement.
     * @param cusID ID of the selected customer.
     */
    public static void deleteCustomer(int cusID) {
        try {
            String sqlcd = "DELETE FROM customers WHERE Customer_ID = ?";

            //create a prepared statement
            PreparedStatement pscd = DBConnection.getConnection().prepareStatement(sqlcd);
            // question mark set up
            pscd.setInt(1, cusID);
            //execute sql
            pscd.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Method to check for appointments when customer is deleted.
     * @param cusID ID of selected customer.
     * @return true if the are associated appointments and false if not.
     * @throws SQLException if sql statement doesn't work.
     */
    public static boolean hasAppts(int cusID) throws SQLException {
            String sqlca = "SELECT Appointment_ID FROM appointments where Customer_ID = ?";

            // prepared statement
            PreparedStatement psca = DBConnection.getConnection().prepareStatement(sqlca);
            // question marks
            psca.setInt(1, cusID);
            // make it so! result set
            ResultSet rs = psca.executeQuery();

            if (rs.next()) {
                return true;

            } else {
                return false;
            }

        }
    }





