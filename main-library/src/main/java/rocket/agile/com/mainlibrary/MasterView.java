package rocket.agile.com.mainlibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.realm.Realm;
import io.realm.RealmQuery;
import rocket.agile.com.mainlibrary.ActionItems.LayoutValue;

public class MasterView extends AppCompatActivity {

//    RealmPersistence realmPersistence = RealmPersistence.getInstance();
    private LayoutValue layoutValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Realm.init(this);
        Realm realm = Realm.getDefaultInstance();

        RealmPersistence realmPersistence = new RealmPersistence();

        // TODO: Check here to see if JSON date reports an update signaling changes made

        // If update is true, or first time app is run, execute persistence
        realmPersistence.updateActionItemValues(realm);

        setContentView(R.layout.master_activity_master_view);

        // TODO: Consider storing string value (i.e. 'LayoutValue' here, in some sort of Enum maybe?


//        layoutValue = (LayoutValue) realmPersistence.getRealmValues("LayoutValue");

//        LayoutValue test = new LayoutValue();

//        RealmQuery<LayoutValue> layoutValue = realm.where(LayoutValue.class);

//        RealmQuery<LayoutValue> layoutValue = realm.where(LayoutValue.class).equalTo("layoutValue", test.getLayoutValue());


        switch (0) {
            case 0:
                startActivity(new Intent(this, NavDrawerMain.class));
                break;
            case 1:
                startActivity(new Intent(this, TabMenuMain.class));
                break;
            case 2:
                startActivity(new Intent(this, GridButtonsActivity.class));
                break;
            case 3:
                startActivity(new Intent(this, LongButtonsActivity.class));
                break;

            default: break;
        }
    }
}