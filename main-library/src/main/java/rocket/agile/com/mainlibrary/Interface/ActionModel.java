package rocket.agile.com.mainlibrary.Interface;

/**
 * Created by KeithK on 5/29/17.
 *
 * /**
 * Created by keithkowalski on 6/19/17.
 *
 * Purpose:  Provide same 3 methods to all classes that implement ActionModel
 * Function: A) Responsible for forcing required values for every Action Type created
 *           B) Not needed for Values
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
