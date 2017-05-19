package rocket.agile.com.mainlibrary.model;

import android.app.ProgressDialog;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rocket.agile.com.mainlibrary.Interface.RetrofitAPI;

/**
 * Created by keithkowalski on 3/29/17.
 */

public class NetworkingManager {

    // Create Singleton
    private static final NetworkingManager ourInstance = new NetworkingManager();
    public static NetworkingManager getInstance() {
        return ourInstance;
    }

    private ProgressDialog progressDialog;
    private String baseURL = "http://rocketdepot.com/api/";

    public void getValues() {

//        showpDialog();

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

                    String valuesDetails = "";

                    String address = valuesData.getAddress();
                    String appName = valuesData.getAppName();
                    String email   = valuesData.getEmail();
                    String mHours  = valuesData.getHours().getMonday();
                    String tHours  = valuesData.getHours().getTuesday();
                    String wHours  = valuesData.getHours().getWednesday();
                    String thHours = valuesData.getHours().getThursday();
                    String fHours  = valuesData.getHours().getFriday();
                    String sHours  = valuesData.getHours().getSaturday();
                    String suHours = valuesData.getHours().getSunday();
                    String phone   = valuesData.getPhone();

                    valuesDetails += "Address: " + address + "\n" +
                            "AppName: " + appName + "\n" +
                            "Email: "   + email   + "\n" +
                            "Hours: \n" + "\tMonday: "    + mHours  + "\n" +
                            "\tTudesday: "  + tHours  + "\n" +
                            "\tWednesday: " + wHours  + "\n" +
                            "\tThursday: "  + thHours + "\n" +
                            "\tFriday: "    + fHours  + "\n" +
                            "\tSaturday: "  + sHours  + "\n" +
                            "\tSunday: "    + suHours + "\n" +
                            "Phone: "   + phone  + "\n\n";

                    Log.d("VALUES", valuesDetails);

//                    hidepDialog();
                }

                @Override
                public void onFailure(Call<Values> call, Throwable t) {
                    Log.d("On Failure", t.toString());
//                    hidepDialog();
                }
            });
        } catch (Exception e) {
            Log.d("On Response", "There is an error");
            e.printStackTrace();
//            hidepDialog();
        }
    }

    private void showpDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    private void hidepDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }
}
