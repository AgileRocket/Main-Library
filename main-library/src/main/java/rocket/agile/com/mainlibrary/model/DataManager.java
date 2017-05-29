package rocket.agile.com.mainlibrary.model;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

//----- Layout Selected --------------

//    LAYOUT THEME
    public int layoutValue = 0;     // TODO: MAKE NETWORK CALL

//----- Primary Values ---------------

    //    APP NAME
    public String appName;

//    PRIMARY HEADER COLOR
    public String primaryHeaderColor = "#d35400";

//    PRIMARY BACKGROUND COLOR
    public String primaryBackgroundColor = "#34495e";

//    ADDRESS
    public String address;

//    EMAIL
    public String email;

//    HOURS
    public String mondayHours;

//    PHONE
    public String phone;


//----- Fragment Data --------------------

//    TYPE
    public int actionType;

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

//----- Values and Action-Items Getter Methods --------

    // Get all values from Realm Persistence
    public void getDataFromRealmPersistence() {

        // Create Realm instance
        Realm realm = Realm.getDefaultInstance();

        // Layout Value
        getValues(realm);
        getActionItems(realm);

        // Close Realm
        realm.close();
    }

    // GET LAYOUT VALUE
    public void getValues(Realm realm) {

        RealmResults<Values> values = realm.where(Values.class).findAll();

        for(Values value: values) {
            appName = value.getAppName();
            address = value.getAddress();
            email = value.getEmail();
            mondayHours = value.getHours().getMonday();
            phone = value.getPhone();
        }
    }

    public void getActionItems(Realm realm) {

        RealmResults<ActionList> actionLists = realm.where(ActionList.class).findAll();

        String temp;

        for(ActionList actionList: actionLists) {
            for(int i = 0; i < actionList.getTotal(); i++) {
                actionType = actionList.getActions().get(i).getActionType();
                aboutUsIcon = actionList.getActions().get(i).getFaIcon();

                temp = actionType + " " + aboutUsIcon + "\n\n";
                Log.d("Actions", temp);
            }
        }
    }
}