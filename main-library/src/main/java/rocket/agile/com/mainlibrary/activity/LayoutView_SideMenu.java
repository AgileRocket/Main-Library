package rocket.agile.com.mainlibrary.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import rocket.agile.com.mainlibrary.R;
import rocket.agile.com.mainlibrary.fragments.AboutUsFragment;
import rocket.agile.com.mainlibrary.fragments.WebsiteFragment;
import rocket.agile.com.mainlibrary.model.DataManager;

public class LayoutView_SideMenu extends AppCompatActivity
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

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

// CUSTOM PRIMARY SETTINGS

        // PRIMARY COLOR
        View primaryBackground = findViewById(R.id.id_main);
        primaryBackground.setBackgroundColor(Color.parseColor(dataManager.primaryBackgroundColor));

//        // PRIMARY HEADER COLOR AND TITLE
        primaryHeader.setBackgroundColor(Color.parseColor(dataManager.primaryHeaderColor));
        this.setTitle(dataManager.appName);
    }


//    Back Button pressed override is to check for user intent on tapping back button
    private Boolean exit = false;
    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if(exit) {
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

        // TO-DO:  Change from if-else to Switch statement

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