package rocket.agile.com.mainlibrary.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import rocket.agile.com.mainlibrary.R;
import rocket.agile.com.mainlibrary.model.ApplicationLifeCycleTracker;
import rocket.agile.com.mainlibrary.model.DataManager;
import rocket.agile.com.mainlibrary.model.actionItems.ActionEmail;

/**
 * Created by keithkowalski on 6/19/17.
 * <p>
 * Purpose:  Provide the layout chosen by the user
 * Function: A) Calls activity layout based on admin settings
 * B) Base activity for all layouts (each layout extends LayoutManager) to access 'onResume', which applies to all
 */

public class LayoutManager extends AppCompatActivity {

    // Context passed via constructor from calling class
    private static Context context;

    public LayoutManager() {
    }

    public LayoutManager(Context context) {
        this.context = context;
    }

    // Call dataManager singleton
    DataManager dataManager = DataManager.getInstance();

    // Affects all classes that extend LayoutManager
    @Override
    protected void onResume() {
        super.onResume();

        Log.d(dataManager.LAYOUT_MANAGER_TAG, "LifeCycle_OnResume()");

        // TODO: Handle resuming foreground here, check for ChangeState data
//        if(networkCalls.isNetworkAvailable()) {
//            if(!ApplicationLifeCycleTracker.initialStart) {     // Check that this is NOT the initial start of the app
//               networkCalls.getChangeStateFromNetworkAPI();

        // Test changeStateIDs exist
//                String changeStateIDs = dataManager.changeStateIDs[0] + dataManager.changeStateIDs[1] + dataManager.changeStateIDs[2];
//                Log.d("Change State IDs", changeStateIDs);
//            }
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.finish();
    }

    public void setLayout() {

        Log.d(dataManager.LAYOUT_MANAGER_TAG, "METHOD: setLayout()");

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
            default:
                break;
        }
    }

    // Back Button pressed override is to check for user intent on tapping back button, applies to all subclasses
    @Override
    public void onBackPressed() {

        FragmentManager manager = getSupportFragmentManager();

        if (dataManager.layoutValue == 0) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else if (manager.findFragmentById(R.id.relative_layout_for_fragment) != null) {
                manager.beginTransaction().remove(manager.findFragmentById(R.id.relative_layout_for_fragment)).commit();
            }
                exit();
        } else {
            exit();
        }
    }

    // Exit method for non-active-side-menu
    private Boolean exit = false;

    private void exit() {
        if (exit) {
            moveTaskToBack(true); // finish activity
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);
        }
    }
}