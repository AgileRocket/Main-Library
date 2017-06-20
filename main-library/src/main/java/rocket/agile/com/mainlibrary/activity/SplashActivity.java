package rocket.agile.com.mainlibrary.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by keithkowalski on 6/20/17.
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Start Master Activity
        startActivity(new Intent(SplashActivity.this, MasterView.class));

        // Close Splash Activity
        finish();
    }
}

