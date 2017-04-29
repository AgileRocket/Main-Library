package rocket.agile.com.mainlibrary.model;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;

import io.realm.Realm;
import io.realm.RealmResults;
import rocket.agile.com.mainlibrary.actionItems.AboutUs_ActionItem;
import rocket.agile.com.mainlibrary.actionItems.LayoutTheme_Value;
import rocket.agile.com.mainlibrary.actionItems.PrimaryBackgroundColor_Value;

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

//----- Available Data ----------

//    LAYOUT THEME
    public int layoutValue;

//    HEADER TITLE
    public String headerTitle;

//    PRIMARY BACKGROUND COLOR
    public String primaryBackgroundColor;

//    PRIMARY HEADER COLOR
    public int primaryHeaderColor;

//    EMAIL ADDRESS
    public String emailAddress;

//    PHONE NUMBER
    public int phoneNumber;

//    ABOUT US
    public String aboutUsBody;


//----- Available Social Media Data ----------

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


//----- Available Action-Items Data ----------

//    DIRECTIONS

//

    // Get all values from Realm Persistence
    public void getValuesFromRealmPersistence() {

        getLayoutValue();
        getPrimaryBGColor();
        getAboutUsBody();   // JSON Creation

        // Close Realm
        realm.close();
    }

    // LAYOUT VALUE
    public void getLayoutValue() {

        RealmResults<LayoutTheme_Value> layoutThemeValueResults = realm.where(LayoutTheme_Value.class).findAll();

        for(LayoutTheme_Value value: layoutThemeValueResults) {
//            Log.d("Layout Value Results = ", value.getLayoutValue() + "");
            layoutValue = value.getLayoutValue();
        }
    }

    // ABOUT US BODY
    public void getPrimaryBGColor() {

        RealmResults<PrimaryBackgroundColor_Value> primaryBGColor_valueResults = realm.where(PrimaryBackgroundColor_Value.class).findAll();

        for(PrimaryBackgroundColor_Value value:primaryBGColor_valueResults) {
            primaryBackgroundColor = value.getPrimaryBGColor();
        }
    }

    // ABOUT US BODY
    public void getAboutUsBody() {

        RealmResults<AboutUs_ActionItem> aboutUs_actionItemResults = realm.where(AboutUs_ActionItem.class).findAll();

        for(AboutUs_ActionItem value:aboutUs_actionItemResults) {
            aboutUsBody = value.getAboutUsBody();
        }
    }
}