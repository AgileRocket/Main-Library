package rocket.agile.com.mainlibrary;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmConfiguration.Builder;
import io.realm.RealmResults;
import rocket.agile.com.mainlibrary.ActionItems.AboutUs_ActionItem;
import rocket.agile.com.mainlibrary.ActionItems.LayoutValue;

/**
 * Created by keithkowalski on 4/21/17.
 */

public class RealmPersistence extends MasterView {

//    @Override
//    public void onCreate() {
//        super.onCreate();
//
//        // Initialize Realm
//        initRealm();
//
//        updateActionItemValues();
//    }

    // Class updates persistently stored data
    public static void saveLayoutValue() {

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        LayoutValue managedLayoutValue = realm.createObject(LayoutValue.class);
        managedLayoutValue.setLayoutValue(1);

        realm.commitTransaction();
    }


//        realm.executeTransactionAsync(new Realm.Transaction() {
//            @Override
//            public void execute(Realm bgRealm) {
//                LayoutValue layoutvalue = bgRealm.createObject(LayoutValue.class);
//                AboutUs_ActionItem aboutUs = bgRealm.createObject(AboutUs_ActionItem.class);
//
//                // Set Layout Value
//                layoutvalue.setLayoutValue(0);
//
//                // Set AboutUs Values
//                aboutUs.setCompany("COMPANY NAME");
//                aboutUs.setEmail("keith@agilerocket.com");
//                aboutUs.setAboutUsBody("THIS IS THE BODY!");
//            }
//        }, new Realm.Transaction.OnSuccess() {
//            @Override
//            public void onSuccess() {
//                // Transaction was a success.
//            }
//        }, new Realm.Transaction.OnError() {
//            @Override
//            public void onError(Throwable error) {
//                // Transaction failed and was automatically canceled.
//            }
//        });
//
//        realm.commitTransaction();

    public static void initRealm() {
        RealmConfiguration config = new RealmConfiguration.
                Builder().
                deleteRealmIfMigrationNeeded().
                build();
        Realm.setDefaultConfiguration(config);

        saveLayoutValue();
    }
}
