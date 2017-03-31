package rocket.agile.com.mainlibrary;

/**
 * Created by keithkowalski on 3/29/17.
 */

public class Networking {

    private static final Networking ourInstance = new Networking();

    public static Networking getInstance() {
        return ourInstance;
    }

    // Values that will come via network calls
    public int layoutValue = 0;
    public int phoneNumber = 01234567;

    // ToDo: Consider making this into an object-like value for 'about us'
    // About Us Info
    public String aboutUs_Body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
            "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure " +
            "dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non " +
            "proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

    public String aboutUs_Email = "BusinessName@gmail.com";

    public String aboutUs_Header = "Company Name";
}
