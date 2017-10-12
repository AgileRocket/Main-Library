package rocket.agile.com.mainlibrary.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import rocket.agile.com.mainlibrary.Adapters.ActionItemAdapter;
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
        ConstraintLayout rootView = findViewById(R.id.button_grid_root_view);
        rootView.setBackgroundColor(Color.BLACK);

        // Set layout Title
        TextView appTitle = findViewById(R.id.button_grid_title);
        appTitle.setText(dataManager.appName);
        appTitle.setTextColor(Color.TRANSPARENT);
        appTitle.setTextSize(24);
        appTitle.setVisibility(View.INVISIBLE);

        // Set layout Title Image
        ImageView appTitleImageView = findViewById(R.id.button_grid_title_view);
        appTitleImageView.setImageResource(R.drawable.agile_rocket_logo);
        appTitleImageView.setVisibility(View.VISIBLE);


//        testBuildButtons1();
        testBuildButtons2();
    }

//    protected void testBuildButtons1() {
//
//        //---- TEST BUILD BUTTONS ---- //
//        // Constraint layout (scroll view)
//        ConstraintLayout nestedScrollView = (ConstraintLayout) findViewById(R.id.nested_scroll_view);
//
//        // Scroll view vertical / horizontal guides
//        Guideline buttonsVerticalGuideline = (Guideline) findViewById(R.id.button_guideline_vertical);
//        Guideline buttonsHorizontalGuideline = (Guideline) findViewById(R.id.button_guideline_horizontal);
//
//        // Dynamic Button
//        IconButton button1 = new IconButton(this);
//        button1.setText("TEST 1");
//        button1.setBackgroundColor(Color.BLUE);
//
//        IconButton button2 = new IconButton(this);
//        button2.setText("TEST 2");
//        button2.setBackgroundColor(Color.BLUE);
//
//        IconButton button3 = new IconButton(this);
//        button3.setText("TEST 3");
//        button3.setBackgroundColor(Color.BLUE);
//
//        IconButton button4 = new IconButton(this);
//        button4.setText("TEST 4");
//        button4.setBackgroundColor(Color.BLUE);
//
//        IconButton button5 = new IconButton(this);
//        button5.setText("TEST 5");
//        button5.setBackgroundColor(Color.BLUE);
//
//        IconButton button6 = new IconButton(this);
//        button6.setText("TEST 6");
//        button6.setBackgroundColor(Color.BLUE);
//
//
//        // Constraint set to be used for dynamic button
//        ConstraintSet set = new ConstraintSet();
//
//        // Set constraints for dynamic button
//        set.clone(nestedScrollView);
//        nestedScrollView.addView(button1);
//        nestedScrollView.addView(button2);
////        nestedScrollView.addView(button3);
////        nestedScrollView.addView(button4);
////        nestedScrollView.addView(button5);
////        nestedScrollView.addView(button6);
//
//
//        set.connect(button1.getId(), ConstraintSet.TOP, nestedScrollView.getId(), ConstraintSet.TOP);
//        set.connect(button1.getId(), ConstraintSet.LEFT, nestedScrollView.getId(), ConstraintSet.LEFT);
//        set.connect(button1.getId(), ConstraintSet.RIGHT, buttonsVerticalGuideline.getId(), ConstraintSet.LEFT, 16);
//        set.connect(button1.getId(), ConstraintSet.BOTTOM, buttonsHorizontalGuideline.getId(), ConstraintSet.BOTTOM, 16);
//
////        set.connect(button2.getId(), ConstraintSet.TOP, nestedScrollView.getId(), ConstraintSet.TOP);
////        set.connect(button2.getId(), ConstraintSet.LEFT, buttonsVerticalGuideline.getId(), ConstraintSet.RIGHT, 16);
////        set.connect(button2.getId(), ConstraintSet.RIGHT, nestedScrollView.getId(), ConstraintSet.RIGHT);
////        set.connect(button2.getId(), ConstraintSet.BOTTOM, buttonsHorizontalGuideline.getId(), ConstraintSet.BOTTOM, 16);
////
////        set.connect(button3.getId(), ConstraintSet.TOP, nestedScrollView.getId(), ConstraintSet.TOP);
////        set.connect(button3.getId(), ConstraintSet.LEFT, nestedScrollView.getId(), ConstraintSet.LEFT);
////        set.connect(button3.getId(), ConstraintSet.RIGHT, buttonsVerticalGuideline.getId(), ConstraintSet.LEFT, 16);
////        set.connect(button3.getId(), ConstraintSet.BOTTOM, buttonsHorizontalGuideline.getId(), ConstraintSet.BOTTOM, 16);
////
////        set.connect(button4.getId(), ConstraintSet.TOP, nestedScrollView.getId(), ConstraintSet.TOP);
////        set.connect(button4.getId(), ConstraintSet.LEFT, nestedScrollView.getId(), ConstraintSet.LEFT);
////        set.connect(button4.getId(), ConstraintSet.RIGHT, buttonsVerticalGuideline.getId(), ConstraintSet.LEFT, 16);
////        set.connect(button4.getId(), ConstraintSet.BOTTOM, buttonsHorizontalGuideline.getId(), ConstraintSet.BOTTOM, 16);
////
////        set.connect(button5.getId(), ConstraintSet.TOP, nestedScrollView.getId(), ConstraintSet.TOP);
////        set.connect(button5.getId(), ConstraintSet.LEFT, nestedScrollView.getId(), ConstraintSet.LEFT);
////        set.connect(button5.getId(), ConstraintSet.RIGHT, buttonsVerticalGuideline.getId(), ConstraintSet.LEFT, 16);
////        set.connect(button5.getId(), ConstraintSet.BOTTOM, buttonsHorizontalGuideline.getId(), ConstraintSet.BOTTOM, 16);
////
////        set.connect(button6.getId(), ConstraintSet.TOP, nestedScrollView.getId(), ConstraintSet.TOP);
////        set.connect(button6.getId(), ConstraintSet.LEFT, nestedScrollView.getId(), ConstraintSet.LEFT);
////        set.connect(button6.getId(), ConstraintSet.RIGHT, buttonsVerticalGuideline.getId(), ConstraintSet.LEFT, 16);
////        set.connect(button6.getId(), ConstraintSet.BOTTOM, buttonsHorizontalGuideline.getId(), ConstraintSet.BOTTOM, 16);
//
//        set.applyTo(nestedScrollView);
//    }

    protected void testBuildButtons2() {

        GridView gridView = findViewById(R.id.gridview);
        gridView.setAdapter(new ActionItemAdapter(this));

//        gridView.setOnClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//                // Do Stuff
//            }
//        });

    }
}
