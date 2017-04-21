package rocket.agile.com.mainlibrary;

import android.support.v7.app.AppCompatActivity;
import io.realm.Realm;
import io.realm.RealmQuery;
import rocket.agile.com.mainlibrary.ActionItems.AboutUs_ActionItem;
import rocket.agile.com.mainlibrary.ActionItems.LayoutValue;

/**
 * Created by keithkowalski on 3/29/17.
 */

public class PersistentStore extends AppCompatActivity {

    // Create Singleton
    private static final PersistentStore ourInstance = new PersistentStore();
    public static PersistentStore getInstance() {
        return ourInstance;
    }

    // Create Realm instance
    public Realm realm = Realm.getDefaultInstance();

    // Class updates persistently stored data
    public void updateActionItemValues() {

        realm.beginTransaction();

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                LayoutValue layoutvalue = bgRealm.createObject(LayoutValue.class);
                AboutUs_ActionItem aboutUs = bgRealm.createObject(AboutUs_ActionItem.class);

                // Set Layout Value
                layoutvalue.setLayoutValue(0);

                // Set AboutUs Values
                aboutUs.setCompany("COMPANY NAME");
                aboutUs.setEmail("keith@agilerocket.com");
                aboutUs.setAboutUsBody("THIS IS THE BODY!");
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                // Transaction was a success.
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                // Transaction failed and was automatically canceled.
            }
        });

        realm.commitTransaction();
    }

    // Class returns object stored in persistent data
    public Object getRealmValues(String className) {

        try {
            Class actionItemValue = Class.forName(className);

            RealmQuery<LayoutValue> query = realm.where(actionItemValue);

            return query.equals(actionItemValue);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
