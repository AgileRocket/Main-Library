package rocket.agile.com.mainlibrary.model;

import android.content.Context;
import android.content.Intent;
import rocket.agile.com.mainlibrary.activity.LayoutView_Buttons_Grid;
import rocket.agile.com.mainlibrary.activity.LayoutView_Buttons_Long;
import rocket.agile.com.mainlibrary.activity.LayoutView_SideMenu;
import rocket.agile.com.mainlibrary.activity.LayoutView_TabBar;

/**
 * Created by keithkowalski on 6/19/17.
 *
 * Purpose:  Provide the layout chosen by the user
 * Function: Calls activity layout based on user selection
 *
 */

public class LayoutManager {

    // Context passed via constructor from calling class
    private static Context context;
    public LayoutManager(Context context) { this.context = context; }

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
