package rocket.agile.com.mainlibrary.realm;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import rocket.agile.com.mainlibrary.actionItems.AboutUs_ActionItem;
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

        // Temporary data saves
        saveLayoutValue();
        createFromJson();
    }


    // Save Layout Value statically
    public static void saveLayoutValue() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        // Create Layout Value
        LayoutValue layoutValue = new LayoutValue();
        layoutValue.setId(0);
        layoutValue.setLayoutValue(1);

        // Overwrite previous persisted object
        realm.copyToRealmOrUpdate(layoutValue);

        realm.commitTransaction();
        realm.close();
    }

    // Save values via JSON
    public static void createFromJson() {
        Realm realm = Realm.getDefaultInstance();

        // About Us
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.createAllFromJson(AboutUs_ActionItem.class, "{ body: \"THIS IS A TEST\", id: 0 }");
            }
        });
    }
}
