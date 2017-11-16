package rocket.agile.com.mainlibrary.activity;

import android.os.Bundle;
import rocket.agile.com.mainlibrary.R;

/**
 * Created by keithkowalski on 6/19/17.
 *
 * Purpose:  Present long buttons layout view to users
 * Function: A) List of long buttons is created, based on action items set by admin
 *
 */

public class LayoutView_Buttons_Long extends LayoutManager {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buttons_activity_long_buttons);
    }
}
