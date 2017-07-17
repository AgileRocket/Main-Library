package rocket.agile.com.mainlibrary.model;

import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;
import rocket.agile.com.mainlibrary.model.actionItems.ActionEmail;
import rocket.agile.com.mainlibrary.model.appInfo.AppInfo;

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
    Realm realm;

//----- Set base URL -----------------
    public String baseURL = "http://rocketdepot.com/api/";

//----- Current Change State ---------
    public boolean changeStateValue = true;  // TODO: Initialize to false, only network call can set to true
    public String changeStateIDs[];

//----- LayoutManager Selected --------------

//    LAYOUT THEME
    public int layoutValue = 0;     // TODO: MAKE NETWORK CALL

//----- Primary AppInfo ---------------

    //    APP NAME
    public String appName;

//    PRIMARY HEADER COLOR
    public String primaryHeaderColor = "#d35400";

//    PRIMARY BACKGROUND COLOR
    public String primaryBackgroundColor = "#34495e";

//    ADDRESS
    public String address;

//    HOURS
    public String mondayHours;

//    PHONE
    public String phone;


//----- Fragment Data --------------------

//    Email
    public int actionEmailType;
    public String emailFAIcon;
    public String emailName;
    public String emailSubject;
    public String emailAddress;

//    Call Us
    public int actionCallType;
    public String callFAIcon;
    public String callName;
    public String callNumber;

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

//----- AppInfo and Action-Items Getter Methods --------

    // GET LAYOUT VALUES
    public void getValues() {

        realm = Realm.getDefaultInstance();
        RealmResults<AppInfo> values = realm.where(AppInfo.class).findAll();

        for(AppInfo value: values) {
            appName = value.getAppName();
            address = value.getAddress();
            mondayHours = value.getHours().getMonday();
            phone = value.getPhone();
        }
        realm.close();
    }

    // GET ACTION ITEMS
    public void getActionItems() {
        realm = Realm.getDefaultInstance();
        RealmResults<ActionEmail> actionLists = realm.where(ActionEmail.class).findAll();

        for(ActionEmail actionEmail: actionLists) {
            // ACTION CALLS
//            actionCallType = actionList.getActionCalls().first().getActionType();
//            callFAIcon = actionList.getActionCalls().first().getFAIcon();
//            callName = actionList.getActionCalls().first().getName();
//            callNumber = actionList.getActionCalls().first().getNumber();

            // ACTION EMAILS
            actionEmailType = actionEmail.getActionType();
            emailFAIcon = actionEmail.getFAIcon();
            emailName = actionEmail.getName();
            emailAddress = actionEmail.getEmailAddress();
            emailSubject = actionEmail.getSubject();
        }

        realm.close();
    }

    // Log data pulled from Realm persistence
    public void logData(String actionName, String faIcon) {
        Log.d(actionName, faIcon);
    }
}