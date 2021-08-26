package Model;

/**
 * Contains constructor and getters for Divisions object.
 */
public class Divisions {

    /**
     * division ID.
     */
    private int id;
    /**
     * division name.
     */
    private String name;
    /**
     * division's country ID.
     */
    private int country_id;

    /**
     * Divisions object constructor.
     * @param id division ID.
     * @param name division name.
     * @param country_id division's country ID.
     */
    public Divisions(int id, String name, int country_id) {
        this.id = id;
        this.name = name;
        this.country_id = country_id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCountry_id() {
        return country_id;
    }

    /**
     * override returns the division name to display in combo box.
     * @return division name.
     */
    @Override
    public String toString() {
        return (name);
    }
}
