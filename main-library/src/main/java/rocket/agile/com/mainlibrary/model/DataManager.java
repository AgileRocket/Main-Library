package rocket.agile.com.mainlibrary.model;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by keithkowalski on 3/21/17.
 */

public class DataManager extends AppCompatActivity {

    // Create Singleton
    private static final DataManager ourInstance = new DataManager();
    public static DataManager getInstance() {
        return ourInstance;
    }

    public int layoutValue = 0;
}