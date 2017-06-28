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
 */

public class NetworkCalls {

    // Context passed via constructor from calling class
    private static Context context;
    public NetworkCalls(Context context) { this.context = context; }
    private NetworkingManager networkingManager;

    public void networkCall() {

        networkingManager = new NetworkingManager(context);

        try {
            networkingManager.execute().get(1000, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            e.printStackTrace();
            Toast.makeText(context, "Network Timeout", Toast.LENGTH_LONG).show();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Toast.makeText(context, "Network Interruption", Toast.LENGTH_LONG).show();
        } catch (ExecutionException e) {
            e.printStackTrace();
            Toast.makeText(context, "Network Execution Error", Toast.LENGTH_LONG).show();
        }
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void getChangeStateFromNetworkAPI() {

        networkingManager = new NetworkingManager(context);
        networkingManager.getChangeStateFromNetworkAPI();
    }
}
