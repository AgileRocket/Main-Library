package rocket.agile.com.mainlibrary.actionItems;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by keithkowalski on 4/29/17.
 */

public class PrimaryBackgroundColor_Value extends RealmObject {

    @PrimaryKey
    public long id;
    public String primaryBGColor;

    public void setId(long id) {
        this.id = id;
    }
    public void setPrimaryBGColor(String primaryBGColor) {
        this.primaryBGColor = primaryBGColor;
    }

    public String getPrimaryBGColor() { return primaryBGColor; }
    public long getId() { return this.id; }
}
