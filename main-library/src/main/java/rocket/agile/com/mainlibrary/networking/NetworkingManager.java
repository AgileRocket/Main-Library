package rocket.agile.com.mainlibrary.networking;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rocket.agile.com.mainlibrary.Interface.RetrofitAPI;
import rocket.agile.com.mainlibrary.model.actionItems.ActionList;
import rocket.agile.com.mainlibrary.model.DataManager;
import rocket.agile.com.mainlibrary.activity.LayoutManager;
import rocket.agile.com.mainlibrary.model.actionItems.Values;
import rocket.agile.com.mainlibrary.realm.RealmPersistence;

/**
 * Created by keithkowalski on 3/29/17.
 *
 * Purpose:  Networking process for data value calls and action item calls; uses AsyncTask to
 *           prevent app progression until network call is completed
 *
 */

public class NetworkingManager extends AsyncTask<Void, Object, Boolean> {

    // Data Manager Singleton
    DataManager dataManager = DataManager.getInstance();

    private Context context;
    public NetworkingManager(Context context) {
        this.context = context;
    }
    NetworkCalls networkCalls = new NetworkCalls(context);

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if(!dataManager.changeStateValue) { // initialize change state to false in data manager
            Toast.makeText(context, "Checking for updates...", Toast.LENGTH_SHORT).show();
            // Set dataManager changeState value
            networkCalls.getChangeStateFromNetworkAPI();
        } else {
            Toast.makeText(context, "Loading data...", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected Boolean doInBackground(Void... voids) {

        if(dataManager.changeStateValue) {
            getValuesFromNetworkAPI();
            getActionsFromNetworkAPI();
        }
        dataManager.getValues();
        return true;
    }

    @Override
    protected void onPostExecute(Boolean bool) {
        super.onPostExecute(null);
        new LayoutManager(context).setLayout(dataManager);  // Set layout
    }

    public void getValuesFromNetworkAPI() {

        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(dataManager.baseURL).
                            addConverterFactory(GsonConverterFactory.create())
                    .build();

            RetrofitAPI service = retrofit.create(RetrofitAPI.class);
            Call<Values> call = service.getValues();

            call.enqueue(new Callback<Values>() {
                @Override
                public void onResponse(Call<Values> call, Response<Values> response) {
                    Values valuesData = response.body();
                    Log.d("--NETWORKING--", "COMPLETE");    // Test for when this completes...
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

    public void getActionsFromNetworkAPI() {

        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(dataManager.baseURL).
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
}