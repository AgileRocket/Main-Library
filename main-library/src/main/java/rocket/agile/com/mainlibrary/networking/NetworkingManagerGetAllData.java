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
 * Purpose:  Networking process for data value calls and action item calls; uses Retrofit2
 *           synchronized data within AsyncTask to prevent app progression until network call is completed
 *
 */

public class NetworkingManagerGetAllData extends AsyncTask<Void, Void, JSONArray> {

    // Data Manager Singleton
    private DataManager dataManager = DataManager.getInstance();

    // JSON array for action items
    private JSONArray jsonArray;

    // Context passed in via constructor
    private Context context;
    public NetworkingManagerGetAllData(Context context) {   // public constructor
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d("Networking", "PreExecute");
    }

    @Override
    protected JSONArray doInBackground(Void... voids) {

        Log.d("Networking", "Background");

        getAppInfoFromNetworkAPI();
        jsonArray = getAllActionsFromNetworkAPI();
        return jsonArray;
    }

    @Override
    protected void onPostExecute(JSONArray result) {

        Log.d("Networking", "PostExecute");

        super.onPostExecute(result);
        RealmPersistence.createOrUpdateActionItems(result);
        DataManagerHelperMethods.getAllActionItemsFromRealm();
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

    public JSONArray getAllActionsFromNetworkAPI() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(dataManager.baseURL).
                        addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI service = retrofit.create(RetrofitAPI.class);
        Call<ResponseBody> call = service.getResponse();

        try {   // Synchronous networking call
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
}