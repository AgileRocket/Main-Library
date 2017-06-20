package rocket.agile.com.mainlibrary.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import io.realm.Realm;
import rocket.agile.com.mainlibrary.model.DataManager;
import rocket.agile.com.mainlibrary.model.LayoutManager;
import rocket.agile.com.mainlibrary.model.NetworkingManager;
import rocket.agile.com.mainlibrary.realm.RealmPersistence;

public class MasterView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Realm.init(this);
        RealmPersistence.initRealm();
        AlertDialog alertDialog;
        boolean networkIsAvailable = isNetworkAvailable();

        if(networkIsAvailable) {
            networkCall();  // Network call always made to at least get data pull for any changes applied via API
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

//    TODO: Implement OnResume here and in all activities

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
}