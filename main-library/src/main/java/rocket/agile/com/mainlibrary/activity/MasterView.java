package rocket.agile.com.mainlibrary.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import io.realm.Realm;
import rocket.agile.com.mainlibrary.model.ApplicationLifeCycleTracker;
import rocket.agile.com.mainlibrary.model.DataManager;
import rocket.agile.com.mainlibrary.networking.NetworkCalls;

public class MasterView extends AppCompatActivity {

    protected void onStart() {
        super.onStart();

        Log.d("MASTER VIEW", "START");
        Log.d("INITIAL START", ApplicationLifeCycleTracker.initialStart + "");
        if(ApplicationLifeCycleTracker.initialStart) {
            startNetworkCall();
        }
    }

    public void startNetworkCall() {

        NetworkCalls networkCalls = new NetworkCalls(this);
        AlertDialog alertDialog;
        boolean networkIsAvailable = networkCalls.isNetworkAvailable();

        if(networkIsAvailable) {
            networkCalls.networkCall();  // Network call always made to at least get data pull for any changes applied via API
        } else if(!networkIsAvailable) {
            Realm realm = Realm.getDefaultInstance();
            if(!realm.isEmpty()) {  // No network, but Realm data is available
                DataManager dataManager = DataManager.getInstance();
                dataManager.getValues();
                new LayoutManager(this).setLayout(dataManager);
                realm.close();
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
                realm.close();
                alertDialog.show();
            }
        }
    }
}