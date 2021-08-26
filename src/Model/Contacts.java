package Model;

/**
 * Contains constructor and getters for Contacts object.
 */
public class Contacts {

    /**
     * Contact ID.
     */
    private int id;
    /**
     * Contact Name.
     */
    private String name;

    /**
     * Contacts constructor.
     * @param id Contact ID.
     * @param name Contact Name.
     */
    public Contacts(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Getter for the Contact ID.
     * @return Contact ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for the Contact Name.
     * @return Contact Name.
     */
    public String getName() {
        return name;
    }


    /**
     * override returns the Contact name to display in combo box.
     * @return contact name.
     */
    @Override
    public String toString() {
        return (name);
    }
}
