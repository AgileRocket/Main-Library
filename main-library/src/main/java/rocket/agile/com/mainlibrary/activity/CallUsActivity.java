package rocket.agile.com.mainlibrary.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import rocket.agile.com.mainlibrary.model.DataManager;
import rocket.agile.com.mainlibrary.model.actionItems.ActionCall;

public class CallUsActivity extends AppCompatActivity {

    private DataManager dataManager = DataManager.getInstance();
    private ActionCall actionCall;
    private String actionTitle;
    private Context context;

    public CallUsActivity(String actionTitle, Context context) {
        this.actionTitle = actionTitle;
        this.context = context;
    }

    public void callAlertDialog() {
        // Find which ActionEmail is equivalent to the one tapped
        for (ActionCall actionCalls : dataManager.actionCall) {
            if (actionCalls.getName().contentEquals(actionTitle)) {
                // Set action email here, based on title
                actionCall = actionCalls;
            }
        }

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage("Do you wish to call " + dataManager.appName + "?")
                .setPositiveButton("Call", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User tapped OK button
                        makePhoneCall();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        builder.create();
        builder.show();
    }

    //    PERMISSIONS FOR MAKING PHONE CALLS DIRECTLY FROM APP
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;

    public void makePhoneCall() {


        int permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE);
        // If permission is not granted, kill call activity
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            finish();
        } else {
            callPhone();
        }

    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callPhone();
                } else {
                    finish();
                }
            }
        }
    }

    private void callPhone() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + actionCall.getNumber()));
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            context.startActivity(intent);
        }
    }
}
