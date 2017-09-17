package rocket.agile.com.mainlibrary.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import rocket.agile.com.mainlibrary.R;
import rocket.agile.com.mainlibrary.model.DataManager;
import rocket.agile.com.mainlibrary.model.actionItems.ActionEmail;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutUsFragment extends Fragment {

    DataManager dataManager = DataManager.getInstance();
    private ActionEmail actionEmail;

    public AboutUsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Iconify.with(new FontAwesomeModule());

        // Find which ActionEmail is equivalent to the one tapped
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String actionTitle = bundle.get("title").toString();

            for(ActionEmail actionEmail: dataManager.actionEmail) {
                if(actionEmail.getName().contentEquals(actionTitle)) {
                    // Set action email here, based on title
                    this.actionEmail = actionEmail;
                }
            }
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_us, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set Image at top
        ImageView imageView = (ImageView) view.findViewById(R.id.aboutus_image_header_icon);
        imageView.setImageResource(R.drawable.ic_menu_gallery);

        // Set Title Text
        TextView textView_title = (TextView) view.findViewById(R.id.aboutus_text_title);
        textView_title.setText(this.actionEmail.getName());

        // Set Details Text
        TextView textView_details = (TextView) view.findViewById(R.id.aboutus_text_details);
        textView_details.setText(this.actionEmail.getEmailAddress());

        // Set Body Text
        TextView textView_body = (TextView) view.findViewById(R.id.aboutus_text_body);
        textView_body.setText(this.actionEmail.getSubject());

        // Set Action Items List
//        ListView listView = (ListView) view.findViewById(R.id.action_item_listview);
//        String[] emailAddresses = new String[dataManager.actionEmail.size()];
//        for(int i = 0; i < dataManager.actionEmail.size(); i++) {
//            emailAddresses[i] = dataManager.actionEmail.get(i).getEmailAddress();
//        }
//        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, emailAddresses);
//        listView.setAdapter(adapter);
    }
}
