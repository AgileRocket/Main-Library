package rocket.agile.com.mainlibrary.model.actionItems;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import rocket.agile.com.mainlibrary.Interface.ActionModel;

/**
 * Created by keithkowalski on 6/14/17.
 */

public class ActionPhone extends RealmObject implements ActionModel {

    @SerializedName("actionType")
    private int actionType;
    @SerializedName("faIcon")
    private String faIcon;
    @SerializedName("name")
    private String name;
    @SerializedName("number")
    private String number;

    public int getActionType() { return this.actionType; }
    public void setActionType(int actionType) { this.actionType = actionType; }

    public String getFAIcon() { return this.faIcon; }
    public void setFaIcon(String faIcon) { this.faIcon = faIcon; }

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }

    // Required default constructor
    public ActionPhone() {}

    // Custom constructor
    public ActionPhone(int actionType, String faIcon, String name, String number) {
        this.setActionType(actionType);
        this.setFaIcon(faIcon);
        this.setName(name);
        this.setNumber(number);
    }
}
