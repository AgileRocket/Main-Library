package rocket.agile.com.mainlibrary.actionItems;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by keithkowalski on 5/2/17.
 */

public class HeaderTitle_Value extends RealmObject {

    @PrimaryKey
    public int id;
    public String title;

    // Getters
    public int getId() { return id; }
    public String getTitle() {
        return title;
    }

    // Setters
    public void setId(final int id) { this.id = id; }
    public void setTitle(final String title) {
        this.title = title;
    }
}
