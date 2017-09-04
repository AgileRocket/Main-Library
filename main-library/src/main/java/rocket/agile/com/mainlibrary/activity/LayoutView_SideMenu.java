package rocket.agile.com.mainlibrary.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import rocket.agile.com.mainlibrary.R;
import rocket.agile.com.mainlibrary.fragments.AboutUsFragment;
import rocket.agile.com.mainlibrary.fragments.WebsiteFragment;
import rocket.agile.com.mainlibrary.model.DataManager;
import rocket.agile.com.mainlibrary.model.DataManagerHelperMethods;
import rocket.agile.com.mainlibrary.model.actionItems.ActionEmail;

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

        // TODO: This is where the list of actionItems is created based on action items that exist (list not empty)
        // Action items are populated via their own classes, but are only called based on what is made available from the menu list we create here (as stated above)

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, primaryHeader, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

// CUSTOM PRIMARY SETTINGS

        // MENU BUTTONS TO CREATE
        Realm realm = Realm.getDefaultInstance();
        ArrayList<Object> actionList = new ArrayList<>();

        for(Class actionClass: dataManager.actionClasses) {
            RealmResults<RealmObject> actionResults = realm.where(actionClass).findAll();

            Object[] actions = actionResults.toArray();
            ArrayList<Object> actionsList = new ArrayList(Arrays.asList(actions));

            actionList.addAll(actionsList);
        }

        Object[] allActions = actionList.toArray();

        for(Object obj: allActions) {
            String rawName = obj.getClass().getSimpleName();
            if(rawName.endsWith("RealmProxy")) {
                // Filter out class name
                String className = rawName.substring(0, rawName.indexOf("RealmProxy"));

                switch(className) {
                    case "ActionEmail":
                        Log.d(dataManager.LAYOUT_MANAGER_TAG, "CREATE ACTION EMAIL BUTTON");
                        break;
                    case "ActionCall":
                        Log.d(dataManager.LAYOUT_MANAGER_TAG, "CREATE ACTION CALL BUTTON");
                        break;
                    case "ActionStaff":
                        Log.d(dataManager.LAYOUT_MANAGER_TAG, "CREATE ACTION STAFF BUTTON");
                        break;
                    default:
                        break;
                }
            }


//            if (obj instanceof ActionEmail) {
//                ActionEmail emailAction = (ActionEmail)obj;
//                Log.d("MY-EMAIL", emailAction.getEmailAddress());
//            }
        }



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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_drawer_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager manager = getSupportFragmentManager();
        WebsiteFragment websiteFragment = new WebsiteFragment();
        AboutUsFragment aboutUsFragment = new AboutUsFragment();

        // TODO:  Change from if-else to Switch statement

        if (id == R.id.nav_website) {
            manager.beginTransaction().replace(
                    R.id.relative_layout_for_fragment,
                    websiteFragment,
                    websiteFragment.getTag()).commit();
        } else if (id == R.id.nav_aboutUs) {
            manager.beginTransaction().replace(
                    R.id.relative_layout_for_fragment,
                    aboutUsFragment,
                    aboutUsFragment.getTag()).commit();
        } else if (id == R.id.nav_home && manager.findFragmentById(R.id.relative_layout_for_fragment) != null) {
            manager.beginTransaction().remove(manager.findFragmentById(R.id.relative_layout_for_fragment)).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}