package rocket.agile.com.mainlibrary.networking;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rocket.agile.com.mainlibrary.Interface.RetrofitAPI;
import rocket.agile.com.mainlibrary.model.DataManager;
import rocket.agile.com.mainlibrary.activity.LayoutManager;
import rocket.agile.com.mainlibrary.model.DataManagerHelperMethods;
import rocket.agile.com.mainlibrary.model.appInfo.AppInfo;
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
            getAppInfoFromNetworkAPI();
            getActionsFromNetworkAPI();
        }

        Log.d("BACKGROUND", "HERE");

        return true;
    }

    @Override
    protected void onPostExecute(Boolean bool) {
        super.onPostExecute(null);
        new LayoutManager(context).setLayout();  // Set layout
    }

    public void getAppInfoFromNetworkAPI() {

        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(dataManager.baseURL).
                            addConverterFactory(GsonConverterFactory.create())
                    .build();

            RetrofitAPI service = retrofit.create(RetrofitAPI.class);
            Call<AppInfo> call = service.getValues();

            call.enqueue(new Callback<AppInfo>() {
                @Override
                public void onResponse(Call<AppInfo> call, Response<AppInfo> response) {
                    AppInfo appInfoData = response.body();
                    RealmPersistence.createOrUpdateAppInfo(appInfoData);
                    DataManagerHelperMethods.getAppInfo();
                }
                @Override
                public void onFailure(Call<AppInfo> call, Throwable t) {
                    Log.d("On Failure <getAppInfo_Networking>", t.toString());
                }
            });
        } catch (Exception e) {
            Log.d("Response <getAppInfo_Networking>", "Error");
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
            Call<ResponseBody> call = service.getResponse();

            call.enqueue(new Callback<ResponseBody>() {

                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        String jsonResult = response.body().string();
                        JSONArray jsonArray = new JSONArray(jsonResult);
                        RealmPersistence.createOrUpdateActionItems(jsonArray);  // Store JSON array to Realm
                        setDataManagerActionItems();    // Set DataManager action item data here
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.d("On Failure <getActionItems_Networking>", t.toString());
                }
            });
        } catch (Exception e) {
            Log.d("On Response <getActionItems_Networking>", "Error");
            e.printStackTrace();
        }
    }

    public void setDataManagerActionItems() {
//        DataManagerHelperMethods.getActionEmails();
        DataManagerHelperMethods.getActionCall();
    }
}