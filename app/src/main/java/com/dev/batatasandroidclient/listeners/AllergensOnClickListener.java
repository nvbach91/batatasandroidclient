package com.dev.batatasandroidclient.listeners;

import android.content.Context;
import android.view.View;

import com.dev.batatasandroidclient.view.Toaster;

/**
 * @author Nguyen Viet Bach
 * Created by dev on 27.9.2015.
 */
public class AllergensOnClickListener implements View.OnClickListener {
    private final Context context;
    public AllergensOnClickListener(Context context) {
        this.context = context;
    }
    @Override
    public void onClick(View v) {
        Toaster.show(context, "Allergens");
    }
}
