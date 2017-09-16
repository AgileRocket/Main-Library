package rocket.agile.com.mainlibrary.activity;

import android.content.ComponentName;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import rocket.agile.com.mainlibrary.R;
import rocket.agile.com.mainlibrary.fragments.AboutUsFragment;
import rocket.agile.com.mainlibrary.fragments.WebsiteFragment;
import rocket.agile.com.mainlibrary.model.DataManager;
import rocket.agile.com.mainlibrary.model.actionItems.ActionCall;
import rocket.agile.com.mainlibrary.model.actionItems.ActionEmail;
import rocket.agile.com.mainlibrary.model.actionItems.ActionStaff;

/**
 * Created by keithkowalski on 6/19/17.
 *
 * Purpose:  Present side menu layout view to users
 * Function: A) List of action items presented in side menu; graphics and social media presented on home page
 *
 */

public class LayoutView_SideMenu extends LayoutManager
        implements NavigationView.OnNavigationItemSelectedListener {

    // Call singleton class for data manager
    DataManager dataManager = DataManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.side_menu_activity_nav_drawer_main);
        Toolbar primaryHeader = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(primaryHeader);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, primaryHeader, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // CUSTOM PRIMARY SETTINGS
        pullMenuItemsFromNetworkCall();

        // PRIMARY COLOR
        View primaryBackground = findViewById(R.id.id_main);
        primaryBackground.setBackgroundColor(Color.parseColor(dataManager.primaryBackgroundColor));

        // PRIMARY HEADER COLOR AND TITLE
        primaryHeader.setBackgroundColor(Color.parseColor(dataManager.primaryHeaderColor));
        this.setTitle(dataManager.appName);

        if(this.getTitle() == null) {
            finish();
            startActivity(getIntent());
        }
    }

//    Pass each action item's name, font awesome icon, and actionType value (int)
    public void pullMenuItemsFromNetworkCall() {
        // MENU BUTTONS TO CREATE

        // FIRST ATTEMPT TO TRAVERSE ALL CLASSES -----------------------------------------
//        Realm realm = Realm.getDefaultInstance();
//        ArrayList<Object> actionList = new ArrayList<>();
//
//        for(Class actionClass: dataManager.actionClasses) {
//            RealmResults<RealmObject> actionResults = realm.where(actionClass).findAll();
//            Object[] actions = actionResults.toArray();
//            ArrayList<Object> actionsList = new ArrayList(Arrays.asList(actions));
//            actionList.addAll(actionsList);
//
//            Log.d("actionResults", actionResults.toString());
//        }
//
//        Object[] allActions = actionList.toArray();

//        for(Object obj: allActions) {

//            String rawName = obj.getClass().getSimpleName();
//            if(rawName.endsWith("RealmProxy")) {
                // Filter out class name
//                String className = rawName.substring(0, rawName.indexOf("RealmProxy"));
                // Run switch case over substring of raw name
//        ---------------------------------------------------------------------------------

        //TODO: May remove for loops to prevent multiple button creation; pending discussion
        for(Class actionClass: dataManager.actionClasses) {
            switch(actionClass.getSimpleName()) {
                case "ActionEmail":
                    if(dataManager.actionEmail.size() > 0) {
                        for (ActionEmail actionEmail : dataManager.actionEmail) {
                            buildMenu(actionEmail.getName(), "", actionEmail.getActionType());
                        }
                        Log.d(dataManager.SIDE_MENU_TAG, "CREATE ACTION EMAIL BUTTONS");
                    }
                    break;
                case "ActionCall":
                    if(dataManager.actionCall.size() > 0) {
                        for (ActionCall actionCall : dataManager.actionCall) {
                            buildMenu(actionCall.getName(), "", actionCall.getActionType());
                        }
                        Log.d(dataManager.SIDE_MENU_TAG, "CREATE ACTION CALL BUTTONS");
                    }
                    break;
                case "ActionStaff":
                    if(dataManager.actionStaff.size() > 0) {
                        for (ActionStaff actionStaff : dataManager.actionStaff) {
                            buildMenu(actionStaff.getName(), "", actionStaff.getActionType());
                        }
                        Log.d(dataManager.SIDE_MENU_TAG, "CREATE ACTION STAFF BUTTONS");
                    }
                    break;
                default:
                    break;
            }
        }
    }

//    Set each button based on data passed in from list data
    public void buildMenu(String title, String icon, int itemID) {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Menu menu = navigationView.getMenu();
        // TODO: Resolve how to use Font Awesome with 'drawable'
//        menu.add(title).setIcon(R.drawable.ic_menu_manage);
//        menu.add(title);
        menu.add(0,itemID,0,title);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Home Button ID
        int homeID = item.getItemId();
        // All other buttons IDs
        int menuButtonsID = item.getItemId();

        FragmentManager manager = getSupportFragmentManager();
        WebsiteFragment websiteFragment = new WebsiteFragment();
        AboutUsFragment aboutUsFragment = new AboutUsFragment();

        // Home Button
        if (homeID == R.id.nav_home && manager.findFragmentById(R.id.relative_layout_for_fragment) != null) {
            manager.beginTransaction().remove(manager.findFragmentById(R.id.relative_layout_for_fragment)).commit();
        }

        // ** NOTE ** menuButtonsID was created from 'actionType' of each Realm class!
        switch(menuButtonsID) {
            // ActionEmail
            case 0:
                manager.beginTransaction().replace(
                        R.id.relative_layout_for_fragment,
                        aboutUsFragment,
                        aboutUsFragment.getTag()).commit();
                break;
            // ActionCall
            case 2:
                manager.beginTransaction().replace(
                        R.id.relative_layout_for_fragment,
                        websiteFragment,
                        websiteFragment.getTag()).commit();
                break;
            default:
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}