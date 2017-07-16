package rocket.agile.com.mainlibrary.model.actionItems;

import io.realm.RealmList;
import io.realm.RealmObject;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by keithkowalski on 5/20/17.
 *
 * Purpose:  Create the list of 'actions' passed via JSON
 * Function: A) Responsible for storing all provided actions and total count of actions
 *
 */

public class ActionList extends RealmObject {

    @SerializedName("callActions")
    private RealmList<ActionCall> actionCalls;

    @SerializedName("emailActions")
    private RealmList<ActionEmail> actionEmails;


    // GETTERS and SETTERS
    public RealmList<ActionCall> getActionCalls() {
        return actionCalls;
    }
    public void setActionCalls(RealmList<ActionCall> actionCalls) {
        this.actionCalls = actionCalls;
    }

    public RealmList<ActionEmail> getActionEmails() {
        return actionEmails;
    }
    public void setActionEmails(RealmList<ActionEmail> actionEmails) {
        this.actionEmails = actionEmails;
    }
}
