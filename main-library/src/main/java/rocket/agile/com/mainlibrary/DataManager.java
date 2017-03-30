package rocket.agile.com.mainlibrary;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import java.io.FileInputStream;

/**
 * Created by keithkowalski on 3/21/17.
 */

public class DataManager extends AppCompatActivity {
    private static final DataManager ourInstance = new DataManager();

    public static DataManager getInstance() {
        return ourInstance;
    }

    static Context context;

    // Layout Value
    public int layoutValue = 0;
    // About Us Info
    public String aboutUs_Body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
            "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure " +
            "dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non " +
            "proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
    public String aboutUs_Email = "BusinessName@gmail.com";
    public String aboutUs_Header = "FILE NOT FOUND";

    // Persistence Storage Access
    public PersistentStore openFile(String fileName) {

        FileInputStream inputStream;

        try {
            inputStream = context.openFileInput(fileName);
            inputStream.read();

            this.aboutUs_Header = inputStream.toString();

            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}