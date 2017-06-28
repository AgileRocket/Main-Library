package rocket.agile.com.mainlibrary.model.actionItems;

import com.google.gson.annotations.SerializedName;
import io.realm.RealmObject;

/**
 * Created by keithkowalski on 5/20/17.
 *
 * The purpose of this class is to gather a list of ALL possible actions acquired during the network call.
 * This is necessary, since 'ActionList' is based on a JSON data value of 'actions'.
 * In order to place these 'actions' in their appropriate Realm Objects, we must first have access to them all, hence the need for this class.
 *
 * This process likely can be improved.
 *
 */

public class Action extends RealmObject {

    @SerializedName("actionType")
    private int actionType;

    @SerializedName("email")
    private String email;

    @SerializedName("faIcon")
    private String faIcon;

    @SerializedName("name")
    private String name;

    @SerializedName("subject")
    private String subject;

    @SerializedName("number")
    private String number;

    //    GETTERS and SETTERS
    public int getActionType() {
        return actionType;
    }

    public void setActionType(int actionType) {
        this.actionType = actionType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFaIcon() {
        return faIcon;
    }

    public void setFaIcon(String faIcon) {
        this.faIcon = faIcon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
