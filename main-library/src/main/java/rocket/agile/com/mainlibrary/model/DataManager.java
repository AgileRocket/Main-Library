package rocket.agile.com.mainlibrary.model;

import android.app.ProgressDialog;
import io.realm.RealmResults;
import rocket.agile.com.mainlibrary.model.actionItems.ActionCall;
import rocket.agile.com.mainlibrary.model.actionItems.ActionEmail;

/**
 * Created by keithkowalski on 3/21/17.
 *
 * Purpose:  Responsible for storing all data as a Singleton class
 * Function: A) Makes calls to Realm for accessing values and action items
 *
 */

public class DataManager {

    // Create Singleton
    private static final DataManager ourInstance = new DataManager();
    public static DataManager getInstance() {
        return ourInstance;
    }

    //----- Set base URL -----------------
    public String baseURL = "http://rocketdepot.com/api/";

    //----- Progress Dialog --------------
    public ProgressDialog progressDialog;

    //----- Current Change State ---------
    public static boolean changeStateValue;
    public static String changeStateIDs[];

//----- LayoutManager Selected --------------

    //    LAYOUT THEME
    public static int layoutValue = 0;     // TODO: MAKE NETWORK CALL

//----- Primary AppInfo ---------------

    //    APP NAME
    public static String appName;

    //    PRIMARY HEADER COLOR
    public static String primaryHeaderColor = "#d35400";

    //    PRIMARY BACKGROUND COLOR
    public static String primaryBackgroundColor = "#34495e";

    //    ADDRESS
    public static String address;

    //    HOURS
    public static String mondayHours;
    public static String tuesdayHours;
    public static String wednesdayHours;
    public static String thursdayHours;
    public static String fridayHours;
    public static String saturdayHours;
    public static String sundayHours;


//----- Available Social Media Data ------

    //    BUSINESS WEBSITE
    public static String website;

    //    FACEBOOK
    public static String facebook;

    //    TWITTER
    public static String twitter;

    //    YOUTUBE
    public static String youtube;

    //    PINTEREST
    public static String pinterest;

    //    YELP
    public static String yelp;

    //    GOOGLE+
    public static String google;

//----- Fragment Data --------------------

    //    Email
    public static RealmResults<ActionEmail> actionEmail;

    //    Call Us
    public static RealmResults<ActionCall> actionCall;

}