package rocket.agile.com.mainlibrary.Interface;

/**
 * Created by KeithK on 5/29/17.
 *
 * Responsible for forcing required values for every Action Type created
 * Not needed for Values
 *
 */

public interface ActionModel {

    void setActionType(int actionType);
    void setFaIcon(String faIcon);
    void setName(String name);

    int getActionType();
    String getFAIcon();
    String getName();
}
