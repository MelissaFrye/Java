package Model;

import java.sql.Timestamp;

/**
 * Contains constructor and getters for Appointments object.
 */
public class Appointments {

    private int a_id;
    private String title;
    private String desc;
    private String loc;
    private String type;
    private Timestamp start;
    private Timestamp end;
    private int customerID;
    private int u_id;
    private int contactID;
    private String customerName;
    private String contactName;

    /**
     * Constructor for the Appointment object.
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
     * @param customerName appointment customer name.
     * @param contactName appointment contact name.
     */
    public Appointments(int a_id, String title, String desc, String loc, String type, Timestamp start, Timestamp end, int customerID, int u_id, int contactID, String customerName, String contactName
    ) {
        this.a_id = a_id;
        this.title = title;
        this.desc = desc;
        this.loc = loc;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerID = customerID;
        this.u_id = u_id;
        this.contactID = contactID;
        this.customerName = customerName;
        this.contactName = contactName;
    }

    public Appointments(int a_id, String title, String desc, String loc, String type, Timestamp start, Timestamp end, int customerID, int u_id, int contactID) {
        this.a_id = a_id;
        this.title = title;
        this.desc = desc;
        this.loc = loc;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerID = customerID;
        this.u_id = u_id;
        this.contactID = contactID;
    }

    /**
     * Getter for the appointment ID.
     * @return appointment ID.
     */
    public int getA_id() {
        return a_id;
    }

    /**
     * Getter for the appointment title.
     * @return appointment title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Getter for the appointment description.
     * @return appointment description.
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Getter for the appointment location.
     * @return appointment location.
     */
    public String getLoc() {
        return loc;
    }

    /**
     * Getter for the appointment type.
     * @return appointment type.
     */
    public String getType() {
        return type;
    }

    /**
     * Getter for the appointment start date and time.
     * @return appointment start.
     */
    public Timestamp getStart() {
        return start;
    }

    /**
     * Getter for the appointment end date and time.
     * @return appointment end.
     */
    public Timestamp getEnd() {
        return end;
    }

    /**
     * Getter for the appointment's customer ID.
     * @return appointment's customer ID.
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * Getter for the appointment's user ID.
     * @return appointment's user ID.
     */
    public int getU_id() {
        return u_id;
    }

    /**
     * Getter for the appointment's Contact ID.
     * @return appointment's Contact ID.
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * Getter for the appointment's customer name.
     * @return appointment's customer name.
     */
    public String getCustomerName() {
        return customerName;
    }
    /**
     * Getter for the appointment's Contact name.
     * @return appointment's Contact name.
     */
    public String getContactName() {
        return contactName;
    }

}
