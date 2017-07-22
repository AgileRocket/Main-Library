package rocket.agile.com.mainlibrary.model;

import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;
import rocket.agile.com.mainlibrary.model.actionItems.ActionCall;
import rocket.agile.com.mainlibrary.model.actionItems.ActionEmail;
import rocket.agile.com.mainlibrary.model.appInfo.AppInfo;

/**
 * Created by keithkowalski on 7/18/17.
 *
 * The purpose of this class is to provide DataManager variables with methods that retrieve their data from Realm storage
 *
 */

public class DataManagerHelperMethods extends DataManager {

    // GET APP INFO
    public static void getAppInfo() {

        realm = Realm.getDefaultInstance();
        RealmResults<AppInfo> values = realm.where(AppInfo.class).findAll();

        for(AppInfo value: values) {
            appName = value.getAppName();
            address = value.getAddress();
            mondayHours = value.getHours().getMonday();
            tuesdayHours = value.getHours().getTuesday();
            wednesdayHours = value.getHours().getWednesday();
            thursdayHours = value.getHours().getThursday();
            fridayHours = value.getHours().getFriday();
            saturdayHours = value.getHours().getSaturday();
            sundayHours = value.getHours().getSunday();
        }
        realm.close();
    }

    // GET ACTION ITEMS
    public static void getActionEmails() {

        realm = Realm.getDefaultInstance();
        RealmResults<ActionEmail> actionEmails = realm.where(ActionEmail.class).findAll();
        actionEmail = actionEmails;
    }

    public static void getActionCall() {

        realm = Realm.getDefaultInstance();
        RealmResults<ActionCall> actionCalls = realm.where(ActionCall.class).findAll();
        actionCall = actionCalls;
    }
}
