/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package org.rpgm.main;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;

import org.apache.cordova.CordovaActivity;
import org.rpgm.apihandling.GPlusAPIActivity;
import org.rpgm.apihandling.GPlusAPIHandler;
import org.rpgm.filehandling.ContentExposure;

public class MainActivity extends CordovaActivity {
    private final int SYSTEM_UI_OPTIONS = View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
            View.SYSTEM_UI_FLAG_FULLSCREEN |
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

    private static GPlusAPIActivity gPlusAPIActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.init();

        // enable Cordova apps to be started in the background
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.getBoolean("cdvStartInBackground", false)) {
            moveTaskToBack(true);
        }

        WebView webView = (WebView)appView.getEngine().getView();
        setContentView(webView);

        setIsFullscreen(true);

        ContentExposure contentExposure = new ContentExposure();
        contentExposure.setStorageManager(this.getSystemService(STORAGE_SERVICE));

        webView.addJavascriptInterface(contentExposure, "exposureInterface");
        webView.addJavascriptInterface(new GPlusAPIHandler(), "gameInterface");

        // Set by <content src="index.html" /> in config.xml
        //loadUrl(launchUrl);
        loadUrl("file:///android_asset/www/index.html");
    }

    @Override
    public void onResume(){
        super.onResume();
        enterFullscreen();
    }

    private void enterFullscreen(){
        if((getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) != 0){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(SYSTEM_UI_OPTIONS);

            ActionBar actionBar = getActionBar();
            if (actionBar != null) {
                actionBar.hide();
            }
        }
    }

    private void setSystemUiVisibilityChangeListener(){
        final View decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener(){
            @Override
            public void onSystemUiVisibilityChange(int visibility){
                if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                    decorView.setSystemUiVisibility(SYSTEM_UI_OPTIONS);
                }
            }
        });
    }

    public void setIsFullscreen(boolean isFullscreen){
        if(!isFullscreen) return;

        setSystemUiVisibilityChangeListener();
    }

    public static void setAPIActivity(GPlusAPIActivity gactivity){
        gPlusAPIActivity = gactivity;
    }

    public static GPlusAPIActivity getgPlusAPIActivity(){
        return gPlusAPIActivity;
    }
}
