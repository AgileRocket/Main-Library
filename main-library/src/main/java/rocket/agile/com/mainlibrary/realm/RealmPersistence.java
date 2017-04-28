package rocket.agile.com.mainlibrary.realm;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import rocket.agile.com.mainlibrary.actionItems.LayoutValue;
import rocket.agile.com.mainlibrary.activity.MasterView;

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

        // Create Layout Value
        LayoutValue layoutValue = new LayoutValue();
        layoutValue.setId(0);
        layoutValue.setLayoutValue(0);

        // Overwrite previous persisted object
        realm.copyToRealmOrUpdate(layoutValue);

        realm.commitTransaction();
        realm.close();
    }
}
