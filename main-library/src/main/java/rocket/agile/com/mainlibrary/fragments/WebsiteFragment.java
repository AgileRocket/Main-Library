package rocket.agile.com.mainlibrary.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import rocket.agile.com.mainlibrary.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WebsiteFragment extends Fragment {

    private WebView webView;

    public WebsiteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String actionTitle = bundle.get("title").toString();
            Log.d("FRAGMENT TEST", actionTitle);
        }

        View v = inflater.inflate(R.layout.fragment_website, container, false);

        // SET WEBSITE
        webView = (WebView) v.findViewById(R.id.web_view);
        // TODO: Load site from network call
        webView.loadUrl("https://www.google.com");

        // Enable Java Script
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient());

        return v;
    }
}