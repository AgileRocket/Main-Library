package rocket.agile.com.mainlibrary.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joanzapata.iconify.widget.IconButton;

import org.w3c.dom.Text;

import java.util.List;

import rocket.agile.com.mainlibrary.R;
import rocket.agile.com.mainlibrary.model.DataManager;

/**
 * Created by keithkowalski on 6/19/17.
 *
 * Purpose:  Present grid layout view to users
 * Function: A) Grid of buttons is created, based on action items set by admin
 *
 */

public class LayoutView_Buttons_Grid extends LayoutManager {

    private DataManager dataManager = DataManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buttons_activity_grid_buttons);
    }

    // Call update view in onResume, so it is called each time app enters foreground
    @Override
    protected void onResume() {
        super.onResume();
        updateView();
    }

    private void updateView() {
        // Set layout Root View
        ConstraintLayout rootView = (ConstraintLayout) findViewById(R.id.button_grid_root_view);
        rootView.setBackgroundColor(Color.BLACK);

        // Set layout Title
        TextView appTitle = (TextView) findViewById(R.id.button_grid_title);
        appTitle.setText(dataManager.appName);
        appTitle.setTextColor(Color.TRANSPARENT);
        appTitle.setTextSize(24);
        appTitle.setVisibility(View.INVISIBLE);

        // Set layout Title Image
        ImageView appTitleImageView = (ImageView) findViewById(R.id.button_grid_title_view);
        appTitleImageView.setImageResource(R.drawable.agile_rocket_logo);
        appTitleImageView.setVisibility(View.VISIBLE);

        // TEST BUILD BUTTONS

        ConstraintLayout nestedScrollView = (ConstraintLayout) findViewById(R.id.buttons_nested_scroll_view);
        IconButton button1 = new IconButton(this);
        ConstraintSet set = new ConstraintSet();
        Button initalButtonLeft = (Button) findViewById(R.id.button_1);

        nestedScrollView.addView(button1);

        button1.setText("TEST");
        button1.setBackgroundColor(Color.BLUE);

        set.clone(nestedScrollView);
        set.connect(button1.getId(), ConstraintSet.TOP, initalButtonLeft.getId(), ConstraintSet.BOTTOM, 8);
//        set.con //TODO: See if you can set further constraints for each side of button
        set.constrainMaxHeight(button1.getId(), 150);
        set.applyTo(nestedScrollView);

    }
}
