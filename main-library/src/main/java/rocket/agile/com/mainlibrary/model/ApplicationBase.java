package rocket.agile.com.mainlibrary.model;

import io.realm.Realm;
import rocket.agile.com.mainlibrary.realm.RealmPersistence;

/**
 * Created by keithkowalski on 6/26/17.
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
