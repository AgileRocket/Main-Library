package rocket.agile.com.mainlibrary.model;

import android.content.Context;
import android.content.Intent;
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
import rocket.agile.com.mainlibrary.activity.LayoutView_Buttons_Grid;
import rocket.agile.com.mainlibrary.activity.LayoutView_Buttons_Long;
import rocket.agile.com.mainlibrary.activity.LayoutView_SideMenu;
import rocket.agile.com.mainlibrary.activity.LayoutView_TabBar;
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
        super.onPreExecute();
//        getChangeStateFromNetworkAPI();   // TODO: Call this when network api is available, may need a delay to complete call first
        if(dataManager.changeStateValue) {
            Toast.makeText(context, "Loading data...", Toast.LENGTH_SHORT).show();
        }
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
        super.onPostExecute(null);

        dataManager.getValues();
        setLayout();

        // Dismiss Progress Dialog
//        dialog.dismiss();
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

    public void setLayout() {

        switch (dataManager.layoutValue) {
            case 0:
                context.startActivity(new Intent(context, LayoutView_SideMenu.class));
                break;
            case 1:
                context.startActivity(new Intent(context, LayoutView_TabBar.class));
                break;
            case 2:
                context.startActivity(new Intent(context, LayoutView_Buttons_Grid.class));
                break;
            case 3:
                context.startActivity(new Intent(context, LayoutView_Buttons_Long.class));
                break;
            default: break;
        }
    }
}