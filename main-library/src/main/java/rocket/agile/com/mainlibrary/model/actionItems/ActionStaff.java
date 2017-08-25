package rocket.agile.com.mainlibrary.model.actionItems;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import rocket.agile.com.mainlibrary.Interface.ActionModel;

/**
 * Created by keithkowalski on 8/25/17.
 */

public class ActionStaff extends RealmObject implements ActionModel {

    @SerializedName("actionType")
    private int actionType;
    @SerializedName("faIcon")
    private String faIcon;
    @SerializedName("name")
    private String name;
    @SerializedName("number")
    private String number;
    @SerializedName("email")
    private String emailAddress;

    @Override
    public void setActionType(int actionType) {
        this.actionType = actionType;
    }

    @Override
    public void setFaIcon(String faIcon) {
        this.faIcon = faIcon;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getActionType() {
        return this.actionType;
    }

    @Override
    public String getFAIcon() {
        return this.faIcon;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
