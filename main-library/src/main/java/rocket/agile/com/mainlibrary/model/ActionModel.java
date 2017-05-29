package rocket.agile.com.mainlibrary.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by KeithK on 5/29/17.
 */

public class ActionModel implements RealmModel {

    @PrimaryKey @SerializedName("actionType")
    private int actionType;

    @SerializedName("faIcon")
    private String faIcon;

    @SerializedName("name")
    private String name;

//    GETTERS & SETTERS
    public int getActionType() { return actionType; }
    public void setActionType(int actionType) { this.actionType = actionType; }

    public String getFaIcon() { return faIcon; }
    public void setFaIcon(String faIcon) { this.faIcon = faIcon; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
