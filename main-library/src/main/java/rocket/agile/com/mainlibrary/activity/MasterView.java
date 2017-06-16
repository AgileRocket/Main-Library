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

    // Set SharedPreferences variable to check first run for app
    SharedPreferences sharedPreferences = null;

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

    private void networkCall() {

        try {
            new NetworkingManager(this).execute().get(1000, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            e.printStackTrace();
            Toast.makeText(this, "Network Timeout", Toast.LENGTH_LONG).show();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Toast.makeText(this, "Network Interruption", Toast.LENGTH_LONG).show();
        } catch (ExecutionException e) {
            e.printStackTrace();
            Toast.makeText(this, "Network Execution Error", Toast.LENGTH_LONG).show();
        }
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private boolean checkFirstRun() {

        final String PREFS_NAME = "MyPrefsFile";
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean firstRun = true;

        if(sharedPreferences.getBoolean("firstrun", true)) {
            sharedPreferences.edit().putBoolean("firstrun", false).commit();
        } else {
            firstRun = false;
            return firstRun;
        }
        return firstRun;
    }
}