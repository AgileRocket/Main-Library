package rocket.agile.com.mainlibrary.networking;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Call;
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

public class NetworkingManager extends AsyncTask<Void, Void, JSONArray> {

    // Data Manager Singleton
    DataManager dataManager = DataManager.getInstance();

    private Context context;
    public NetworkingManager(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        Log.d("IN", "PRELOADING");

//        if(!dataManager.changeStateValue) { // initialize change state to false in data manager
//            Toast.makeText(context, "Checking for updates...", Toast.LENGTH_SHORT).show();
//            // Set dataManager changeState value
//            networkCalls.getChangeStateFromNetworkAPI();
//        } else {
//            Toast.makeText(context, "Loading data...", Toast.LENGTH_SHORT).show();
//        }
    }

    @Override
    protected JSONArray doInBackground(Void... voids) {

        Log.d("BACKGROUND", "HERE");

        JSONArray jsonArray;
        getAppInfoFromNetworkAPI();
        jsonArray = getActionsFromNetworkAPI();
        return jsonArray;
    }

    @Override
    protected void onPostExecute(JSONArray result) {
        super.onPostExecute(result);
        RealmPersistence.createOrUpdateActionItems(result);
        setDataManagerActionItems();
        new LayoutManager(context).setLayout();  // Set layout
        dataManager.progressDialog.dismiss();
    }

    public void getAppInfoFromNetworkAPI() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(dataManager.baseURL).
                        addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI service = retrofit.create(RetrofitAPI.class);
        Call<AppInfo> call = service.getValues();

        try {
            Response<AppInfo> response = call.execute();
            AppInfo appInfo = response.body();
            RealmPersistence.createOrUpdateAppInfo(appInfo);
            DataManagerHelperMethods.getAppInfo();
        } catch (IOException e) {
            Log.d("Retrofit", "Failure");
        }
    }

    public JSONArray getActionsFromNetworkAPI() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(dataManager.baseURL).
                        addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI service = retrofit.create(RetrofitAPI.class);
        Call<ResponseBody> call = service.getResponse();

        try {
            Response<ResponseBody> response = call.execute();
            String jsonResult = response.body().string();
            JSONArray jsonArray = new JSONArray(jsonResult);
            return jsonArray;
        } catch (IOException e) {
            Log.d("Retrofit Actions", "Failure");
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("Retrofit Actions", "Failure");
        }
        return null;
    }

    public void setDataManagerActionItems() {
        DataManagerHelperMethods.getActionEmails();
        DataManagerHelperMethods.getActionCall();
    }
}