package rocket.agile.com.mainlibrary.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import rocket.agile.com.mainlibrary.R;
import rocket.agile.com.mainlibrary.model.DataManager;
import rocket.agile.com.mainlibrary.model.actionItems.ActionCall;
import rocket.agile.com.mainlibrary.model.actionItems.ActionEmail;
import rocket.agile.com.mainlibrary.model.actionItems.ActionStaff;

/**
 * Created by KeithK on 10/12/17.
 */

public class ActionItemAdapter extends BaseAdapter {

    private DataManager dataManager = DataManager.getInstance();
    private Context context;
    private int actionItemCount;

    public ActionItemAdapter(Context context, int actionItemCount) {

        this.context = context;
        this.actionItemCount = actionItemCount;
    }

    @Override
    public int getCount() {

        return actionItemCount;
    }

    @Override
    public Object getItem(int i) {

        // TODO: i is the index of the custom array created in activity that calls this adapter (example: http://www.coderzheaven.com/2012/02/29/custom-gridview-in-android-a-simple-example/)
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {

        ImageView imageView;

        if(view == null) {

            imageView = new ImageView(this.context);
            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);

//        imageView.setImageResource(this, dataManager.actionEmail.get(0).getFAIcon()));

            for (Class actionClass : dataManager.actionClasses) {
                switch (actionClass.getSimpleName()) {
                    case "ActionEmail":
                        if (dataManager.actionEmail.size() > 0) {
                            for (ActionEmail actionEmail : dataManager.actionEmail) {
                                imageView.setImageResource(R.color.colorPrimary);
                                return imageView;
                            }
                        }
                        break;
                    case "ActionCall":
                        if (dataManager.actionCall.size() > 0) {
                            for (ActionCall actionCall : dataManager.actionCall) {
                                imageView.setImageResource(R.color.colorAccent);
                                return imageView;
                            }
                        }
                        break;
                    case "ActionStaff":
                        if (dataManager.actionStaff.size() > 0) {
                            for (ActionStaff actionStaff : dataManager.actionStaff) {
                                imageView.setImageResource(R.color.colorPrimaryDark);
                                return imageView;
                            }
                        }
                        break;
                    default:
                        break;
                }
            }

        } else {
            imageView = (ImageView) view;
        }

        return imageView;
    }
}

