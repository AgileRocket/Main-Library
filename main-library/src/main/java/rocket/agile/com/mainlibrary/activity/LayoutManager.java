package rocket.agile.com.mainlibrary.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import rocket.agile.com.mainlibrary.model.ApplicationLifeCycleTracker;
import rocket.agile.com.mainlibrary.model.DataManager;
import rocket.agile.com.mainlibrary.networking.NetworkCalls;

/**
 * Created by keithkowalski on 6/19/17.
 *
 * Purpose:  Provide the layout chosen by the user
 * Function: Calls activity layout based on user selection
 *
 */

public class LayoutManager extends AppCompatActivity {

    // Context passed via constructor from calling class
    private static Context context;
    public LayoutManager() {}
    public LayoutManager(Context context) { this.context = context; }

    // Call singleton class for data manager
    DataManager dataManager = DataManager.getInstance();

    // Affects all classes that extend LayoutManager
    @Override
    protected void onResume() {
        super.onResume();

        NetworkCalls networkCalls = new NetworkCalls(this);

        if(networkCalls.isNetworkAvailable()) {
            if(!ApplicationLifeCycleTracker.initialStart) {
                Log.d("LAYOUT ACTIVITY", "RESUME");
               networkCalls.getChangeStateFromNetworkAPI();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        this.finish();
    }

    public static void setLayout(DataManager dataManager) {

        switch (dataManager.layoutValue) {
            case 0:
                context.startActivity(new Intent(context, LayoutView_SideMenu.class));
                break;
            case 1:
                context.startActivity(new Intent(context, LayoutView_TabBar.class));
                break;
            case 2:
                context.startActivity(new Intent(context, LayoutView_Buttons_Grid.class));
                break;
            case 3:
                context.startActivity(new Intent(context, LayoutView_Buttons_Long.class));
                break;
            default: break;
        }
    }
}
