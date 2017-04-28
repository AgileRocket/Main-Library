package rocket.agile.com.mainlibrary.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;
import rocket.agile.com.mainlibrary.actionItems.LayoutValue;
import rocket.agile.com.mainlibrary.R;
import rocket.agile.com.mainlibrary.realm.RealmPersistence;

public class MasterView extends AppCompatActivity {

    public int layoutValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Realm.init(this);
        RealmPersistence.initRealm();

        // TODO: Check here to see if JSON date reports an update signaling changes made
        // If update is true, or first time app is run, execute persistence

        setContentView(R.layout.master_activity_master_view);

        // TODO: Consider storing string value (i.e. 'LayoutValue' here, in some sort of Enum maybe?

        layoutValue = getLayoutValue();

        switch (layoutValue) {
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

    public int getLayoutValue() {
        Realm realm = Realm.getDefaultInstance();

        RealmResults<LayoutValue> layoutValueResults = realm.where(LayoutValue.class).findAll();

        for(LayoutValue value:layoutValueResults) {
            Log.d("Layout Value Results = ", value.getLayoutValue() + "");
            layoutValue = value.getLayoutValue();
        }

        realm.close();
        return layoutValue;
    }
}