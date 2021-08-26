package DBAccess;

import Model.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils_Database.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Contains methods to query the database.
 */
public class DBAppointments {

    /**
     * Uses a prepared statement to get a result set from the database.
     * @return an observable list of all appointments.
     */
    public static ObservableList<Appointments> getAppointmentsTable() {

        ObservableList<Appointments> appointmentsTableList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT Appointment_ID, Title, Description, Location, Type, Start, End, appointments.Customer_ID, User_ID, appointments.Contact_ID, Customer_Name, Contact_Name FROM appointments, customers, contacts where appointments.Customer_ID = customers.Customer_ID and appointments.Contact_ID = contacts.Contact_ID";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int Appointment_ID = rs.getInt("Appointment_ID");
                String Title = rs.getString("Title");
                String Description = rs.getString("Description");
                String Location = rs.getString("Location");
                String Type = rs.getString("Type");
                Timestamp Start = rs.getTimestamp("Start");
                Timestamp End = rs.getTimestamp("End");
                int Customer_ID = rs.getInt("Customer_ID");
                int User_ID = rs.getInt("User_ID");
                int Contact_ID = rs.getInt("Contact_ID");
                String Customer_Name = rs.getString("Customer_Name");
                String Contact_Name = rs.getString("Contact_Name");
                Appointments A = new Appointments(Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID, Customer_Name, Contact_Name);
                appointmentsTableList.add(A);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return appointmentsTableList;
    }

