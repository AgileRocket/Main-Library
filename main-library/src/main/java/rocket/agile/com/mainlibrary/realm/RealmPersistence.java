package rocket.agile.com.mainlibrary.realm;

import java.util.Collection;

import io.realm.Realm;
import io.realm.Realm.Transaction;
import io.realm.RealmConfiguration;
import io.realm.internal.IOException;
import rocket.agile.com.mainlibrary.activity.MasterView;
import rocket.agile.com.mainlibrary.model.ActionList;
import rocket.agile.com.mainlibrary.model.DataManager;
import rocket.agile.com.mainlibrary.model.Values;

/**
 * Created by keithkowalski on 4/21/17.
 */

public class RealmPersistence extends MasterView {

    // Data Manager Singleton
    final static DataManager dataManager = DataManager.getInstance();

    // Initialize Realm
    public static void initRealm() {
        RealmConfiguration config = new RealmConfiguration.
                Builder().
                schemaVersion(1).
                deleteRealmIfMigrationNeeded().
                build();
        Realm.setDefaultConfiguration(config);
    }

    // Save values via JSON asynchronously
    //    Persist Values
    public static void createOrUpdateValues(final Values values) {
        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(new Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(values);
            }
        });
        // Set data in data manager
        dataManager.getValues(realm);
        realm.close();
    }

//    Persist Action Items
    public static void createOrUpdateActionItems(final ActionList actionList) {
        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(new Transaction() {
            @Override
            public void execute(Realm realm) {
                try {
                    realm.insertOrUpdate(actionList);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        // Set data in data manager
        dataManager.getActionItems(realm);
        realm.close();
    }
}
