package com.dev.batatasandroidclient.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

/**
 * Created by dev on 27.9.2015.
 */
public class DetailsDialog extends Dialog {
    public DetailsDialog(Context context) {
        super(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(255, 255, 255, 255)));
        getWindow().setDimAmount(0);
    }
}
