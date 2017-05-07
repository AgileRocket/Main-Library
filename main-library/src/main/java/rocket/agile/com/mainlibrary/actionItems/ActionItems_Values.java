package rocket.agile.com.mainlibrary.actionItems;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by keithkowalski on 5/3/17.
 */

public class ActionItems_Values extends RealmObject {

    //    LAYOUT
    @PrimaryKey
    public int versionID;   // TODO: Change to Date
    public int layoutValue;

    //    PRIMARY VALUES
    public String headerTitle;
    public String primaryBGColor;
    public String primaryHeaderColor;


//----- SETTERS & GETTERS -----

//    Setters
    public void setVersionID(int versionID) {
        this.versionID = versionID;
    }

    public void setLayoutValue(int layoutValue) {
        this.layoutValue = layoutValue;
    }

    public void setHeaderTitle(final String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public void setprimaryHeaderColor(String primaryHeaderColor) {
        this.primaryHeaderColor = primaryHeaderColor;
    }

    public void setPrimaryBGColor(String primaryBGColor) {
        this.primaryBGColor = primaryBGColor;
    }

//    Getters
    public int getVersionID() {
        return this.versionID;
    }

    public int getLayoutValue() {
        return layoutValue;
    }

    public String getHeaderTitle() {
        return headerTitle;
    }

    public String getPrimaryHeaderColor() {
        return primaryHeaderColor;
    }

    public String getPrimaryBGColor() {
        return primaryBGColor;
    }
}

