package rocket.agile.com.mainlibrary.model;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rocket.agile.com.mainlibrary.Interface.RetrofitAPI;
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
    protected Boolean doInBackground(Void... voids) {

        if(getChangeState()) {
            Log.d("ASYNC CHECK", "GOT HERE DO");
            getValuesFromNetworkAPI();
            getActionsFromNetworkAPI();
        }

        return true;
    }

    @Override
    protected void onPreExecute() {

        Log.d("ASYNC CHECK", "GOT HERE PRE");

        dialog = new ProgressDialog(context);
        dialog.setMessage("Loading data...");
        dialog.show();
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Boolean bool) {

        // Dismiss Progress Dialog
        dialog.dismiss();
        // Load data from Realm Storage to DataManager Class

        Log.d("ASYNC CHECK", "GOT HERE POST /// appName = " + dataManager.appName);

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

//                    int temp = actionLists.getActions().get(0).getActionType();
//                    String email = actionLists.getActions().get(0).getEmail();

                    ActionEmail actionEmail = new ActionEmail();


                    Log.d("TYPE", temp + "");
                    Log.d("EMAIL", email);



//                    RealmPersistence.createOrUpdateActionItems(actionLists);
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

    private void logOutString() {
        //                    String actions = "";
//
//                    for(int i = 0; i < actionLists.getTotal(); i++) {
//                        int actionType = actionLists.getActionsFromNetworkAPI().get(i).getActionType();
//                        String email = actionLists.getActionsFromNetworkAPI().get(i).getEmail();
//                        String icon = actionLists.getActionsFromNetworkAPI().get(i).getFaIcon();
//                        String name = actionLists.getActionsFromNetworkAPI().get(i).getName();
//                        String subject = actionLists.getActionsFromNetworkAPI().get(i).getSubject();
//
//                        actions += "Action: "  + actionType + "\n" +
//                                "Email: "   + email      + "\n" +
//                                "FA Icon: " + icon       + "\n" +
//                                "Name: "    + name       + "\n" +
//                                "Subject: " + subject    + "\n\n";
//                    }
//
//                    String total = actionLists.getTotal().toString();
//                    actions += "Total Actions: " + total;
//
//                    Log.d("ACTION LIST", actions);
    }
}
