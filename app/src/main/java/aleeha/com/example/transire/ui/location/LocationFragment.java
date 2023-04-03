package aleeha.com.example.transire.ui.location;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import aleeha.com.example.transire.R;

public class LocationFragment extends Fragment {

    WebView myWebView;
    ProgressBar progressBar;


    public LocationFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_location, container, false);
        myWebView = (WebView) view.findViewById(R.id.wv_mapView);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);
        myWebView.setVisibility(View.GONE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                myWebView.loadUrl("https://www.google.com/maps/?q=16.39494807504391, 120.57488264828564");
                progressBar.setVisibility(View.GONE);
                myWebView.setVisibility(View.VISIBLE);
            }
        },500);


        return view;
    }
}