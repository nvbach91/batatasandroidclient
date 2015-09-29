package com.dev.batatasandroidclient.view;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.dev.batatasandroidclient.R;

/**
 * @author Nguyen Viet Bach
 *         Created by dev on 27.9.2015.
 */
public class SplashScreen extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //View decorView = getWindow().getDecorView();
        //int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        //decorView.setSystemUiVisibility(uiOptions);
        ActionBar actionBar = getActionBar();
        actionBar.hide();

        setContentView(R.layout.splash_screen);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
