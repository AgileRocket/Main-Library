package rocket.agile.com.mainlibrary.realm;

import android.util.Log;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import rocket.agile.com.mainlibrary.activity.MasterView;
import rocket.agile.com.mainlibrary.model.actionItems.ActionList;
import rocket.agile.com.mainlibrary.model.DataManager;
import rocket.agile.com.mainlibrary.model.actionItems.Values;

/**
 * Created by keithkowalski on 4/21/17.
 *
 * Purpose:  Primary class where realm data storage calls are provided
 * Function: A) Responsible for storing data from network values and network action items
 *           B) Provides initial Realm call for application use
 *
 */

public class RealmPersistence extends MasterView {

    // Data Manager Singleton
    static DataManager dataManager = DataManager.getInstance();
    static Realm realm;

    // Initialize Realm
    public static void initRealm() {
        RealmConfiguration config = new RealmConfiguration.
                Builder().
                schemaVersion(1).
                deleteRealmIfMigrationNeeded().
                build();
        Realm.setDefaultConfiguration(config);
    }

    //    Persist Values
    public static void createOrUpdateValues(final Values values) {

        try {
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.insertOrUpdate(values);
                }
            });
            dataManager.getValues();
        } finally {
            if(realm != null) {
                realm.close();
            }
        }
    }

    //    Persist Action Items
    public static void createOrUpdateActionItems(final ActionList actionList) {

        try {
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.insertOrUpdate(actionList);
                }
            });
            dataManager.getActionItems();
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }
}
