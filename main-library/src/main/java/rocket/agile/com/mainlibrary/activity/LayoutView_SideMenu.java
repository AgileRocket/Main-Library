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

        for(Class actionClass: dataManager.actionClasses) {
            switch(actionClass.getSimpleName()) {
                case "ActionEmail":
                    for(ActionEmail actionEmail: dataManager.actionEmail) {
                        buildMenu(actionEmail.getName(), "");
                    }
                    Log.d(dataManager.SIDE_MENU_TAG, "CREATE ACTION EMAIL BUTTONS");
                    break;
                case "ActionCall":
                    for(ActionCall actionCall: dataManager.actionCall) {
                        buildMenu(actionCall.getName(), "");
                    }
                    Log.d(dataManager.SIDE_MENU_TAG, "CREATE ACTION CALL BUTTONS");
                    break;
                case "ActionStaff":
                    for(ActionStaff actionStaff: dataManager.actionStaff) {
                        buildMenu(actionStaff.getName(), "");
                    }
                    Log.d(dataManager.SIDE_MENU_TAG, "CREATE ACTION STAFF BUTTONS");
                    break;
                default:
                    break;
            }
        }
    }

    public void buildMenu(String title, String icon) {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Menu menu = navigationView.getMenu();
        // TODO: Resolve how to use Font Awesome with 'drawable'
//        menu.add(title).setIcon(R.drawable.ic_menu_manage);
        menu.add(title);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        String id2 = (String)item.getTitle();

        FragmentManager manager = getSupportFragmentManager();
        WebsiteFragment websiteFragment = new WebsiteFragment();
        AboutUsFragment aboutUsFragment = new AboutUsFragment();

        if (id == R.id.nav_home && manager.findFragmentById(R.id.relative_layout_for_fragment) != null) {
            manager.beginTransaction().remove(manager.findFragmentById(R.id.relative_layout_for_fragment)).commit();
        }

        // TODO: See if id2 can reflect list of realm classes with data (data manager?)
        switch(id2) {
            case "Contact Us":
                manager.beginTransaction().replace(
                        R.id.relative_layout_for_fragment,
                        aboutUsFragment,
                        aboutUsFragment.getTag()).commit();
            default: break;
        }

//        if (id == R.id.nav_website) {
//            manager.beginTransaction().replace(
//                    R.id.relative_layout_for_fragment,
//                    websiteFragment,
//                    websiteFragment.getTag()).commit();
//        } else if (id2 == "Agile Rocket") {
//            manager.beginTransaction().replace(
//                    R.id.relative_layout_for_fragment,
//                    aboutUsFragment,
//                    aboutUsFragment.getTag()).commit();
//        } else if (id == R.id.nav_home && manager.findFragmentById(R.id.relative_layout_for_fragment) != null) {
//            manager.beginTransaction().remove(manager.findFragmentById(R.id.relative_layout_for_fragment)).commit();
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}