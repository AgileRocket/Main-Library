package rocket.agile.com.mainlibrary.model;

import android.support.v7.app.AppCompatActivity;
import io.realm.Realm;
import io.realm.RealmResults;
import rocket.agile.com.mainlibrary.actionItems.AboutUs_ActionItem;
import rocket.agile.com.mainlibrary.actionItems.ActionItems_Values;
import rocket.agile.com.mainlibrary.actionItems.Email_ActionItem;


/**
 * Created by keithkowalski on 3/21/17.
 */

public class DataManager extends AppCompatActivity {

    // Create Singleton
    private static final DataManager ourInstance = new DataManager();
    public static DataManager getInstance() {
        return ourInstance;
    }

    // Create Realm instance
    Realm realm = Realm.getDefaultInstance();

//----- App Data -------------------

//    LAYOUT THEME
    public int layoutValue = 0;     // TODO: MAKE NETWORK CALL

//----- Primary Values ---------------

//    HEADER TITLE
    public String headerTitle = "WORKS! :)";      // TODO: MAKE NETWORK CALL

//    PRIMARY HEADER COLOR
    public String primaryHeaderColor = "#d35400";

//    PRIMARY BACKGROUND COLOR
    public String primaryBackgroundColor = "#34495e";

//    ADDRESS
    public String address;

//    APP NAME
    public String appName;

//    EMAIL
    public String email;

//    HOURS
    public String mondayHours;

//    PHONE
    public String phone;


//----- Fragment Data --------------------

//    ABOUT US
    public String aboutUsBody;
    public String aboutUsIcon;


//----- Available Social Media Data ------

//    BUSINESS WEBSITE
    public String website;

//    FACEBOOK
    public String facebook;

//    TWITTER
    public String twitter;

//    YOUTUBE
    public String youtube;

//    PINTEREST
    public String pinterest;

//    YELP
    public String yelp;

//    GOOGLE+
    public String google;



//----- Values Getter Method --------

    // Get all values from Realm Persistence
    public void getDataFromRealmPersistence() {

        // Layout Value
        getValues();
//        getActionItems();

        // Close Realm
        realm.close();
    }

    // GET LAYOUT VALUE
    public void getValues() {

        RealmResults<Values> values = realm.where(Values.class).findAll();

        for(Values value: values) {
            address = value.getAddress();
            appName = value.getAppName();
            email = value.getEmail();
            mondayHours = value.getHours().getMonday();
            phone = value.getPhone();
        }
    }

    public void getActionItems() {

        RealmResults<AboutUs_ActionItem> aboutUs_actionItems = realm.where(AboutUs_ActionItem.class).findAll();
        for(AboutUs_ActionItem actionItem: aboutUs_actionItems) {
            aboutUsBody = actionItem.getAboutUsBody();
            aboutUsIcon = actionItem.getAboutUsIcon();
        }

//        RealmResults<Email_ActionItem> email_actionItems = realm.where(Email_ActionItem.class).findAll();
//        for(Email_ActionItem actionItem: email_actionItems) {
//            emailAddress = actionItem.getEmailAddress();
//            emailIcon = actionItem.getEmailIcon();
//        }
    }
}