package rocket.agile.com.mainlibrary.ActionItems;

import io.realm.RealmObject;

/**
 * Created by keithkowalski on 4/21/17.
 */

public class LayoutValue extends RealmObject {

    public int layoutValue;

    public int getLayoutValue() {
        return layoutValue;
    }

    public void setLayoutValue(int layoutValue) {
        this.layoutValue = layoutValue;
    }
}
