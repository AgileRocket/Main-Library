package rocket.agile.com.mainlibrary.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import rocket.agile.com.mainlibrary.R;
import rocket.agile.com.mainlibrary.model.DataManager;


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

        // Set Title Text
        TextView textView_title = (TextView) view.findViewById(R.id.aboutus_text_title);
        textView_title.setText(dataManager.email);

        // Set Details Text
        TextView textView_details = (TextView) view.findViewById(R.id.aboutus_text_details);
        textView_details.setText(dataManager.address);

        // Set Body Text
        TextView textView_body = (TextView) view.findViewById(R.id.aboutus_text_body);
        textView_body.setText(dataManager.mondayHours);
    }
}
