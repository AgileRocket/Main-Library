package rocket.agile.com.mainlibrary.model;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;;import io.realm.Realm;
import rocket.agile.com.mainlibrary.activity.MasterView;

/**
 * Created by keithkowalski on 6/26/17.
 */

public class ApplicationLifeCycleTracker implements Application.ActivityLifecycleCallbacks {

    private int numStarted = 0;
    public static boolean initialStart = true;

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override
    public void onActivityStarted(Activity activity) {

        if (numStarted == 0) {
            // App went to foreground
            Log.d("Application Status", "FOREGROUND");
            Log.d("Initial Start", initialStart + "");
        }
        numStarted++;
    }

    @Override
    public void onActivityResumed(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivityStopped(Activity activity) {
        numStarted--;
        if(numStarted == 0) {
            // App went to background
            Log.d("Application Status", "BACKGROUND");
            initialStart = false;
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
    }
}
