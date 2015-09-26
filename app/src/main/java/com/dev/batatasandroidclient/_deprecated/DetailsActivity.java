package com.dev.batatasandroidclient._deprecated;

import android.app.Activity;
import android.os.Bundle;

import com.dev.batatasandroidclient.R;

/**
 * Created by dev on 26.9.2015.
 */
public class DetailsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_details);
/*
        Bundle bundle = getIntent().getExtras();
        Product product = bundle.getParcelable("com.dev.batatasandroidclient.Product");*/

    }
}
