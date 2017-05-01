package rocket.agile.com.mainlibrary.actionItems;

import io.realm.annotations.PrimaryKey;

/**
 * Created by keithkowalski on 5/1/17.
 */

public class PrimaryHeaderColor_Value {

    @PrimaryKey
    public long id;
    public String primaryHeaderColor;

    public void setId(long id) {
        this.id = id;
    }
    public void setprimaryHeaderColor(String primaryHeaderColor) {
        this.primaryHeaderColor = primaryHeaderColor;
    }

    public long getId() { return this.id; }
    public String getPrimaryHeaderColor() { return primaryHeaderColor; }
}
