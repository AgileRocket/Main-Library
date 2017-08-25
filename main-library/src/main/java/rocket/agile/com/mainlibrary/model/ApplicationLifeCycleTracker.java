package rocket.agile.com.mainlibrary.model;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by keithkowalski on 6/26/17.
 *
 * Responsible for tracking Application entering background and foreground
 *
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
            Log.d(DataManager.APP_LIFE_CYCLE_TAG, "Application Status: FOREGROUND");
            Log.d(DataManager.APP_LIFE_CYCLE_TAG, "Initial Start: " + initialStart + "");
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
            Log.d(DataManager.APP_LIFE_CYCLE_TAG, "Application Status: BACKGROUND");
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
