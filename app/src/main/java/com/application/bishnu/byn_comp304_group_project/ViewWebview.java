package com.application.bishnu.byn_comp304_group_project;
/**
 * Author Bishnu Khanal
 * date: 27/04/2018
 * subject: COMP304-003
 * Project: online book store.
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class ViewWebview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_webview);

        WebView wv = (WebView) findViewById(R.id.webview);
        wv.loadUrl("https://www.amazon.ca/s/ref=nb_sb_noss?url=node%3D935948&field-keywords=books+review");
    }
}
