package rocket.agile.com.mainlibrary;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import java.io.FileOutputStream;

/**
 * Created by keithkowalski on 3/29/17.
 */

public class PersistentStore extends AppCompatActivity {

    static Context context;

    public static PersistentStore writeToInternalStorage(String fileName, String string) {

        FileOutputStream outputStream;

        try {
            outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
