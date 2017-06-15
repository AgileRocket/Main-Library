package rocket.agile.com.mainlibrary.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import io.realm.Realm;
import rocket.agile.com.mainlibrary.R;
import rocket.agile.com.mainlibrary.model.DataManager;
import rocket.agile.com.mainlibrary.model.NetworkingManager;
import rocket.agile.com.mainlibrary.realm.RealmPersistence;

public class MasterView extends AppCompatActivity {

    // Data Manager Singleton
    DataManager dataManager = DataManager.getInstance();
    // Set SharedPreferences variable to check first run for app
    SharedPreferences prefs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Realm.init(this);
        RealmPersistence.initRealm();

        boolean networkIsAvailable = isNetworkAvailable();

        setContentView(R.layout.master_activity_master_view);

        // TODO: Check here to see if 1st time app is run?
        if(networkIsAvailable) {
            networkCall();  // Network call always made to at least get data pull for any changes applied via API
        }
        else if(!networkIsAvailable) {
            Toast.makeText(this, "Network Connection Error",
                    Toast.LENGTH_LONG).show();
        }
    }

//    TODO: Test onResume() for network calls when entering foreground

    @Override
    protected void onStart() {
        super.onStart();

        // Call for values and actions to guarantee update to interface
        dataManager.getValues();
        setLayout();
    }

    private void networkCall() {

     //   boolean networkPullComplete = false;

        try {
           // while(!networkPullComplete) {       // Check if networking thread has completed pulling all data
                try {
                    new NetworkingManager(this).execute().get(10, TimeUnit.SECONDS);
//                    networkPullComplete = new NetworkingManager(this).execute().get(10, TimeUnit.SECONDS);
                } catch (TimeoutException e) {
                    e.printStackTrace();
            //    }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void setLayout() {

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

    private boolean checkFirstRun() {

        final String PREFS_NAME = "MyPrefsFile";
        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean firstRun = true;

        if(prefs.getBoolean("firstrun", true)) {
            prefs.edit().putBoolean("firstrun", false).commit();
        } else {
            firstRun = false;
            return firstRun;
        }
        return firstRun;
    }
}