package rocket.agile.com.mainlibrary.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import rocket.agile.com.mainlibrary.model.ApplicationLifeCycleTracker;
import rocket.agile.com.mainlibrary.model.DataManager;
import rocket.agile.com.mainlibrary.networking.NetworkingManager;

/**
 * Created by keithkowalski on 6/19/17.
 *
 * Purpose:  Initial activity launched (not seen) when app opens
 * Function: A) Initiates networking class based on life cycle state of application
 *           B) Checks for initial network availability and Realm data persistence
 *
 */

public class MasterView extends AppCompatActivity {

    DataManager dataManager = DataManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("MASTER VIEW", "START");
        Log.d("INITIAL START", ApplicationLifeCycleTracker.initialStart + "");

        if(ApplicationLifeCycleTracker.initialStart) {
            startNetworkCall();
        }
    }

    public void startNetworkCall() {

        AlertDialog alertDialog;
        if(isNetworkAvailable()) {
            dataManager.progressDialog = new ProgressDialog(this);
            dataManager.progressDialog.setMessage("Loading data...");
            dataManager.progressDialog.show();
            try {
                new NetworkingManager(this).execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }

//        Realm realm = Realm.getDefaultInstance();
//        if(!realm.isEmpty()) {  // No network, but Realm data is available
//            DataManagerHelperMethods.getAppInfo();
//            new LayoutManager(this).setLayout();
//            realm.close();
//        }
        else {    // No network and no Realm data
            alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Network Error");
            alertDialog.setMessage("Network connection required.");
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int i) {
                    finishAndRemoveTask();
                }
            });
//            realm.close();
            alertDialog.show();
        }
    }

    // Check for network availability
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) this.getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }
}