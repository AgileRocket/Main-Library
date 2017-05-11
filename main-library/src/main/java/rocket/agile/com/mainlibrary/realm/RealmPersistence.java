package rocket.agile.com.mainlibrary.realm;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.internal.IOException;
import rocket.agile.com.mainlibrary.actionItems.AboutUs_ActionItem;
import rocket.agile.com.mainlibrary.actionItems.ActionItems_Values;
import rocket.agile.com.mainlibrary.actionItems.Email_ActionItem;
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

        createOrUpdatePrimaryValuesFromJson();
        createOrUpdateActionItemsFromJson();
    }


    // Save values via JSON asynchronously

//    TODO: Look into bringing JSON in one list, not just individual calls with 1 line of JSON

    //    PRIMARY VALUES
    public static void createOrUpdatePrimaryValuesFromJson() {
        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.createOrUpdateObjectFromJson(ActionItems_Values.class, "{ versionID: 0, " +
                                                                               "layoutValue: 1, " +
                                                                               "headerTitle: \"JSON ARRAY WORKS!\", " +
                                                                               "primaryBGColor: \"#34495e\", " +
                                                                               "primaryHeaderColor: \"#d35400\" }");
            }
        });
    }

    public static void createOrUpdateActionItemsFromJson() {
        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                try {
                    realm.createOrUpdateAllFromJson(AboutUs_ActionItem.class, "[{aboutUsID: 0, aboutUsBody: \"Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                                                                                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation " +
                                                                                "ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse " +
                                                                                "cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt " +
                                                                                "mollit anim id est laborum.\", aboutUsIcon: \"about.png\"}], " +

                                                                              "[{emailID: 0, emailAddress: \"test@gmail.com\", emailIcon: \"email.png\"}]");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                try {
//                    realm.createOrUpdateAllFromJson(Email_ActionItem.class, { [{'aboutUsID: 0, aboutUsBody: \"TEST\", aboutUsIcon: \"about.png\"'}, {'emailID: 0, emailAddress: \"test@gmail.com\", emailIcon: \"email.png\"'}] });
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
    }

//    // ABOUT US
//    public static void createOrUpdateAboutUsFromJson() {
//        Realm realm = Realm.getDefaultInstance();
//
//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                realm.createOrUpdateObjectFromJson(AboutUs_ActionItem.class, "{ body: \"Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
//                        "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation " +
//                        "ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse " +
//                        "cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt " +
//                        "mollit anim id est laborum.\", id: 0 }");
//            }
//        });
//    }
}
