package rocket.agile.com.mainlibrary.model;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import io.realm.Realm;
import io.realm.RealmResults;
import rocket.agile.com.mainlibrary.actionItems.AboutUs_ActionItem;
import rocket.agile.com.mainlibrary.actionItems.LayoutValue;

/**
 * Created by keithkowalski on 3/21/17.
 */

public class DataManager extends AppCompatActivity {

    // Create Singleton
    private static final DataManager ourInstance = new DataManager();
    public static DataManager getInstance() {
        return ourInstance;
    }

    Realm realm = Realm.getDefaultInstance();

    // Available Data
    public int layoutValue;
    public String aboutUsBody;


    // Get all values from Realm Persistence
    public void getValuesFromRealmPersistence() {

        getLayoutValue();
        getAboutUsBody();   // JSON Creation

        // Close Realm
        realm.close();
    }

    // LAYOUT VALUE
    public void getLayoutValue() {

        RealmResults<LayoutValue> layoutValueResults = realm.where(LayoutValue.class).findAll();

        for(LayoutValue value:layoutValueResults) {
//            Log.d("Layout Value Results = ", value.getLayoutValue() + "");
            layoutValue = value.getLayoutValue();
        }
    }

    // ABOUT US BODY
    public void getAboutUsBody() {

        RealmResults<AboutUs_ActionItem> aboutUs_actionItemResults = realm.where(AboutUs_ActionItem.class).findAll();

        for(AboutUs_ActionItem value:aboutUs_actionItemResults) {
            aboutUsBody = value.getAboutUsBody();
        }
    }
}