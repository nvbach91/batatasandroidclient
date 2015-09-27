package com.dev.batatasandroidclient.view;

import android.app.Activity;
import android.os.Bundle;

import com.dev.batatasandroidclient.R;

/**
 * @author Nguyen Viet Bach
 *         Created by dev on 27.9.2015.
 */
public class SplashScreen extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
