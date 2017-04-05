package rocket.agile.com.mainlibrary;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by keithkowalski on 3/29/17.
 */

public class PersistentStore extends Activity {

    // Create Singleton
    private static final PersistentStore ourInstance = new PersistentStore();
    public static PersistentStore getInstance() {
        return ourInstance;
    }

    public PersistentStore writeToInternalStorage(String fileName, String actionType) {

        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(actionType.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Persistence Storage Access
    public String openFile(String fileName) {

        FileInputStream inputStream;
        String testString = "";

            try {
            inputStream = openFileInput(fileName);
            inputStream.read();

            testString = inputStream.toString();

            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return testString;
    }
}
