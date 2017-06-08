package rocket.agile.com.mainlibrary.model;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import io.realm.Realm;
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

public class NetworkingManager extends AsyncTask<Void, Object, Boolean> {

    private Context context;

    public NetworkingManager(Context context) {
        this.context = context;
    }

    // Data Manager Singleton
    DataManager dataManager = DataManager.getInstance();

    // Create Progress Dialog indicator
    private ProgressDialog dialog;

    // Set base URL
    private String baseURL = "http://rocketdepot.com/api/";

    // TODO: Update to match change values from server
    public boolean getChangeState() {

         return true;
    }

    @Override
    protected void onPreExecute() {

        Log.d("IN PRE", "TRUE");
        Toast.makeText(context, "Loading data...", Toast.LENGTH_LONG).show();

        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {

        Log.d("IN BACKGROUND", "TRUE");

        if(getChangeState()) {
            getValuesFromNetworkAPI();
            getActionsFromNetworkAPI();
        }

        return true;
    }

    @Override
    protected void onPostExecute(Boolean bool) {

        Log.d("IN POST", "TRUE");

        Realm.init(context);
        RealmPersistence.initRealm();

        // Dismiss Progress Dialog
//        dialog.dismiss();
        super.onPostExecute(null);
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
