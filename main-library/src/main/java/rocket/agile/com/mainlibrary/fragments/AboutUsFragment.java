package rocket.agile.com.mainlibrary.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmResults;
import rocket.agile.com.mainlibrary.Interface.ActionModel;
import rocket.agile.com.mainlibrary.R;
import rocket.agile.com.mainlibrary.model.DataManager;
import rocket.agile.com.mainlibrary.model.actionItems.ActionCall;
import rocket.agile.com.mainlibrary.model.actionItems.ActionStaff;

import static android.R.attr.data;
import static rocket.agile.com.mainlibrary.model.DataManager.actionStaff;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutUsFragment extends Fragment {

    DataManager dataManager = DataManager.getInstance();

    public AboutUsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_us, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Check if value is null (no data added by admin)
//        Realm realm = Realm.getDefaultInstance();
//        ActionStaff staff = realm.where(ActionStaff.class).findFirst();
        if(dataManager.actionStaff.size() == 0) {
            Log.d(dataManager.SIDE_MENU_TAG, "actionStaff list is empty and not in use");
        }

        // Set Title Text
        TextView textView_title = (TextView) view.findViewById(R.id.aboutus_text_title);
        textView_title.setText("Phone number: " + dataManager.actionCall.get(0).getNumber());

        // Set Details Text
        TextView textView_details = (TextView) view.findViewById(R.id.aboutus_text_details);
        textView_details.setText(dataManager.appName);

        // Set Body Text
        TextView textView_body = (TextView) view.findViewById(R.id.aboutus_text_body);
        textView_body.setText(dataManager.mondayHours);

        // Set Action Items List
        ListView listView = (ListView) view.findViewById(R.id.action_item_listview);
        String[] emailAddresses = new String[dataManager.actionEmail.size()];
        for(int i = 0; i < dataManager.actionEmail.size(); i++) {
            emailAddresses[i] = dataManager.actionEmail.get(i).getEmailAddress();
        }
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, emailAddresses);
        listView.setAdapter(adapter);
    }
}
