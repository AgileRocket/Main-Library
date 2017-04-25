package rocket.agile.com.mainlibrary.ActionItems;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by keithkowalski on 4/21/17.
 */

public class LayoutValue extends RealmObject {

    @PrimaryKey
    public static long id;

    public int layoutValue;

    public void setId(long id) {
        LayoutValue.id = id;
    }
    public void setLayoutValue(int layoutValue) {
        this.layoutValue = layoutValue;
    }


    public int getLayoutValue() { return layoutValue; }
    public static long getId() { return id; }
}
