package rocket.agile.com.mainlibrary.model;

/**
 * Created by KeithK on 5/29/17.
 */

public interface ActionModel {

    void setActionType(int actionType);
    void setFaIcon(String faIcon);
    void setName(String name);

    int getActionType();
    String getFAIcon();
    String getName();
}
