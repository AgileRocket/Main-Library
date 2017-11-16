package rocket.agile.com.mainlibrary.model;

import android.os.Bundle;
import android.util.Log;

import java.util.Set;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmModel;
import io.realm.RealmResults;
import rocket.agile.com.mainlibrary.Interface.ActionModel;
import rocket.agile.com.mainlibrary.R;
import rocket.agile.com.mainlibrary.model.Custom.ActionItemData;
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

    // Add custom type to list; purpose of this type is to track appropriate index values.
    // Flat-mapping these values would be ideal, but is only available in the latest Android JDKs
    public static void buildAvailableActionItemsList() {

        ActionItemData actionItemData;

        for (Class actionClass : actionClasses) {
            switch (actionClass.getSimpleName()) {
                case "ActionEmail":
                    for(int i = 0; i < actionEmail.size(); i++) {
                        actionItemData = new ActionItemData(actionEmail.get(i), i);
                        availableActionItems.add(actionItemData);
                    }
                    break;
                case "ActionCall":
                    for(int i = 0; i < actionCall.size(); i++) {
                        actionItemData = new ActionItemData(actionCall.get(i), i);
                        availableActionItems.add(actionItemData);
                    }
                    break;
                case "ActionStaff":
                    for(int i = 0; i < actionStaff.size(); i++) {
                        actionItemData = new ActionItemData(actionStaff.get(i), i);
                        availableActionItems.add(actionItemData);
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
