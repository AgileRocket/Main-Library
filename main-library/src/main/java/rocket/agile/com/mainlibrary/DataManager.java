package rocket.agile.com.mainlibrary;

import android.util.Log;

import java.io.FileInputStream;

/**
 * Created by keithkowalski on 3/21/17.
 */

public class DataManager extends MasterView {

    // Create Singleton
    private static final DataManager ourInstance = new DataManager();
    public static DataManager getInstance() {
        return ourInstance;
    }

    public int layoutValue = 0;
}