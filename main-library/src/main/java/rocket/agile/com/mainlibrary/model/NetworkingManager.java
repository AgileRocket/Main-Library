package rocket.agile.com.mainlibrary.model;

import android.app.ProgressDialog;
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

public class NetworkingManager extends MasterView {

    // Create Singleton
    private static final NetworkingManager ourInstance = new NetworkingManager();
    public static NetworkingManager getInstance() {
        return ourInstance;
    }

    private String baseURL = "http://rocketdepot.com/api/";

    public void getValues(final ProgressDialog progressDialog) {

        showpDialog(progressDialog);

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

                    hidepDialog(progressDialog);
                }

                @Override
                public void onFailure(Call<Values> call, Throwable t) {
                    Log.d("On Failure", t.toString());
                    hidepDialog(progressDialog);
                }
            });
        } catch (Exception e) {
            Log.d("On Response", "There is an error");
            e.printStackTrace();
            hidepDialog(progressDialog);
        }
    }

    private void showpDialog(ProgressDialog progressDialog) {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    private void hidepDialog(ProgressDialog progressDialog) {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }
}
