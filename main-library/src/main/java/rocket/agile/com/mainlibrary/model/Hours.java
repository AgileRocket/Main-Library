package rocket.agile.com.mainlibrary.model;

import com.google.gson.annotations.SerializedName;
import io.realm.RealmObject;

/**
 * Created by keithkowalski on 5/18/17.
 */

public class Hours extends RealmObject {

    @SerializedName("Fri")
    private String friday;

    @SerializedName("Mon")
    private String monday;

    @SerializedName("Sat")
    private String saturday;

    @SerializedName("Sun")
    private String sunday;

    @SerializedName("Thu")
    private String thursday;

    @SerializedName("Tue")
    private String tuesday;

    @SerializedName("Wed")
    private String wednesday;

    // GETTERS and SETTERS
    public String getFriday() {
        return friday;
    }

    public void setFriday(String friday) {
        this.friday = friday;
    }

    public String getMonday() {
        return monday;
    }

    public void setMonday(String monday) {
        this.monday = monday;
    }

    public String getSaturday() {
        return saturday;
    }

    public void setSaturday(String saturday) {
        this.saturday = saturday;
    }

    public String getSunday() {
        return sunday;
    }

    public void setSunday(String sunday) {
        this.sunday = sunday;
    }

    public String getThursday() {
        return thursday;
    }

    public void setThursday(String thursday) {
        this.thursday = thursday;
    }

    public String getTuesday() {
        return tuesday;
    }

    public void setTuesday(String tuesday) {
        this.tuesday = tuesday;
    }

    public String getWednesday() {
        return wednesday;
    }

    public void setWednesday(String wednesday) {
        this.wednesday = wednesday;
    }
}
