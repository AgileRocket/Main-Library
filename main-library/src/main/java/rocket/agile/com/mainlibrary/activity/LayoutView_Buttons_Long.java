package rocket.agile.com.mainlibrary.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import rocket.agile.com.mainlibrary.Adapters.ActionItemAdapterListView;
import rocket.agile.com.mainlibrary.R;
import rocket.agile.com.mainlibrary.model.DataManager;
import rocket.agile.com.mainlibrary.model.DataManagerHelperMethods;
import rocket.agile.com.mainlibrary.model.actionItems.ActionCall;
import rocket.agile.com.mainlibrary.model.actionItems.ActionEmail;

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
        rootView.setBackgroundColor(Color.LTGRAY);

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
        setListViewAndListenForUserTaps();
    }

    private void setListViewAndListenForUserTaps() {

        final ListView listView = findViewById(R.id.listview);
        // Adapter is responsible for setting buttons in the listView (see ActionItemAdapterListView class)
        listView.setAdapter(new ActionItemAdapterListView(this));

        // Listen for user touches
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String classNameOfItemTapped = null;

                // Filter out class name from provided string name of each available actionItem class
                String rawClassName = listView.getItemAtPosition(position).getClass().getSimpleName();
                if(rawClassName.endsWith("RealmProxy")) {
                    classNameOfItemTapped = rawClassName.substring(0, rawClassName.indexOf("RealmProxy"));
                }
                switch (classNameOfItemTapped) {
                    case "ActionEmail":
                        ActionEmail actionEmail = dataManager.actionEmail.get((int)id);
                        final Intent emailIntent = new Intent(Intent.ACTION_SEND);
                        emailIntent.setType("plain/text");
                        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{actionEmail.getEmailAddress()});
                        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, actionEmail.getSubject());
                        startActivity(emailIntent);
                        break;

                    case "ActionCall":
                        ActionCall actionCall = dataManager.actionCall.get((int)id);
                        int permissionCheck = ContextCompat.checkSelfPermission(LayoutView_Buttons_Long.this, Manifest.permission.CALL_PHONE);
                        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(LayoutView_Buttons_Long.this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
                        }
                        CallUsActivity callUsActivity = new CallUsActivity(actionCall.getName(), LayoutView_Buttons_Long.this);
                        callUsActivity.callAlertDialog();
                        break;

                    default:
                        break;
                }
                Log.d("ID TAPPED",rawClassName);
            }
        });
    }

}
