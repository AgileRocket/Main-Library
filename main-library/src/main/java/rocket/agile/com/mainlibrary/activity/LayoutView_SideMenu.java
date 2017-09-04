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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import java.util.ArrayList;
import java.util.Arrays;
import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import rocket.agile.com.mainlibrary.R;
import rocket.agile.com.mainlibrary.fragments.AboutUsFragment;
import rocket.agile.com.mainlibrary.fragments.WebsiteFragment;
import rocket.agile.com.mainlibrary.model.DataManager;

/**
 * Created by keithkowalski on 6/19/17.
 *
 * Purpose:  Present side menu layout view to users
 * Function: A) List of action items presented in side menu; graphics and social media presented on home page
 *
 */

public class LayoutView_SideMenu extends LayoutManager
        implements NavigationView.OnNavigationItemSelectedListener {

    private final int MenuItem_EditId = 1, MenuItem_DeleteId = 0;

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

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // CUSTOM PRIMARY SETTINGS
        createMenuItems();

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
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.add(0, 1, R.id.drawer_layout, "Agile Rocket").setIcon(R.drawable.agile_rocket_logo);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_drawer_main, menu);

//        super.onCreateOptionsMenu(menu);
//
//        menu.add(0, 0, 0, "Option1").setShortcut('3', 'c');
//        menu.add(0, 1, 0, "Option2").setShortcut('3', 'c');
//        menu.add(0, 2, 0, "Option3").setShortcut('4', 's');
//
//        SubMenu sMenu = menu.addSubMenu(0, 3, 0, "SubMenu"); //If you want to add submenu
//        sMenu.add(0, 4, 0, "SubOption1").setShortcut('5', 'z');
//        sMenu.add(0, 5, 0, "SubOption2").setShortcut('5', 'z');

//        if(enableAdd)
//            menu.add(0, MENU_ADD, Menu.NONE, R.string.your-add-text).setIcon(R.drawable.your-add-icon);
//        if(enableList)
//            menu.add(0, MENU_LIST, Menu.NONE, R.string.your-list-text).setIcon(R.drawable.your-list-icon);
//        if(enableRefresh)
//            menu.add(0, MENU_REFRESH, Menu.NONE, R.string.your-refresh-text).setIcon(R.drawable.your-refresh-icon);
//        if(enableLogin)
//            menu.add(0, MENU_LOGIN, Menu.NONE, R.string.your-login-text).setIcon(R.drawable.your-login-icon);
//        return super.onPrepareOptionsMenu(menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
//
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
//
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

    public void createMenuItems() {
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
                        Log.d(dataManager.SIDE_MENU_TAG, "CREATE ACTION EMAIL BUTTON");
                        break;
                    case "ActionCall":
                        Log.d(dataManager.SIDE_MENU_TAG, "CREATE ACTION CALL BUTTON");
                        break;
                    case "ActionStaff":
                        Log.d(dataManager.SIDE_MENU_TAG, "CREATE ACTION STAFF BUTTON");
                        break;
                    default:
                        break;
                }
            }
        }
        realm.close();
    }
}