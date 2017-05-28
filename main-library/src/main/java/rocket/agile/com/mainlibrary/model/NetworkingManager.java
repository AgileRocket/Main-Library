package rocket.agile.com.mainlibrary.model;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rocket.agile.com.mainlibrary.Interface.RetrofitAPI;
import rocket.agile.com.mainlibrary.activity.MasterView;
import rocket.agile.com.mainlibrary.realm.RealmPersistence;

/**
 * Created by keithkowalski on 3/29/17.
 */

public class NetworkingManager extends AsyncTask<Object, Object, Void> {

    private Context context;

    public NetworkingManager(Context context) {
        this.context = context;
    }

    // Create Singleton
    private static final NetworkingManager ourInstance = new NetworkingManager(null);
    public static NetworkingManager getInstance() {
        return ourInstance;
    }

    // Create Progress Dialog indicator
    private ProgressDialog dialog;

    // Set base URL
    private String baseURL = "http://rocketdepot.com/api/";

    // TODO: Update to match change values from server
    public boolean getChangeState() {

         return true;
    }

    public void getValues() {

        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL).
                            addConverterFactory(GsonConverterFactory.create())
                    .build();

            RetrofitAPI service = retrofit.create(RetrofitAPI.class);

            Call<Values> call = service.getValues();

            call.enqueue(new Callback<Values>() {
                @Override
                public void onResponse(Call<Values> call, Response<Values> response) {
                    Values valuesData = response.body();
                    RealmPersistence.createOrUpdateValues(valuesData);
                }

                @Override
                public void onFailure(Call<Values> call, Throwable t) {
                    Log.d("On Failure", t.toString());
                }
            });
        } catch (Exception e) {
            Log.d("On Response", "There is an error");
            e.printStackTrace();
        }
    }

    public void getActions() {

        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL).
                            addConverterFactory(GsonConverterFactory.create())
                    .build();

            RetrofitAPI service = retrofit.create(RetrofitAPI.class);

            Call<ActionList> call = service.getActionList();

            call.enqueue(new Callback<ActionList>() {
                @Override
                public void onResponse(Call<ActionList> call, Response<ActionList> response) {
                    ActionList actionLists = response.body();

                    RealmPersistence.createOrUpdateActionItems(actionLists);
                }

                @Override
                public void onFailure(Call<ActionList> call, Throwable t) {
                    Log.d("On Failure", t.toString());
                }
            });
        } catch (Exception e) {
            Log.d("On Response", "There is an error");
            e.printStackTrace();
        }
    }

    private void logOutString() {
        //                    String actions = "";
//
//                    for(int i = 0; i < actionLists.getTotal(); i++) {
//                        int actionType = actionLists.getActions().get(i).getActionType();
//                        String email = actionLists.getActions().get(i).getEmail();
//                        String icon = actionLists.getActions().get(i).getFaIcon();
//                        String name = actionLists.getActions().get(i).getName();
//                        String subject = actionLists.getActions().get(i).getSubject();
//
//                        actions += "Action: "  + actionType + "\n" +
//                                "Email: "   + email      + "\n" +
//                                "FA Icon: " + icon       + "\n" +
//                                "Name: "    + name       + "\n" +
//                                "Subject: " + subject    + "\n\n";
//                    }
//
//                    String total = actionLists.getTotal().toString();
//                    actions += "Total Actions: " + total;
//
//                    Log.d("ACTION LIST", actions);
    }

// TODO:  See if network availability can be checked here...
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager;
        connectivityManager = (ConnectivityManager) context.getSystemService();
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(context);
        dialog.setMessage("Loading data...");
        dialog.show();
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Object... voids) {

        if(getChangeState()) {
            getValues();
            getActions();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {

        // Dismiss Progress Dialog
        dialog.dismiss();
        // Data Manager Singleton
        DataManager dataManager = DataManager.getInstance();
        // Load data from Realm Storage to DataManager Class
        dataManager.getDataFromRealmPersistence();

        super.onPostExecute(null);
    }
}
