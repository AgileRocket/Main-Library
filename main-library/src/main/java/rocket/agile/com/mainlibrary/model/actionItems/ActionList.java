package rocket.agile.com.mainlibrary.model.actionItems;

import io.realm.RealmList;
import io.realm.RealmObject;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by keithkowalski on 5/20/17.
 *
 * Purpose:  Create the list of 'actions' passed via JSON
 * Function: A) Responsible for storing all provided actions and total count of actions
 *
 */

public class ActionList extends RealmObject {

    @SerializedName("actions")
    private RealmList<Action> actions;

    @SerializedName("total")
    private Integer total;

// GETTERS and SETTERS
    public List<Action> getActions() { return actions; }
    public void setActions(RealmList<Action> actions) {
        this.actions = actions;
    }

    public Integer getTotal() {
        return total;
    }
    public void setTotal(Integer total) {
        this.total = total;
    }
}
