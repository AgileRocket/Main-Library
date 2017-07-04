package rocket.agile.com.mainlibrary.networking;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rocket.agile.com.mainlibrary.Interface.RetrofitAPI;
import rocket.agile.com.mainlibrary.activity.MasterView;
import rocket.agile.com.mainlibrary.model.ApplicationLifeCycleTracker;
import rocket.agile.com.mainlibrary.model.DataManager;

/**
 * Created by keithkowalski on 6/28/17.
 *
 * Responsible for calling ChangeState value
 * Class extends AsyncTask to guarantee completion before dismissing Dialog Alert
 * Kept separate from NetworkManager to fulfill requirement of checking this network value independently
 *
 */

public class NetworkGetChangeState extends AsyncTask<Void, Object, Boolean> {

    private Context context;
    public NetworkGetChangeState(Context context) {
        this.context = context;
    }

    // Data Manager Singleton
    DataManager dataManager = DataManager.getInstance();

    // Progress dialog variable
    ProgressDialog progressDialog;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = new ProgressDialog(context);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Checking for updates...");
        progressDialog.setTitle("Please wait");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {

        getChangeStateFromNetworkAPI();

        return true;
    }

    @Override
    protected void onPostExecute(Boolean bool) {
        super.onPostExecute(null);

        if(dataManager.changeStateValue) {
            ApplicationLifeCycleTracker.initialStart = true;
            Intent intent = new Intent(context, MasterView.class);
            context.startActivity(intent);
        }

        progressDialog.dismiss();
    }

    public void getChangeStateFromNetworkAPI() {

        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(dataManager.baseURL).
                            addConverterFactory(GsonConverterFactory.create())
                    .build();

            RetrofitAPI service = retrofit.create(RetrofitAPI.class);

            // TODO: Remove temp code for actual server call that is currently commented
            Log.d("CHECK CHANGE STATE", "TRUE");
            dataManager.changeStateValue = true;

//            // TODO: Change this to match change state when available from api
//            Call<ChangeState> call = service.getChangeState();
//
//            call.enqueue(new Callback<ChangeState>() {
//                @Override
//                public void onResponse(Call<ChangeState> call, Response<ChangeState> response) {
//                    ChangeState changeState = response.body();
//                    dataManager.changeStateValue = changeState.getChangeState();
//                }
//                @Override
//                public void onFailure(Call<ChangeState> call, Throwable t) {
//                    Log.d("On Failure", t.toString());
//                }
//            });
        } catch (Exception e) {
            Log.d("On Response", "There is an error");
            e.printStackTrace();
        }
    }
}
