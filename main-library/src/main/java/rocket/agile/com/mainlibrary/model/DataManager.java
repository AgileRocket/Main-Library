package rocket.agile.com.mainlibrary.model;

import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;
import rocket.agile.com.mainlibrary.actionItems.ActionEmail;
import rocket.agile.com.mainlibrary.actionItems.ActionPhone;

/**
 * Created by keithkowalski on 3/21/17.
 */

public class DataManager {

    // Create Singleton
    private static final DataManager ourInstance = new DataManager();
    public static DataManager getInstance() {
        return ourInstance;
    }

    Realm realm;

//----- Current Change State ---------
    public boolean changeStateValue = true;  // TODO: MAKE NETWORK CALL

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

//    Email
    public int actionEmailType;
    public String emailFAIcon;
    public String emailName;
    public String emailSubject;

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

//----- Values and Action-Items Getter Methods --------

    // GET LAYOUT VALUES
    public void getValues() {

        realm = Realm.getDefaultInstance();
        RealmResults<Values> values = realm.where(Values.class).findAll();

        for(Values value: values) {
            appName = value.getAppName();
            address = value.getAddress();
            mondayHours = value.getHours().getMonday();
            phone = value.getPhone();
        }
        realm.close();
    }

    // GET ACTION ITEMS

    // EMAIL
    public void getEmailAction() {

        realm = Realm.getDefaultInstance();
        RealmResults<ActionEmail> actionEmails = realm.where(ActionEmail.class).findAll();

        actionEmailType = actionEmails.first().getActionType();
        emailFAIcon = actionEmails.first().getFAIcon();
        emailName = actionEmails.first().getName();
        email = actionEmails.first().getEmail();
        emailSubject = actionEmails.first().getSubject();

//        Log.d("ActionEmail Count", actionEmails.size() + "");
//        Log.d("Data Manager\n", "\n" + actionEmails.first().getActionType() + "\n" + actionEmails.first().getFAIcon() + "\n" + actionEmails.first().getName() + "\n" + actionEmails.first().getEmail() + "\n" + actionEmails.first().getSubject());

        realm.close();
    }

    // CALL
    public void getCallAction() {

        realm = Realm.getDefaultInstance();
        RealmResults<ActionPhone> actionPhones = realm.where(ActionPhone.class).findAll();

        actionCallType = actionPhones.first().getActionType();
        callFAIcon = actionPhones.first().getFAIcon();
        callName = actionPhones.first().getName();
        callNumber = actionPhones.first().getNumber();

//        logData(actionPhones + "", callFAIcon);

        realm.close();
    }

    public void logData(String actionName, String faIcon) {
        Log.d(actionName, faIcon);
    }
}