package rocket.agile.com.mainlibrary.model;

import io.realm.Realm;
import io.realm.RealmResults;
import rocket.agile.com.mainlibrary.model.actionItems.ActionCall;
import rocket.agile.com.mainlibrary.model.actionItems.ActionEmail;
import rocket.agile.com.mainlibrary.model.actionItems.ActionStaff;
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

        Realm realm = Realm.getDefaultInstance();
        RealmResults<AppInfo> values = realm.where(AppInfo.class).findAll();

        for(AppInfo value: values) {
            appName = value.getAppName();
            mailingAddress = value.getAddress();
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

    // SET ALL ACTION ITEMS LIST
    public static void setAllActionItemsList() {
        allActionsList.add("actionEmail");
        allActionsList.add("actionCall");
        allActionsList.add("actionStaff");
    }

    // GET ACTION ITEMS
    public static void getActionEmails() {

        Realm realm = Realm.getDefaultInstance();
        RealmResults<ActionEmail> actionEmails = realm.where(ActionEmail.class).findAll();
        actionEmail = actionEmails;
    }

    public static void getActionCall() {

        Realm realm = Realm.getDefaultInstance();
        RealmResults<ActionCall> actionCalls = realm.where(ActionCall.class).findAll();
        actionCall = actionCalls;
    }

    public static void getActionStaff() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<ActionStaff> actionStaffs = realm.where(ActionStaff.class).findAll();
        actionStaff = actionStaffs;
    }

    // Calls all action items at one time, which are stored in Realm
    public static void getAllActionItemsFromRealm() {
        getActionEmails();
        getActionCall();
        getActionStaff();
    }
}
