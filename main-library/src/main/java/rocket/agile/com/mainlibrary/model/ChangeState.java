package rocket.agile.com.mainlibrary.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.annotations.PrimaryKey;

/**
 * Created by KeithK on 6/11/17.
 *
 * Responsible for accessing ChangeState value via network call
 * Responsible for providing data to be changed
 *
 */

public class ChangeState {

    @SerializedName("changeState")
    private boolean changeState;
    @SerializedName("changeStateIDs")
    private List<String> changeIDs;

    //    GETTERS and SETTERS
    public boolean getChangeState() {
        return changeState;
    }
    public void setChangeState(boolean changeState) {
        this.changeState = changeState;
    }


    public List<String> getChangeIDs() {
        return changeIDs;
    }
    public void setChangeIDs(List<String> changeIDs) {
        this.changeIDs = changeIDs;
    }
}
