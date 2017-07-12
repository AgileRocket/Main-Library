package rocket.agile.com.mainlibrary.model.actionItems;

import com.google.gson.annotations.SerializedName;
import io.realm.RealmObject;
import rocket.agile.com.mainlibrary.Interface.ActionModel;

/**
 * Created by KeithK on 5/29/17.
 *
 * Purpose:  Class stores values for email service
 * Function: A) Responsible for providing values to be stored in Realm for email
 *
 *
 */

public class ActionEmail extends RealmObject implements ActionModel {

    @SerializedName("actionType")
    private int actionType;
    @SerializedName("faIcon")
    private String faIcon;
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("subject")
    private String subject;

    public int getActionType() { return this.actionType; }
    public void setActionType(int actionType) { this.actionType = actionType; }

    public String getFAIcon() { return this.faIcon; }
    public void setFaIcon(String faIcon) { this.faIcon = faIcon; }

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    // Required default constructor
    public ActionEmail() {}

    // Custom constructor
    public ActionEmail(int actionType, String faIcon, String name, String email, String subject) {
        this.setActionType(actionType);
        this.setFaIcon(faIcon);
        this.setName(name);
        this.setEmail(email);
        this.setSubject(subject);
    }
}
