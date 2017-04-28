package rocket.agile.com.mainlibrary.actionItems;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by keithkowalski on 4/21/17.
 */

public class LayoutValue extends RealmObject {

    @PrimaryKey public long id;
    public int layoutValue;

    public void setId(long id) {
        this.id = id;
    }
    public void setLayoutValue(int layoutValue) {
        this.layoutValue = layoutValue;
    }

    public int getLayoutValue() { return layoutValue; }
    public long getId() { return this.id; }
}
