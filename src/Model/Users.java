package Model;

/**
 * Contains constructor and getters for Users object.
 */
public class Users {

    /**
     * user ID.
     */
    private int id;
    /**
     * user name.
     */
    private String name;
    /**
     * count of appointments this user has.
     */
    private int count;

    /**
     * User object constructor.
     * @param id user ID.
     * @param name user name.
     */
    public Users(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * User object constructor.
     * @param name user ID.
     * @param count count of appointments this user has.
     */
    public Users(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    /**
     * override returns the user name to display in combo box.
     * @return user name.
     */
    @Override
    public String toString() {
        return (name + " " + id);
    }

}
