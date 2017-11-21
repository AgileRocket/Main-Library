package rocket.agile.com.mainlibrary.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.joanzapata.iconify.IconDrawable;
import rocket.agile.com.mainlibrary.R;
import rocket.agile.com.mainlibrary.model.Custom.BottomNavigationViewHelper;
import rocket.agile.com.mainlibrary.model.actionItems.ActionCall;
import rocket.agile.com.mainlibrary.model.actionItems.ActionEmail;
import rocket.agile.com.mainlibrary.model.actionItems.ActionStaff;

public class LayoutView_BottomNavBar extends LayoutManager {

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_view__bottom_nav_bar);

        // Temporary Logo
        ScrollView scrollView = findViewById(R.id.scrollView);
        scrollView.setBackgroundResource(R.drawable.agile_rocket_logo);

        // Set layout title
        this.setTitle(dataManager.appName);

        updateView();
    }

    private void updateView() {
        /* Min nav items is 2
         * Max nav items is 5
         * Admin will use 'More' ActionItem if ActionItems > 5
         * Database will need to provide 2 lists of ActionItems for this Layout: BottomMenuActionItems
         * and SubMenuActionItems.
         */

        for (Class actionClass : dataManager.actionClasses) {
            switch (actionClass.getSimpleName()) {
                case "ActionEmail":
                    if (dataManager.actionEmail.size() > 0) {
                        for (ActionEmail actionEmail : dataManager.actionEmail) {
                            buildMenu(actionEmail.getName(), actionEmail.getFAIcon(), actionEmail.getActionType());
                        }
                    }
                    break;
                case "ActionCall":
                    if (dataManager.actionCall.size() > 0) {
                        for (ActionCall actionCall : dataManager.actionCall) {
                            buildMenu(actionCall.getName(), actionCall.getFAIcon(), actionCall.getActionType());
                        }
                    }
                    break;
                case "ActionStaff":
                    if (dataManager.actionStaff.size() > 0) {
                        for (ActionStaff actionStaff : dataManager.actionStaff) {
                            buildMenu(actionStaff.getName(), actionStaff.getFAIcon(), actionStaff.getActionType());
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }

    //    Set each button based on data passed in from list data
    public void buildMenu(String title, String icon, int itemID) {
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Add item to menu
        Menu menu = navigation.getMenu();
        menu.add(0, itemID, 0, title).setIcon(new IconDrawable(this, icon));

        // REMEMBER TO RUN THIS LINE AFTER ANY MENU ITEM CHANGES!
        BottomNavigationViewHelper.disableShiftMode(navigation);
    }

    // User tap listener
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int id = item.getItemId();

            // item is MenuItem passed in to this method
            String actionTitle = item.getTitle().toString();

            switch(id) {
                // ActionEmail
                case 0:
                    // Email class
                    ActionEmail actionEmailSelected = null;
                    // Determine which email action item was selected
                    for (ActionEmail actionEmail : dataManager.actionEmail) {
                        if (actionEmail.getName().contentEquals(actionTitle)) {
                            // Set action email here, based on title
                            actionEmailSelected = actionEmail;
                        }
                    }
                    final Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.setType("plain/text");
                    emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{actionEmailSelected.getEmailAddress()});
                    emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, actionEmailSelected.getSubject());
                    startActivity(emailIntent);
                    break;

                // ActionStaff
                case 1:
                    break;
                // ActionCall
                // ActionCall uses an activity view; it is not dependent on data from the layout menu activity and should be independently presented.
                case 2:
                    // Check phone call permissions
                    int permissionCheck = ContextCompat.checkSelfPermission(LayoutView_BottomNavBar.this, Manifest.permission.CALL_PHONE);
                    if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(LayoutView_BottomNavBar.this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
                    }
                    CallUsActivity callUsActivity = new CallUsActivity(actionTitle, LayoutView_BottomNavBar.this);
                    callUsActivity.callAlertDialog();
                    break;
                // Default
                default:
                    break;
            }
            return true;
        }
    };
}


