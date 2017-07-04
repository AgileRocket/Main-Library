package rocket.agile.com.mainlibrary.Interface;

import retrofit2.Call;
import retrofit2.http.GET;
import rocket.agile.com.mainlibrary.model.actionItems.ActionList;
import rocket.agile.com.mainlibrary.model.ChangeState;
import rocket.agile.com.mainlibrary.model.actionItems.Values;

/**
 * Created by keithkowalski on 5/18/17.
 *
 * Purpose: Provide call names for Retrofit to use
 * Function:  Retrofit makes calls to get methods based on naming conventions provided here.
 *            Interface forces every class implementing RetrofitAPI to inherit these methods
 *
 */

public interface RetrofitAPI {

    @GET("changeState")
    Call<ChangeState> getChangeState();

    @GET("info")
    Call<Values> getValues();

    @GET("actions")
    Call<ActionList> getActionList();
}
