package rocket.agile.com.mainlibrary.model;

import android.app.ProgressDialog;

import java.util.ArrayList;
import java.util.List;
import io.realm.RealmResults;
import rocket.agile.com.mainlibrary.activity.LayoutManager;
import rocket.agile.com.mainlibrary.activity.LayoutView_Buttons_Grid;
import rocket.agile.com.mainlibrary.activity.LayoutView_Buttons_Long;
import rocket.agile.com.mainlibrary.activity.LayoutView_SideMenu;
import rocket.agile.com.mainlibrary.activity.LayoutView_TabBar;
import rocket.agile.com.mainlibrary.activity.MasterView;
import rocket.agile.com.mainlibrary.model.actionItems.ActionCall;
import rocket.agile.com.mainlibrary.model.actionItems.ActionEmail;
import rocket.agile.com.mainlibrary.model.actionItems.ActionStaff;
import rocket.agile.com.mainlibrary.networking.NetworkingManagerGetAllData;

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
//    public String baseURL = "http://192.168.7.111:8080/api/";
    public String baseURL = "http://rocketdepot.com/api/";

    //----- Progress Dialog --------------
    public ProgressDialog progressDialog;

    //----- Current Change State ---------
    public static boolean changeStateValue;
    public static List<String> changeStateIDs;

//----- LayoutManager Selected ---------

    //    LAYOUT THEME
    public static int layoutValue = 0;     // TODO: GET FROM NETWORK CALL

//----- Primary AppInfo ----------------

    //    APP NAME
    public static String appName;

    //    PRIMARY HEADER COLOR
    public static String primaryHeaderColor = "#d35400";

    //    PRIMARY BACKGROUND COLOR
    public static String primaryBackgroundColor = "#34495e";

    //    ADDRESS
    public static String mailingAddress;

    //    HOURS
    public static String mondayHours;
    public static String tuesdayHours;
    public static String wednesdayHours;
    public static String thursdayHours;
    public static String fridayHours;
    public static String saturdayHours;
    public static String sundayHours;

//----- Social Media Data ---------------

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

//----- Action Item List -----------------
    public static Class[] actionClasses = {ActionEmail.class, ActionCall.class, ActionStaff.class};  // includes all possible actions, regardless if they have data

//----- Fragment Data --------------------

    //    Email
    public static RealmResults<ActionEmail> actionEmail;

    //    Call Us
    public static RealmResults<ActionCall> actionCall;

    //    Staff
    public static RealmResults<ActionStaff> actionStaff;


//----- DEBUG TAGS --------------------
    public static final String MASTER_VIEW_TAG = MasterView.class.getSimpleName();
    public static final String TAB_BAR_TAG = LayoutView_TabBar.class.getSimpleName();
    public static final String SIDE_MENU_TAG = LayoutView_SideMenu.class.getSimpleName();
    public static final String BUTTONS_LONG_TAG = LayoutView_Buttons_Long.class.getSimpleName();
    public static final String BUTTONS_GRID_TAG = LayoutView_Buttons_Grid.class.getSimpleName();
    public static final String LAYOUT_MANAGER_TAG = LayoutManager.class.getSimpleName();
    public static final String APP_LIFE_CYCLE_TAG = ApplicationLifeCycleTracker.class.getSimpleName();
    public static final String NETWORKING_MANAGER_TAG = NetworkingManagerGetAllData.class.getSimpleName();


}