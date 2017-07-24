package rocket.agile.com.mainlibrary.networking;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rocket.agile.com.mainlibrary.Interface.RetrofitAPI;
import rocket.agile.com.mainlibrary.model.ChangeState;
import rocket.agile.com.mainlibrary.model.DataManager;

/**
 * Created by keithkowalski on 6/28/17.
 *
 * Responsible for calling ChangeState value
 * Class extends AsyncTask to guarantee completion before dismissing Dialog Alert
 * Kept separate from NetworkManager to fulfill requirement of checking this network value independently
 *
 */

public class NetworkGetChangeState extends AsyncTask<Void, Void, Boolean> {

    // Data Manager Singleton
    DataManager dataManager = DataManager.getInstance();

    private Context context;
    public NetworkGetChangeState(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d("Networking<ChangeState>", "PreExecute");
    }

    @Override
    protected Boolean doInBackground(Void... voids) {

        Log.d("Networking<ChangeState>", "Background");
        getChangeStateFromNetworkAPI();
        return true;
    }

    @Override
    protected void onPostExecute(Boolean bool) {
        super.onPostExecute(null);

        Log.d("Networking<ChangeState>", "PostExecute");
        dataManager.progressDialog.dismiss();
    }

    public void getChangeStateFromNetworkAPI() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(dataManager.baseURL).
                        addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI service = retrofit.create(RetrofitAPI.class);
        Call<ChangeState> call = service.getChanges();
        try {
            Response<ChangeState> response = call.execute();
            ChangeState changeState = response.body();
            dataManager.changeStateValue = changeState.getChangeState();
            dataManager.changeStateIDs = changeState.getChangeIDs();
        } catch (IOException e) {
            Log.d("Retrofit", "Failure");
        }
    }
}
