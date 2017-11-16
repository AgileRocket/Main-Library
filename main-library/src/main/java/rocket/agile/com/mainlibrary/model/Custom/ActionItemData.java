package rocket.agile.com.mainlibrary.model.Custom;

/**
 * Created by keithkowalski on 10/23/17.
 *
 *  The purpose of this class is to capture the action item and it's index from its Realm List.
 *  This will allow it to be properly referenced in other classes.
 *
 */

public class ActionItemData {

    public Object actionItem;
    public int actionItemIndex;

    public ActionItemData(Object actionItem, int actionItemIndex) {
        this.actionItem = actionItem;
        this.actionItemIndex = actionItemIndex;
    }
}
