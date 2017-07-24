package rocket.agile.com.mainlibrary.model;

import android.util.Log;

import io.realm.Realm;
import rocket.agile.com.mainlibrary.realm.RealmPersistence;

/**
 * Created by keithkowalski on 6/26/17.
 *
 * Purpose:  This class is the absolute beginning of the entire app; this will always run first on launch
 * Function: A) Responsible for establishing the life cycle tracker, which assists in telling us when the app is either in the background or foreground
 *           B) Responsible for initiating Realm
 *
 */

public class ApplicationBase extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Register app life cycle tracker class for entire application
        registerActivityLifecycleCallbacks(new ApplicationLifeCycleTracker());

        // Initialize Realm
        Realm.init(this);
        RealmPersistence.initRealm();
    }
}
