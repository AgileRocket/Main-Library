package rocket.agile.com.mainlibrary.realm;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import rocket.agile.com.mainlibrary.activity.MasterView;
import rocket.agile.com.mainlibrary.model.Values;

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
    }

    // Save values via JSON asynchronously
    //    PRIMARY VALUES
    public static void createOrUpdateValues(final Values values) {
        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(values);
            }
        });
    }

//    public static void createOrUpdateActionItemsFromJson() {
//        Realm realm = Realm.getDefaultInstance();
//
//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                try {
//                    realm.createOrUpdateAllFromJson(AboutUs_ActionItem.class, "[{aboutUsID: 0, aboutUsBody: \"Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
//                                                                                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation " +
//                                                                                "ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse " +
//                                                                                "cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt " +
//                                                                                "mollit anim id est laborum.\", aboutUsIcon: \"about.png\"}], " +
//
//                                                                              "[{emailID: 0, emailAddress: \"test@gmail.com\", emailIcon: \"email.png\"}]");
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//    }
}
