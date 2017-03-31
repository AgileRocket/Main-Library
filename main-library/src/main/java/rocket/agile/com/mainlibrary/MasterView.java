package rocket.agile.com.mainlibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MasterView extends AppCompatActivity {

    PersistentStore persistentStore = PersistentStore.getInstance();
    DataManager dataManager = DataManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.master_activity_master_view);

        // TEST CHECK - no functional intent
        persistentStore.writeToInternalStorage("Test-File", "IT WORKS!");

        // TODO: Networking data needs to be called to be stored here

        // TODO: DataManager needs to pull from persistent store
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