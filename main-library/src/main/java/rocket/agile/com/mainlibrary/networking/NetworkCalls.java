package rocket.agile.com.mainlibrary.networking;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by keithkowalski on 6/22/17.
 *
 * Purpose:  Serves as the repeatedly used calls for networking purposes
 * Function: A) Responsible for providing a centralized class where foundational network calls can be made from
 *
 */

//public class NetworkCalls {

    // Context passed via constructor from calling class
//    private static Context context;
//    public NetworkCalls(Context context) { this.context = context; }
//
//    private NetworkGetChangeState networkGetChangeState;

    // Update to all values and/or action items
//    public void networkCall() {
//
//        try {
//            new NetworkingManager(context).execute();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    // Check for any changes made from backend
//    public void getChangeStateFromNetworkAPI() {
//
//        networkGetChangeState = new NetworkGetChangeState(context);
//
//        try {
//           networkGetChangeState.execute().get(5000, TimeUnit.MILLISECONDS);
//        } catch (TimeoutException e) {
//            e.printStackTrace();
//            Toast.makeText(context, "Network Timeout", Toast.LENGTH_LONG).show();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            Toast.makeText(context, "Network Interruption", Toast.LENGTH_LONG).show();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//            Toast.makeText(context, "Network Execution Error", Toast.LENGTH_LONG).show();
//        }
//    }

//    // Check for network availability
//    public boolean isNetworkAvailable() {
//        ConnectivityManager connectivityManager
//                = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
//
//        return networkInfo != null && networkInfo.isConnected();
//    }
//}
