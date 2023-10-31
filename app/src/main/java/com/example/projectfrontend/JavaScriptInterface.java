package com.example.projectfrontend;

import android.webkit.JavascriptInterface;

public class JavaScriptInterface {
    private TrizActivity tActivity;

    public JavaScriptInterface(TrizActivity activity){
        tActivity = activity;
    }

    @JavascriptInterface
    public void playVideo() {}
}
