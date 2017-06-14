package rocket.agile.com.mainlibrary.model;

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
import rocket.agile.com.mainlibrary.actionItems.ActionList;
import rocket.agile.com.mainlibrary.realm.RealmPersistence;

/**
 * Created by keithkowalski on 3/29/17.
 */

public class NetworkingManager extends AsyncTask<Void, Object, Boolean> {

    // Data Manager Singleton
    DataManager dataManager = DataManager.getInstance();

    private Context context;
    public NetworkingManager(Context context) {
        this.context = context;
    }

    // Create Progress Dialog indicator
//    private ProgressDialog dialog;

    // Set base URL
    private String baseURL = "http://rocketdepot.com/api/";

    @Override
    protected void onPreExecute() {

        Toast.makeText(context, "Loading data...", Toast.LENGTH_LONG).show();

        super.onPreExecute();
//        getChangeStateFromNetworkAPI();   // TODO: Call this when network api is available
    }

    @Override
    protected Boolean doInBackground(Void... voids) {

        if(dataManager.changeStateValue) {
            getValuesFromNetworkAPI();
            getActionsFromNetworkAPI();
        }
        return true;
    }

    @Override
    protected void onPostExecute(Boolean bool) {

        // Dismiss Progress Dialog
//        dialog.dismiss();
        super.onPostExecute(null);
    }

    public void getChangeStateFromNetworkAPI() {

        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL).
                            addConverterFactory(GsonConverterFactory.create())
                    .build();

            RetrofitAPI service = retrofit.create(RetrofitAPI.class);

            // TODO: Change this to match change state when available from api
            Call<ChangeState> call = service.getChangeState();

            call.enqueue(new Callback<ChangeState>() {
                @Override
                public void onResponse(Call<ChangeState> call, Response<ChangeState> response) {
                    ChangeState changeState = response.body();
                    dataManager.changeStateValue = changeState.getChangeState();
                }
                @Override
                public void onFailure(Call<ChangeState> call, Throwable t) {
                    Log.d("On Failure", t.toString());
                }
            });
        } catch (Exception e) {
            Log.d("On Response", "There is an error");
            e.printStackTrace();
        }
    }

    public void getValuesFromNetworkAPI() {

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

    public void getActionsFromNetworkAPI() {

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
}