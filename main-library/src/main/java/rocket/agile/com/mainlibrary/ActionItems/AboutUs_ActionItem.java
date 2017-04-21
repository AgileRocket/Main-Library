package rocket.agile.com.mainlibrary.ActionItems;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by keithkowalski on 4/17/17.
 */

public class AboutUs_ActionItem extends RealmObject {

    @PrimaryKey
    public String company;
    public String email;
    public String aboutUsBody;


    // Getters
    public String getCompany() {
        return company;
    }
    public String getEmail() {
        return email;
    }
    public String getAboutUsBody() {
        return aboutUsBody;
    }

    // Setters
    public void setCompany(final String company) {
        this.company = company;
    }
    public void setEmail(final String email) {
        this.email = email;
    }
    public void setAboutUsBody(final String aboutUsBody) {
        this.aboutUsBody = aboutUsBody;
    }
}
