package rocket.agile.com.mainlibrary.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

import io.realm.Realm;
import rocket.agile.com.mainlibrary.R;
import rocket.agile.com.mainlibrary.model.DataManager;
import rocket.agile.com.mainlibrary.realm.RealmPersistence;

public class MasterView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
//            loadJsonFromStream();
            Log.d("INPUT STREAM: ", loadJsonFromStream().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Realm.init(this);

        // TODO: Check here to see if JSON date reports an update signaling changes made
        // If update is true, or first time app is run, execute persistence
        RealmPersistence.initRealm();

        DataManager dataManager = DataManager.getInstance();
        dataManager.getValuesFromRealmPersistence();

        setContentView(R.layout.master_activity_master_view);

        switch (0) {
            case 0:
                startActivity(new Intent(this, LayoutView_SideMenu.class));
                break;
            case 1:
                startActivity(new Intent(this, LayoutView_TabBar.class));
                break;
            case 2:
                startActivity(new Intent(this, LayoutView_Buttons_Grid.class));
                break;
            case 3:
                startActivity(new Intent(this, LayoutView_Buttons_Long.class));
                break;

            default: break;
        }
    }

    private InputStream loadJsonFromStream() throws IOException {

        return getApplicationContext().getAssets().open("test.json");
    }
}