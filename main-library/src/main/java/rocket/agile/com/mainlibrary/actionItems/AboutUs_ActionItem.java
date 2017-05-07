package rocket.agile.com.mainlibrary.actionItems;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by keithkowalski on 4/17/17.
 */

public class AboutUs_ActionItem extends RealmObject {

    @PrimaryKey public int aboutUsID;
    public String aboutUsBody;
    public String aboutUsIcon;

    // Getters
    public int getAboutUsID() { return aboutUsID; }
    public String getAboutUsBody() {
        return aboutUsBody;
    }
    public String getAboutUsIcon() { return aboutUsIcon; }

    // Setters
    public void setAboutUsID(int aboutUsID) { this.aboutUsID = aboutUsID; }
    public void setAboutUsBody(String aboutUsBody) {
        this.aboutUsBody = aboutUsBody;
    }
    public void setAboutUsIcon(String aboutUsIcon) { this.aboutUsIcon = aboutUsIcon; }
}
