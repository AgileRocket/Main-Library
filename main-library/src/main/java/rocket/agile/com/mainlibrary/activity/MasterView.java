package rocket.agile.com.mainlibrary.activity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import io.realm.Realm;
import rocket.agile.com.mainlibrary.R;
import rocket.agile.com.mainlibrary.model.DataManager;
import rocket.agile.com.mainlibrary.model.NetworkingManager;
import rocket.agile.com.mainlibrary.realm.RealmPersistence;

public class MasterView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.master_activity_master_view);
        View pView = findViewById(R.id.p_alert);

        // Realm Initialization
        Realm.init(this);
        RealmPersistence.initRealm();

        // TODO: Check here to see if 1st time app is run
        // Data Manager Singleton
        DataManager dataManager = DataManager.getInstance();

        boolean networkIsAvailable = isNetworkAvailable();  // Check to make sure this works

        // TODO: Networking and saving needs to finish before accessing Realm data
        if(networkIsAvailable) {
            new NetworkingManager(pView.getContext()).execute();
        }

        switch (dataManager.layoutValue) {
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

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}