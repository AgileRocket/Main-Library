package rocket.agile.com.mainlibrary.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import io.realm.Realm;
import rocket.agile.com.mainlibrary.R;
import rocket.agile.com.mainlibrary.model.DataManager;
import rocket.agile.com.mainlibrary.model.NetworkingManager;
import rocket.agile.com.mainlibrary.realm.RealmPersistence;

public class MasterView extends AppCompatActivity {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set Progress Dialog Message
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);

        Realm.init(this);

        // TODO: Check here to see if JSON reports any updates
        // Networking call
        NetworkingManager networkingManager = NetworkingManager.getInstance();
        networkingManager.getValues(progressDialog);

        // Realm Persistence
        RealmPersistence.initRealm();

        // Data Manager Update
        DataManager dataManager = DataManager.getInstance();
        dataManager.getDataFromRealmPersistence();

        setContentView(R.layout.master_activity_master_view);

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
}