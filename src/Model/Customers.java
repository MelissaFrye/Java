package Model;

/**
 * Contains constructor and getters for Customers object.
 */
public class Customers {

    private int id;
    private String name;
    private String address;
    private String postal;
    private String phone;
    private int firstLvl;
    private String division;
    private String country;
    private int country_id;

    /**
     * Constructor for the Customer object.
     * @param id Customers ID.
     * @param name Customers name.
     */
    public Customers(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Constructor for the Customer object.
     * @param id Customers ID.
     * @param name Customers name.
     * @param address customer address.
     * @param postal customer postal code.
     * @param phone customer phone number.
     * @param firstLvl customer division ID.
     * @param division customer division name.
     * @param country_id customer country ID.
     * @param country customer country name.
     */
    public Customers(int id, String name, String address, String postal, String phone, int firstLvl, String division, int country_id, String country) {

        this.id = id;
        this.name = name;
        this.address = address;
        this.postal = postal;
        this.phone = phone;
        this.firstLvl = firstLvl;
        this.division = division;
        this.country = country;
        this.country_id = country_id;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPostal() {
        return postal;
    }

    public String getPhone() {
        return phone;
    }

    public int getFirstLvl() {
        return firstLvl;
    }

    public String getDivision() {
        return division;
    }

    public String getCountry() {
        return country;
    }

    public int getCountry_id() {
        return country_id;
    }

    /**
     * override returns the customer name to display in combo box.
     * @return customer name.
     */
    @Override
    public String toString() {
        return (name + " " + id);
    }

}
