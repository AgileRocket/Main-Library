package rocket.agile.com.mainlibrary;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by keithkowalski on 3/29/17.
 */

public class PersistentStore extends MasterView {

    // Create Singleton
    private static final PersistentStore ourInstance = new PersistentStore();
    public static PersistentStore getInstance() {
        return ourInstance;
    }

    public PersistentStore writeToInternalStorage(String fileName, String string) {

        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Internal check to make sure persistence file is being written
    public void fileExistence(String fname){

        File file = getApplicationContext().getFileStreamPath(fname);

        if(file.exists())
            Log.d("EXISTS", "FILE EXISTS");
        else
            Log.d("Does NOT Exist", "FILE DOES NOT EXIST");
    }
}
