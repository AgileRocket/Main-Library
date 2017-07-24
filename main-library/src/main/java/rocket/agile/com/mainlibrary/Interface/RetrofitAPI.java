package rocket.agile.com.mainlibrary.Interface;

import org.json.JSONArray;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import rocket.agile.com.mainlibrary.model.ChangeState;
import rocket.agile.com.mainlibrary.model.appInfo.AppInfo;

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
    Call<ChangeState> getChanges();

    @GET("info")
    Call<AppInfo> getValues();

    @GET("actions")
    Call<ResponseBody> getResponse();
}
