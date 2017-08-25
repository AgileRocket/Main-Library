package rocket.agile.com.mainlibrary.model.appInfo;

import com.google.gson.annotations.SerializedName;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by keithkowalski on 5/18/17.
 *
 * Purpose:  Create the list of basic values (i.e. app name, colors, hours, contact email mailingAddress, etc)
 * Function: A) Responsible for storing values to Realm
 *
 */

public class AppInfo extends RealmObject {

    @PrimaryKey @SerializedName("id")
    private int id;

    @SerializedName("mailingAddress")
    private String address;

    @SerializedName("appName")
    private String appName;

    @SerializedName("email")
    private String email;

    @SerializedName("Hours")
    private Hours hours;

    @SerializedName("phone")
    private String phone;

    //    GETTERS and SETTERS
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Hours getHours() { return hours; }

    public void setHours(Hours hours) { this.hours = hours; }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
