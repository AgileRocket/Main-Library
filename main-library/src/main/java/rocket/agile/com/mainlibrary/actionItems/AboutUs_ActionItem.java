package rocket.agile.com.mainlibrary.actionItems;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by keithkowalski on 4/17/17.
 */

public class AboutUs_ActionItem extends RealmObject {

    @PrimaryKey public int id;
    public String body;

    // Getters
    public int getId() { return id; }
    public String getAboutUsBody() {
        return body;
    }

    // Setters
    public void setId(final int id) { this.id = id; }
    public void setAboutUsBody(final String aboutUsBody) {
        this.body = aboutUsBody;
    }
}
