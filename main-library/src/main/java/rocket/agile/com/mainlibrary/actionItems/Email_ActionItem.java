package rocket.agile.com.mainlibrary.actionItems;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by keithkowalski on 5/7/17.
 */

public class Email_ActionItem extends RealmObject {

    @PrimaryKey public int emailID;
    public String emailAddress;
    public String emailIcon;

    // GETTERS
    public int getEmailID() { return emailID; }
    public String getEmailAddress() { return emailAddress; }
    public String getEmailIcon() { return emailIcon; }

    // SETTERS
    public void setEmailID(int emailID) { this.emailID = emailID; }
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }
    public void setEmailIcon(String emailIcon) { this.emailIcon = emailIcon; }
}
