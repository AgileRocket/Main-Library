package rocket.agile.com.mainlibrary.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.joanzapata.iconify.IconDrawable;

import java.util.ArrayList;

import rocket.agile.com.mainlibrary.model.DataManager;

/**
 * Created by KeithK on 10/12/17.
 */

public class ActionItemAdapter extends BaseAdapter {

    private DataManager dataManager = DataManager.getInstance();
    private Context context;
    private ArrayList<Object> availableActionItemsList;

    public ActionItemAdapter(Context context, ArrayList<Object> objectArrayList) {
        this.context = context;
        this.availableActionItemsList = objectArrayList;
    }

    @Override
    public int getCount() {

        return availableActionItemsList.size();
    }

    @Override
    public Object getItem(int i) {

        // TODO: i is the index of the custom array created in activity that calls this adapter (example: http://www.coderzheaven.com/2012/02/29/custom-gridview-in-android-a-simple-example/)
        return availableActionItemsList.get(i);
    }

    @Override
    public long getItemId(int i) {

        return i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {

        ImageView imageView;
        String className = null;

        if(view == null) {

            imageView = new ImageView(this.context);
            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);

            // Filter out class name from provided string name of each available actionItem class
            String rawName = availableActionItemsList.get(i).getClass().getSimpleName();
            if(rawName.endsWith("RealmProxy")) {
                className = rawName.substring(0, rawName.indexOf("RealmProxy"));
            }

            switch(className) {
                case "ActionEmail":
                    imageView.setImageDrawable(new IconDrawable(this.context, dataManager.actionEmail.get(0).getFAIcon())
                            .color(Color.RED)
                            .sizeDp(20));
                    break;
                case "ActionCall":
                    imageView.setImageDrawable(new IconDrawable(this.context, dataManager.actionCall.get(0).getFAIcon())
                            .color(Color.RED)
                            .sizeDp(20));
                    break;
                case "ActionStaff":
                    imageView.setImageDrawable(new IconDrawable(this.context, dataManager.actionStaff.get(0).getFAIcon())
                            .color(Color.RED)
                            .sizeDp(20));
                    break;
                default:
                    break;
            }
        } else {
            imageView = (ImageView) view;
        }
        return imageView;
    }
}

