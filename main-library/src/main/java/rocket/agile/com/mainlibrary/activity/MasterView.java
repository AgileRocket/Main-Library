package rocket.agile.com.mainlibrary.activity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import io.realm.Realm;
import rocket.agile.com.mainlibrary.model.DataManager;
import rocket.agile.com.mainlibrary.model.NetworkingManager;
import rocket.agile.com.mainlibrary.realm.RealmPersistence;

public class MasterView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Realm Initialization
        Realm.init(this);
        RealmPersistence.initRealm();

        // TODO: Check here to see if JSON reports any updates or 1st time app is run
        // Networking Singleton
        NetworkingManager networkingManager = NetworkingManager.getInstance();

        boolean networkIsAvailable = isNetworkAvailable();  // Check to make sure this works

        // TODO: Networking and saving needs to finish before accessing Realm data
        if(networkIsAvailable && networkingManager.getChangeState()) {
            networkingManager.getValues();
            networkingManager.getActions();
        }

        // Data Manager Singleton
        DataManager dataManager = DataManager.getInstance();
        // Load data from Realm Storage
        dataManager.getDataFromRealmPersistence();

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