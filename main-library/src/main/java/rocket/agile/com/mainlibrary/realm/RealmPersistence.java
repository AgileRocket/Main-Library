package rocket.agile.com.mainlibrary.realm;

import android.util.Log;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import rocket.agile.com.mainlibrary.activity.MasterView;
import rocket.agile.com.mainlibrary.model.DataManagerHelperMethods;
import rocket.agile.com.mainlibrary.model.actionItems.ActionCall;
import rocket.agile.com.mainlibrary.model.actionItems.ActionEmail;
import rocket.agile.com.mainlibrary.model.appInfo.AppInfo;

/**
 * Created by keithkowalski on 4/21/17.
 *
 * Purpose:  Primary class where realm data storage calls are provided
 * Function: A) Responsible for storing data from network values and network action items
 *           B) Provides initial Realm call for application use
 *
 */

public class RealmPersistence extends MasterView {

    private static Realm realm;

    // Initialize Realm
    public static void initRealm() {
        RealmConfiguration config = new RealmConfiguration.
                Builder().
                schemaVersion(1).
                deleteRealmIfMigrationNeeded().
                build();
        Realm.setDefaultConfiguration(config);
    }

    //    Persist AppInfo
    public static void createOrUpdateAppInfo(final AppInfo appInfo) {

        try {
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.insertOrUpdate(appInfo);
                }
            });
            DataManagerHelperMethods.getAppInfo();
        } finally {
            if(realm != null) {
                realm.close();
            }
        }
    }

    //    Persist Action Items
    public static void createOrUpdateActionItems(final JSONArray jsonArray) {

        try {
            for ( int i=0; i<jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int type = jsonObject.getInt("actionType");
                switch (type) {
                    case 0:
                        ActionEmail actionEmail = new Gson().fromJson(jsonObject.toString(), ActionEmail.class);
                        RealmPersistence.createRealmObject(actionEmail);
                        break;

                    case 1:
                        Log.d("case", "1");
                        break;
                    case 2:
                        ActionCall actionCall = new Gson().fromJson(jsonObject.toString(), ActionCall.class);
                        RealmPersistence.createRealmObject(actionCall);
                        break;
                    case 3:
                        Log.d("case", "3");
                        break;
                    default:
                        Log.d("case", "default");
                        break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static void createRealmObject(final RealmObject realmObject) {
        try {
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.insert(realmObject);
                }
            });
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }
}
