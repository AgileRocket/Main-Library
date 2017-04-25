package rocket.agile.com.mainlibrary;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import rocket.agile.com.mainlibrary.ActionItems.LayoutValue;

/**
 * Created by keithkowalski on 4/21/17.
 */

public class RealmPersistence extends MasterView {

    // Initialize Realm
    public static void initRealm() {
        RealmConfiguration config = new RealmConfiguration.
                Builder().
                schemaVersion(1).
                deleteRealmIfMigrationNeeded().
                build();
        Realm.setDefaultConfiguration(config);

        saveLayoutValue();
    }

    // Initialize Layout Value
    public static void saveLayoutValue() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        try {
            LayoutValue test = realm.where(LayoutValue.class).equalTo("id", LayoutValue.getId()).findFirst();
            test.deleteFromRealm();
        } catch (Exception e){

        }

        LayoutValue managedLayoutValue = realm.createObject(LayoutValue.class);
        managedLayoutValue.setId(0);
        managedLayoutValue.setLayoutValue(1);

        realm.commitTransaction();
        realm.close();
    }
}
