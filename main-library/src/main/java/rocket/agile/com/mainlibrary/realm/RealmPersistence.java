package rocket.agile.com.mainlibrary.realm;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import rocket.agile.com.mainlibrary.actionItems.AboutUs_ActionItem;
import rocket.agile.com.mainlibrary.actionItems.HeaderTitle_Value;
import rocket.agile.com.mainlibrary.actionItems.LayoutTheme_Value;
import rocket.agile.com.mainlibrary.actionItems.PrimaryBackgroundColor_Value;
import rocket.agile.com.mainlibrary.actionItems.PrimaryHeaderColor_Value;
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
        createOrUpdateHeaderTitle();
        createOrUpdatePrimaryBGColorFromJson();
        createOrUpdatePrimaryHeaderColorFromJson();
        createOrUpdateAboutUsFromJson();
    }

    // Save Layout Value statically
    public static void saveLayoutValue() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        // Create Layout Value
        LayoutTheme_Value layoutThemeValue = new LayoutTheme_Value();
        layoutThemeValue.setId(0);
        layoutThemeValue.setLayoutValue(0);

        // Overwrite previous persisted object
        realm.copyToRealmOrUpdate(layoutThemeValue);

        realm.commitTransaction();
        realm.close();
    }

    // Save values via JSON asynchronously

//    TODO: Look into bringing JSON in one list, not just individual calls with 1 line of JSON

//    PRIMARY BACKGROUND COLOR
    public static void createOrUpdateHeaderTitle() {
        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.createOrUpdateObjectFromJson(HeaderTitle_Value.class, "{ title: \"APP TITLE\", id: 0 }");
            }
        });
    }

//    PRIMARY BACKGROUND COLOR
    public static void createOrUpdatePrimaryBGColorFromJson() {
        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.createOrUpdateObjectFromJson(PrimaryBackgroundColor_Value.class, "{ primaryBGColor: \"#34495e\", id: 0 }");
            }
        });
    }

//    PRIMARY HEADER COLOR
    public static void createOrUpdatePrimaryHeaderColorFromJson() {
        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.createOrUpdateObjectFromJson(PrimaryHeaderColor_Value.class, "{ primaryHeaderColor: \"#d35400\", id: 0 }");
            }
        });
    }

    // ABOUT US
    public static void createOrUpdateAboutUsFromJson() {
        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.createOrUpdateObjectFromJson(AboutUs_ActionItem.class, "{ body: \"Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                        "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation " +
                        "ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse " +
                        "cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt " +
                        "mollit anim id est laborum.\", id: 0 }");
            }
        });
    }
}
