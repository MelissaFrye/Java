package Model;

/**
 * Contains constructor and getters for Countries object.
 */
public class Countries {
    /**
     * Country ID.
     */
    private int id;
    /**
     * Country Name.
     */
    private String name;

    /**
     * Countries constructor.
     * @param id Country ID.
     * @param name Customer Name.
     */
    public Countries(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Getter for country ID.
     * @return country ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for country name.
     * @return Country name.
     */
    public String getName() {
        return name;
    }

    /**
     * override returns Country name to display in combo box.
     * @return country name.
     */
    @Override
    public String toString() {
        return (name);
    }
}