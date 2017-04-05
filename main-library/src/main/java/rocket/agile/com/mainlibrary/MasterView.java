package rocket.agile.com.mainlibrary;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MasterView extends AppCompatActivity {

    PersistentStore persistentStore = PersistentStore.getInstance();
    DataManager dataManager = DataManager.getInstance();
    Networking networking = Networking.getInstance();

    String testString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.master_activity_master_view);

        // TODO: Networking data needs to be called to be stored to persistence here
        persistentStore.writeToInternalStorage("Test-File", networking.aboutUs_Header);

        // TEST IF FILE EXISTS
        File file = getApplicationContext().getFileStreamPath("Test-File");

        if(file.exists())
            Log.d("EXISTS", "FILE EXISTS");
        else
            Log.d("Does NOT Exist", "FILE DOES NOT EXIST");


        // TODO: DataManager needs to pull from persistent store
//        testString = persistentStore.openFile("Test-File");
//        Log.d("ABOUT-US HEADER: ", testString);


        FileInputStream inputStream;
        int testString;

        try {
            inputStream = openFileInput("Test-File");
            inputStream.read();

//            Log.d("INPUT STREAM", inputStream.read());
            testString = inputStream;
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.d("GOT IT!", testString.toString());


        int layoutValue = dataManager.layoutValue;

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
}