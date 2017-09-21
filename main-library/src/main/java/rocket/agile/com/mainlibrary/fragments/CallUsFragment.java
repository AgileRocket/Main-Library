package rocket.agile.com.mainlibrary.fragments;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import rocket.agile.com.mainlibrary.R;
import rocket.agile.com.mainlibrary.model.DataManager;
import rocket.agile.com.mainlibrary.model.actionItems.ActionCall;

/**
 *
 *  Created by keithkowalski on 9/21/17.
 *
 *  This class is responsible for checking that the user has granted permission to make phone calls
 *  directly from the app, as well as making the phone call itself.
 *
 */
public class CallUsFragment extends DialogFragment {

    private DataManager dataManager = DataManager.getInstance();
    private ActionCall actionCall;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Find which ActionEmail is equivalent to the one tapped
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String actionTitle = bundle.get("title").toString();

            for (ActionCall actionCall : dataManager.actionCall) {
                if (actionCall.getName().contentEquals(actionTitle)) {
                    // Set action email here, based on title
                    this.actionCall = actionCall;
                }
            }
        }

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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
                        FragmentManager manager = getFragmentManager();
                        if (manager.findFragmentById(R.id.relative_layout_for_fragment) != null) {
                            manager.beginTransaction().remove(manager.findFragmentById(R.id.relative_layout_for_fragment)).commit();
                        }
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }


//    PERMISSIONS FOR MAKING PHONE CALLS DIRECTLY FROM APP
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;

    public void makePhoneCall() {

        int permissionCheck = ContextCompat.checkSelfPermission(this.getActivity(), Manifest.permission.CALL_PHONE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this.getActivity(), new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
        } else {
            callPhone();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callPhone();
                }
            }
        }
    }

    private void callPhone() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + actionCall.getNumber()));
        if (ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(intent);
        }
    }
}
