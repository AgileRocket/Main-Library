package rocket.agile.com.mainlibrary.model;

import com.google.gson.annotations.SerializedName;
import io.realm.annotations.PrimaryKey;

/**
 * Created by KeithK on 6/11/17.
 *
 * Responsible for accessing ChangeState value via network call
 * Responsible for providing data to be changed
 *
 */

public class ChangeState {

    @PrimaryKey
    @SerializedName("changeState")
    private boolean changeState;
    private String changeID[];

    //    GETTERS and SETTERS
    public boolean getChangeState() { return changeState; }
    public void setChangeState(boolean changeState) { this.changeState = changeState; }

    public String[] getChangeID() { return changeID; }
    public void setChangeID(String[] changeID) { this.changeID = changeID; }
}
