package rocket.agile.com.mainlibrary.model;

import android.support.v7.app.AppCompatActivity;
import io.realm.Realm;
import io.realm.RealmResults;
import rocket.agile.com.mainlibrary.actionItems.ActionItems_Values;


/**
 * Created by keithkowalski on 3/21/17.
 */

public class DataManager extends AppCompatActivity {

    // Create Singleton
    private static final DataManager ourInstance = new DataManager();
    public static DataManager getInstance() {
        return ourInstance;
    }

    Realm realm = Realm.getDefaultInstance();

//----- App Data -------------------

//    LAYOUT THEME
    public int layoutValue;

//----- Main Activity Data ---------------

//    HEADER TITLE
    public String headerTitle;

//    PRIMARY HEADER COLOR
    public String primaryHeaderColor;

//    PRIMARY BACKGROUND COLOR
    public String primaryBackgroundColor;


//----- Fragment Data --------------------

//    EMAIL ADDRESS
    public String emailAddress;

//    PHONE NUMBER
    public int phoneNumber;

//    ABOUT US
    public String aboutUsBody;


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
    public void getValuesFromRealmPersistence() {

        // Layout Value
        getPrimaryValues();

        // Close Realm
        realm.close();
    }

    // GET LAYOUT VALUE
    public void getPrimaryValues() {

        RealmResults<ActionItems_Values> actionItems_values = realm.where(ActionItems_Values.class).findAll();

        for(ActionItems_Values value: actionItems_values) {
            layoutValue = value.getLayoutValue();
            headerTitle = value.getHeaderTitle();
            primaryHeaderColor = value.getPrimaryHeaderColor();
            primaryBackgroundColor = value.getPrimaryBGColor();
        }
    }
}