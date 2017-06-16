package rocket.agile.com.mainlibrary.realm;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import rocket.agile.com.mainlibrary.actionItems.ActionPhone;
import rocket.agile.com.mainlibrary.activity.MasterView;
import rocket.agile.com.mainlibrary.actionItems.ActionEmail;
import rocket.agile.com.mainlibrary.actionItems.ActionList;
import rocket.agile.com.mainlibrary.model.DataManager;
import rocket.agile.com.mainlibrary.model.Values;

/**
 * Created by keithkowalski on 4/21/17.
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

    // Save values via JSON asynchronously
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
                    for (int i = 0; i < actionList.getActions().size(); i++) {
                        switch (actionList.getActions().get(i).getActionType()) {
                            // EMAIL
                            case 0:
                                ActionEmail actionEmail = new ActionEmail(
                                        actionList.getActions().get(i).getActionType(),
                                        actionList.getActions().get(i).getFaIcon(),
                                        actionList.getActions().get(i).getName(),
                                        actionList.getActions().get(i).getEmail(),
                                        actionList.getActions().get(i).getSubject()
                                );
                                realm.insertOrUpdate(actionEmail);
                                break;

                            // PHONE
                            case 2:
                                ActionPhone actionPhone = new ActionPhone(
                                        actionList.getActions().get(i).getActionType(),
                                        actionList.getActions().get(i).getFaIcon(),
                                        actionList.getActions().get(i).getName(),
                                        actionList.getActions().get(i).getNumber()
                                );
                                realm.insertOrUpdate(actionPhone);
                                break;

                            default:
                                break;
                        }
                    }
                }
            });
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }
}
