package rocket.agile.com.mainlibrary.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import rocket.agile.com.mainlibrary.R;
import rocket.agile.com.mainlibrary.model.DataManager;

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
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ImageView imageView;

        if(view == null) {

            imageView = new ImageView(this.context);
            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) view;
        }

        imageView.setImageResource(R.color.colorPrimary);
        return imageView;
    }
}

