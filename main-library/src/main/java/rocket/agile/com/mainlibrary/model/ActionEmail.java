package rocket.agile.com.mainlibrary.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmModel;

/**
 * Created by KeithK on 5/29/17.
 */

public class ActionEmail extends ActionModel {

    @SerializedName("email")
    private String email;
    @SerializedName("subject")
    private String subject;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
}
