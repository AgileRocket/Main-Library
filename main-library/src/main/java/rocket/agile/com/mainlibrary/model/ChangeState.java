package rocket.agile.com.mainlibrary.model;

import com.google.gson.annotations.SerializedName;
import io.realm.annotations.PrimaryKey;

/**
 * Created by KeithK on 6/11/17.
 */

public class ChangeState {

    @PrimaryKey
    @SerializedName("changeState")
    private boolean changeState;

    //    GETTERS and SETTERS
    public boolean getChangeState() { return changeState; }
    public void setChangeState(boolean changeState) { this.changeState = changeState; }
}
