package rocket.agile.com.mainlibrary.model;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;

import io.realm.Realm;
import io.realm.RealmResults;
import rocket.agile.com.mainlibrary.actionItems.AboutUs_ActionItem;
import rocket.agile.com.mainlibrary.actionItems.HeaderTitle_Value;
import rocket.agile.com.mainlibrary.actionItems.LayoutTheme_Value;
import rocket.agile.com.mainlibrary.actionItems.PrimaryBackgroundColor_Value;
import rocket.agile.com.mainlibrary.actionItems.PrimaryHeaderColor_Value;

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



//----- Action-Items Getter Methods --------

//    DIRECTIONS

//

    // Get all values from Realm Persistence
    public void getValuesFromRealmPersistence() {

        // Layout Value
        getLayoutValue();

        // Header Title
        getHeaderTitle();

        // Primary Colors
        getPrimaryBGColor();
        getPrimaryHeaderColor();

        // About Us
        getAboutUsBody();

        // Close Realm
        realm.close();
    }

    // GET LAYOUT VALUE
    public void getLayoutValue() {

        RealmResults<LayoutTheme_Value> layoutThemeValueResults = realm.where(LayoutTheme_Value.class).findAll();

        for(LayoutTheme_Value value: layoutThemeValueResults) {
//            Log.d("Layout Value Results = ", value.getLayoutValue() + "");
            layoutValue = value.getLayoutValue();
        }
    }

    // GET PRIMARY BACKGROUND COLOR
    public void getHeaderTitle() {

        RealmResults<HeaderTitle_Value> headerTitle_values = realm.where(HeaderTitle_Value.class).findAll();

        for(HeaderTitle_Value value:headerTitle_values) {
            headerTitle = value.getTitle();
        }
    }

    // GET PRIMARY BACKGROUND COLOR
    public void getPrimaryBGColor() {

        RealmResults<PrimaryBackgroundColor_Value> primaryBGColor_valueResults = realm.where(PrimaryBackgroundColor_Value.class).findAll();

        for(PrimaryBackgroundColor_Value value:primaryBGColor_valueResults) {
            primaryBackgroundColor = value.getPrimaryBGColor();
        }
    }

    // GET PRIMARY HEADER COLOR
    public void getPrimaryHeaderColor() {

        RealmResults<PrimaryHeaderColor_Value> primaryHeaderColor_values = realm.where(PrimaryHeaderColor_Value.class).findAll();

        for(PrimaryHeaderColor_Value value:primaryHeaderColor_values) {
            primaryHeaderColor = value.getPrimaryHeaderColor();
        }
    }

    // GET ABOUT US BODY
    public void getAboutUsBody() {

        RealmResults<AboutUs_ActionItem> aboutUs_actionItemResults = realm.where(AboutUs_ActionItem.class).findAll();

        for(AboutUs_ActionItem value:aboutUs_actionItemResults) {
            aboutUsBody = value.getAboutUsBody();
        }
    }
}