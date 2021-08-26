package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class of appointment types.
 */
    public enum TypeEnum {

        PLANNING_SESSION, DEBRIEFING, DEBUGGING, OTHER;


    /**
     * Gets the list of types to display in a combo box.
     * @return a list of appointment types.
     */
    public static ObservableList<TypeEnum> getAllTypes() {

        ObservableList<TypeEnum> typeList = FXCollections.observableArrayList();

        typeList.add(PLANNING_SESSION);
        typeList.add(DEBRIEFING);
        typeList.add(DEBUGGING);
        typeList.add(OTHER);

        return typeList;

    }


}



