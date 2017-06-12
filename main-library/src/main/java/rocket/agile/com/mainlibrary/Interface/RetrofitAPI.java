package rocket.agile.com.mainlibrary.Interface;

import retrofit2.Call;
import retrofit2.http.GET;
import rocket.agile.com.mainlibrary.model.ActionList;
import rocket.agile.com.mainlibrary.model.ChangeState;
import rocket.agile.com.mainlibrary.model.Values;

/**
 * Created by keithkowalski on 5/18/17.
 */

public interface RetrofitAPI {

    @GET("changeState")
    Call<ChangeState> getChangeState();

    @GET("info")
    Call<Values> getValues();

    @GET("actions")
    Call<ActionList> getActionList();
}
