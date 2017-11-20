package rocket.agile.com.mainlibrary.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.joanzapata.iconify.IconDrawable;

import rocket.agile.com.mainlibrary.R;
import rocket.agile.com.mainlibrary.model.DataManager;

/**
 * Created by KeithK on 11/18/17.
 */

public class ActionItemAdapterListView extends BaseAdapter {

    private DataManager dataManager = DataManager.getInstance();
    private Context context;
    private LayoutInflater mInflater;

    public ActionItemAdapterListView(Context context) {
        this.context = context;
        mInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return dataManager.availableActionItems.size();
    }

    @Override
    public Object getItem(int position) {
        return dataManager.availableActionItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get view for row item
        String className = null;
        int actionItemIndex;
        ViewHolder holder;


        if(convertView == null) {
            convertView = mInflater.inflate(R.layout.listview_custom_row, parent, false);

            holder = new ViewHolder();
            holder.rowView = convertView.findViewById(R.id.row_constraintLayout);
            holder.titleTextView = convertView.findViewById(R.id.listview_action_item_title);
            holder.iconImageView = convertView.findViewById(R.id.listview_action_item_icon);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.rowView = convertView.findViewById(R.id.row_constraintLayout);
        holder.titleTextView = convertView.findViewById(R.id.listview_action_item_title);
        holder.iconImageView = convertView.findViewById(R.id.listview_action_item_icon);

        // Filter out class name from provided string name of each available actionItem class
        String rawName = dataManager.availableActionItems.get(position).actionItem.getClass().getSimpleName();
        if(rawName.endsWith("RealmProxy")) {
            className = rawName.substring(0, rawName.indexOf("RealmProxy"));
        }
        // Get index of actionItem stored
        actionItemIndex = dataManager.availableActionItems.get(position).actionItemIndex;

        switch(className) {
            case "ActionEmail":
                // Get title element
                holder.rowView.setBackgroundColor(Color.TRANSPARENT);
                holder.titleTextView.setText(dataManager.actionEmail.get(actionItemIndex).getName());
                holder.titleTextView.setTextColor(Color.RED);
                holder.iconImageView.setImageDrawable(new IconDrawable(this.context, dataManager.actionEmail.get(actionItemIndex).getFAIcon())
                        .color(Color.RED)
                        .sizeDp(20));
                break;
            case "ActionCall":
                holder.rowView.setBackgroundColor(Color.TRANSPARENT);
                holder.titleTextView.setText(dataManager.actionCall.get(actionItemIndex).getName());
                holder.titleTextView.setTextColor(Color.RED);
                holder.iconImageView.setImageDrawable(new IconDrawable(this.context, dataManager.actionCall.get(actionItemIndex).getFAIcon())
                        .color(Color.RED)
                        .sizeDp(20));
                break;
            case "ActionStaff":
                holder.rowView.setBackgroundColor(Color.TRANSPARENT);
                holder.titleTextView.setText(dataManager.actionStaff.get(actionItemIndex).getName());
                holder.titleTextView.setTextColor(Color.RED);
                holder.iconImageView.setImageDrawable(new IconDrawable(this.context, dataManager.actionStaff.get(actionItemIndex).getFAIcon())
                        .color(Color.RED)
                        .sizeDp(20));
                break;
            default:
                break;
        }
        return convertView;
    }

    private static class ViewHolder {
        public View rowView;
        public TextView titleTextView;
        public ImageView iconImageView;
    }
}
