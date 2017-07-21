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

    DataManager dataManager = DataManager.getInstance();

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
        dataManager.realm.close();
    }
}
