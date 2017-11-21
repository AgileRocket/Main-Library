package rocket.agile.com.mainlibrary.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.joanzapata.iconify.IconDrawable;

import java.lang.reflect.Field;

import rocket.agile.com.mainlibrary.R;
import rocket.agile.com.mainlibrary.model.Custom.BottomNavigationViewHelper;
import rocket.agile.com.mainlibrary.model.actionItems.ActionCall;
import rocket.agile.com.mainlibrary.model.actionItems.ActionEmail;
import rocket.agile.com.mainlibrary.model.actionItems.ActionStaff;

public class LayoutView_BottomNavBar extends LayoutManager {

    private TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_view__bottom_nav_bar);
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

//        navigation.getMenu().add(0, 1, Menu.NONE, "test1").setIcon(R.drawable.ic_menu_camera).setShowAsAction(2);
//        navigation.getMenu().add(0, 2, Menu.NONE, "test2").setIcon(R.drawable.ic_menu_camera).setShowAsAction(2);
//        navigation.getMenu().add(0, 3, Menu.NONE, "test3").setIcon(R.drawable.ic_menu_camera).setShowAsAction(2);
//        navigation.getMenu().add(0, 4, Menu.NONE, "test4").setIcon(R.drawable.ic_menu_camera).setShowAsAction(2);
//        navigation.getMenu().add(0, 5, Menu.NONE, "test5").setIcon(R.drawable.ic_menu_camera).setShowAsAction(2);


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
//            if (id == R.id.navigation_home) {
//                mTextMessage.setText(R.string.title_home);
//                return true;
//            } else if (id == R.id.navigation_dashboard) {
//                mTextMessage.setText(R.string.title_dashboard);
//                return true;
//            } else if (id == R.id.navigation_notifications) {
//                mTextMessage.setText(R.string.title_notifications);
//                return true;
//            }
            return false;
        }
    };
}


