package rocket.agile.com.mainlibrary.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import io.realm.Realm;
import rocket.agile.com.mainlibrary.model.DataManager;
import rocket.agile.com.mainlibrary.model.LayoutManager;
import rocket.agile.com.mainlibrary.model.NetworkCalls;
import rocket.agile.com.mainlibrary.realm.RealmPersistence;

public class MasterView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Realm.init(this);
        RealmPersistence.initRealm();
    }

    @Override
    protected void onStart() {
        super.onStart();

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