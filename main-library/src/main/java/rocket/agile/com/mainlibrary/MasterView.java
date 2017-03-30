package rocket.agile.com.mainlibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import java.io.File;


public class MasterView extends AppCompatActivity {

    Networking networking = Networking.getInstance();

    // TODO: Value needs to be pulled from data fetch / persistence
    int layoutValue = networking.layoutValue;

    boolean fileExists = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.master_activity_master_view);

        PersistentStore persistentStore = new PersistentStore();
        persistentStore.writeToInternalStorage("Test-File", networking.aboutUs_Header);

        fileExists = fileExistance("Test-File");

        if(fileExists) {
            Log.d("EXISTS", "FILE EXISTS");
        } else {
            Log.d("Does NOT Exist", "FILE DOES NOT EXIST");
        }

        switch (layoutValue) {

            case 0:
                startActivity(new Intent(this, NavDrawerMain.class));
                break;
            case 1:
                startActivity(new Intent(this, TabMenuMain.class));
                break;
            case 2:
                startActivity(new Intent(this, GridButtonsActivity.class));
                break;
            case 3:
                startActivity(new Intent(this, LongButtonsActivity.class));
                break;

            default: break;
        }
    }

    public boolean fileExistance(String fname){
        File file = getApplicationContext().getFileStreamPath(fname);
        return file.exists();
    }
}
