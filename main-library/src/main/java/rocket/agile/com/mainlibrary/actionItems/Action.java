package rocket.agile.com.mainlibrary.actionItems;

import com.google.gson.annotations.SerializedName;
import io.realm.RealmObject;

/**
 * Created by keithkowalski on 5/20/17.
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
