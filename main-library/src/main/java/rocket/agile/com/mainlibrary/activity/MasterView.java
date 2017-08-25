package rocket.agile.com.mainlibrary.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import io.realm.Realm;
import rocket.agile.com.mainlibrary.model.DataManager;
import rocket.agile.com.mainlibrary.model.DataManagerHelperMethods;
import rocket.agile.com.mainlibrary.networking.NetworkGetChangeState;
import rocket.agile.com.mainlibrary.networking.NetworkingManagerGetAllData;

/**
 * Created by keithkowalski on 6/19/17.
 *
 * Purpose:  Initial activity launched (not seen) when app opens
 * Function: A) Checks for Realm data; if none found, initiates networking calls
 *           B) If Realm data is found, loads it and then checks for changeState value
 *
 */

public class MasterView extends AppCompatActivity {

    private DataManager dataManager = DataManager.getInstance();
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Realm realm = Realm.getDefaultInstance();

        if(realm.isEmpty()) {
            Log.d(dataManager.MASTER_VIEW_TAG, "Realm Empty");
            startInitialNetworkCall();
        } else {
            Log.d(dataManager.MASTER_VIEW_TAG, "Realm Has data");
            getDataFromRealmOnly();

//            checkChangeStateNetworkCall();
            // TODO: If changeState is true, update data provided by ChangeStateIDs list
        }
        realm.close();
    }

    private void startInitialNetworkCall() {

        if(isNetworkAvailable()) {
            dataManager.progressDialog = new ProgressDialog(this);
            dataManager.progressDialog.setMessage("Loading data...");
            dataManager.progressDialog.setTitle("Please wait");
            dataManager.progressDialog.show();
            try {
                new NetworkingManagerGetAllData(this).execute();
            } catch (Exception e) {
                e.printStackTrace();
                Log.d(dataManager.MASTER_VIEW_TAG, "ERROR: Networking Manager Get All Data");
            }
        } else {    // No network and no Realm data
            alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Network Error");
            alertDialog.setMessage("Network connection required.");
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int i) {
                    finishAndRemoveTask();
                }
            });
            alertDialog.show();
        }
    }

    private void getDataFromRealmOnly() {

        Realm realm = Realm.getDefaultInstance();

        DataManagerHelperMethods.getAppInfo();
        DataManagerHelperMethods.getAllActionItemsFromRealm();
        new LayoutManager(this).setLayout();

        realm.close();
    }

    private void checkChangeStateNetworkCall() {

        dataManager.progressDialog = new ProgressDialog(this);
        dataManager.progressDialog.setMessage("Checking for updates...");
        dataManager.progressDialog.setTitle("Please wait");
        dataManager.progressDialog.show();
        try {
            new NetworkGetChangeState(this).execute();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(dataManager.MASTER_VIEW_TAG, "ERROR: Networking Manager Get Change State");
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