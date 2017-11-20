package rocket.agile.com.mainlibrary.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import rocket.agile.com.mainlibrary.Adapters.ActionItemAdapterListView;
import rocket.agile.com.mainlibrary.R;
import rocket.agile.com.mainlibrary.model.DataManager;
import rocket.agile.com.mainlibrary.model.DataManagerHelperMethods;

/**
 * Created by keithkowalski on 6/19/17.
 *
 * Purpose:  Present long buttons layout view to users
 * Function: A) List of long buttons is created, based on action items set by admin
 *
 */

public class LayoutView_Buttons_Long extends LayoutManager {

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    private DataManager dataManager = DataManager.getInstance();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buttons_activity_long_buttons);
        DataManagerHelperMethods.buildAvailableActionItemsList();
    }

    // Call update view in onResume, so it is called each time app enters foreground
    @Override
    protected void onResume() {
        super.onResume();
        updateView();
    }

    private void updateView() {
        // Set layout Root View
        ConstraintLayout rootView = findViewById(R.id.button_long_root_view);
        rootView.setBackgroundColor(Color.BLACK);

        // Set layout Title
        TextView appTitle = findViewById(R.id.button_long_title);
        appTitle.setText(dataManager.appName);
        appTitle.setTextColor(Color.TRANSPARENT);
        appTitle.setTextSize(24);
        appTitle.setVisibility(View.INVISIBLE);

        // Set layout Title Image
        ImageView appTitleImageView = findViewById(R.id.button_long_title_view);
        appTitleImageView.setImageResource(R.drawable.agile_rocket_logo);
        appTitleImageView.setVisibility(View.VISIBLE);

        // ListView Settings
        setListView();
    }

    private void setListView() {

        listView = findViewById(R.id.listview);
        // Adapter is responsible for setting buttons in the listView (see ActionItemAdapterListView class)
        listView.setAdapter(new ActionItemAdapterListView(this));

        // TODO: add on click listener
    }

}
