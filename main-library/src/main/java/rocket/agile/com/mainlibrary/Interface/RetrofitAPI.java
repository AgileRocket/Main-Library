package rocket.agile.com.mainlibrary.Interface;

import retrofit2.Call;
import retrofit2.http.GET;
import rocket.agile.com.mainlibrary.model.Values;

/**
 * Created by keithkowalski on 5/18/17.
 */

public interface RetrofitAPI {

    @GET("info")
    Call<Values> getValues();

//    @GET("actions")
//    Call<ActionList> getActionList();
}