    /**
     * A prepared statement is used to insert a new record into the appointments table.
     * @param title appointment title.
     * @param desc appointment description.
     * @param loc appointment location.
     * @param type appointment type.
     * @param start appointment start time.
     * @param end appointment end time.
     * @param custID appointment customer ID.
     * @param userID appointment user ID.
     * @param contactID appointment contact ID.
     */
    public static void createAppointment(String title, String desc, String loc, String type, Timestamp start, Timestamp end, int custID, int userID, int contactID) {
        try {
            String sqlai = "INSERT INTO appointments VALUES (NULL, ?, ?, ?, ?, ?, ?, now(), 'MF', now(), 'MF', ?, ?, ?)";

            // create a prepared statement
            PreparedStatement psai = DBConnection.getConnection().prepareStatement(sqlai);
            // question marks
            psai.setString(1, title);
            psai.setString(2, desc);
            psai.setString(3, loc);
            psai.setString(4, type);
            psai.setTimestamp(5, start);
            psai.setTimestamp(6, end);
            psai.setInt(7, custID);
            psai.setInt(8, userID);
            psai.setInt(9, contactID);

            psai.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * A selected appointment is deleted from the DB using a prepared statement.
     * @param aID appointment ID.
     */
    public static void deleteAppointment(int aID) {
        try {
            // sql to delete an appointment
            String sqlad = "DELETE FROM appointments WHERE Appointment_ID = ?";

            // create a prepared statement
            PreparedStatement psda = DBConnection.getConnection().prepareStatement(sqlad);

            //question marks
            psda.setInt(1, aID);

            psda.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates an existing record in the appointments table using a prepared statement.
     * @param a_id appointment ID.
     * @param title appointment title.
     * @param desc appointment description.
     * @param loc appointment location.
     * @param type appointment type.
     * @param start appointment start time.
     * @param end appointment end time.
     * @param customerID appointment customer ID.
     * @param u_id appointment user ID.
     * @param contactID appointment contact ID.
     */
    public static void modifyAppointment(int a_id, String title, String desc, String loc, String type, Timestamp start,
                                         Timestamp end, int customerID, int u_id, int contactID) {
        try {

            //sql statement to modify existing entry in DB
            String sqlma = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, " +
                    "Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";

            // prepared statement
            PreparedStatement psma = DBConnection.getConnection().prepareStatement(sqlma);

            // map ?
            psma.setString(1, title);
            psma.setString(2, desc);
            psma.setString(3, loc);
            psma.setString(4, type);
            psma.setTimestamp(5, start);
            psma.setTimestamp(6, end);
            psma.setInt(7, customerID);
            psma.setInt(8, u_id);
            psma.setInt(9, contactID);
            psma.setInt(10, a_id);

            // make it so!
            psma.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a list of appointments by selected contact ID using a prepared statement.
     * @param contactID selected contact ID.
     * @return an Observable list of appointments.
     */
    public static ObservableList<Appointments> getApptsByContactID(int contactID) {

        ObservableList<Appointments> apptsByContact = FXCollections.observableArrayList();

        try {
            //sql statement
            String sqlabc = "SELECT appointments.*, Customer_Name, Contact_Name FROM appointments, customers, contacts WHERE appointments.Contact_ID = ? and appointments.Customer_ID = customers.Customer_ID and contacts.Contact_ID = ?";
            // prepared statement
            PreparedStatement psabc = DBConnection.getConnection().prepareStatement(sqlabc);
            //question mark
            psabc.setInt(1, contactID);
            psabc.setInt(2, contactID);

            //result set and build list
            ResultSet rs = psabc.executeQuery();

            while (rs.next()) {
                int Appointment_ID = rs.getInt("Appointment_ID");
                String Title = rs.getString("Title");
                String Description = rs.getString("Description");
                String Location = rs.getString("Location");
                String Type = rs.getString("Type");
                Timestamp Start = rs.getTimestamp("Start");
                Timestamp End = rs.getTimestamp("End");
                int Customer_ID = rs.getInt("Customer_ID");
                int User_ID = rs.getInt("User_ID");
                int Contact_ID = rs.getInt("Contact_ID");
                String Customer_Name = rs.getString("Customer_Name");
                String Contact_Name = rs.getString("Contact_Name");
                Appointments A = new Appointments(Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID, Customer_Name, Contact_Name);
                apptsByContact.add(A);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return apptsByContact;
    }

    /**
     * returns the number of appointments by type using a prepared statement.
     * @param type appointment type.
     * @return a count of appointments.
     */
    public static int numberOfApptsByType(String type) {
        int resultAT = 0;
        try {
            String sqlat = "SELECT COUNT(*) FROM appointments WHERE Type = ?";
            PreparedStatement psat = DBConnection.getConnection().prepareStatement(sqlat);
            psat.setString(1, type);
            ResultSet rsat = psat.executeQuery();

            if (rsat.next()) {
                resultAT = rsat.getInt(1);
            } else
                System.out.println("No Appts Found");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultAT;
    }

    /**
     * Takes a selected customer ID and returns a list of appointments for that customer using a prepared statement.
     * @param cusID ID of the selected customer.
     * @return Observable list of appointments.
     */
    public static ObservableList<Appointments> apptsByCusID(int cusID) {

        ObservableList<Appointments> apptsByCus = FXCollections.observableArrayList();
        try {
            String sqlbh = "SELECT Appointment_ID, Title, Description, Location, Type, Start, End, appointments.Customer_ID, User_ID, appointments.Contact_ID, Customer_Name, Contact_Name FROM appointments, customers, contacts where appointments.Customer_ID = ? and appointments.Customer_ID = customers.Customer_ID and appointments.Contact_ID = contacts.Contact_ID";
            PreparedStatement pscl = DBConnection.getConnection().prepareStatement(sqlbh);
            pscl.setInt(1, cusID); // set ?
            ResultSet rscl = pscl.executeQuery();
            while (rscl.next()) {
                int Appointment_ID = rscl.getInt("Appointment_ID");
                String Title = rscl.getString("Title");
                String Description = rscl.getString("Description");
                String Location = rscl.getString("Location");
                String Type = rscl.getString("Type");
                Timestamp Start = rscl.getTimestamp("Start");
                Timestamp End = rscl.getTimestamp("End");
                int Customer_ID = rscl.getInt("Customer_ID");
                int User_ID = rscl.getInt("User_ID");
                int Contact_ID = rscl.getInt("Contact_ID");
                String Customer_Name = rscl.getString("Customer_Name");
                String Contact_Name = rscl.getString("Contact_Name");
                Appointments A = new Appointments(Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID, Customer_Name, Contact_Name);
                apptsByCus.add(A);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return apptsByCus;
    }

    /**
     * Deletes an appointment record from the appointments table using a prepared statement.
     * @param custID selected customer ID
     */
    public static void deleteAllApptsByCustID(int custID) {
        try {
            // sql to delete an appointment
            String sqlad = "DELETE FROM appointments WHERE Customer_ID = ?";

            // create a prepared statement
            PreparedStatement psda = DBConnection.getConnection().prepareStatement(sqlad);

            //question marks
            psda.setInt(1, custID);

            psda.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Accepts a user ID and returns a list of appointments for that user.
     * @param userID ID of the selected user.
     * @return an Observable list of appointments.
     */
    public static ObservableList<Appointments> apptsByUserID(int userID) {

        ObservableList<Appointments> apptsByUser = FXCollections.observableArrayList();
        try {
            String sqlbu = "SELECT Appointment_ID, Title, Description, Location, Type, Start, End, appointments.Customer_ID, appointments.User_ID, appointments.Contact_ID, Customer_Name, Contact_Name FROM appointments, customers, contacts where appointments.User_ID = ? and appointments.Customer_ID = customers.Customer_ID and appointments.Contact_ID = contacts.Contact_ID";
            PreparedStatement pscl = DBConnection.getConnection().prepareStatement(sqlbu);
            pscl.setInt(1, userID); // set ?
            ResultSet rscl = pscl.executeQuery();
            while (rscl.next()) {
                int Appointment_ID = rscl.getInt("Appointment_ID");
                String Title = rscl.getString("Title");
                String Description = rscl.getString("Description");
                String Location = rscl.getString("Location");
                String Type = rscl.getString("Type");
                Timestamp Start = rscl.getTimestamp("Start");
                Timestamp End = rscl.getTimestamp("End");
                int Customer_ID = rscl.getInt("Customer_ID");
                int User_ID = rscl.getInt("User_ID");
                int Contact_ID = rscl.getInt("Contact_ID");
                String Customer_Name = rscl.getString("Customer_Name");
                String Contact_Name = rscl.getString("Contact_Name");
                Appointments A = new Appointments(Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID, Customer_Name, Contact_Name);
                apptsByUser.add(A);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return apptsByUser;

    }
}


