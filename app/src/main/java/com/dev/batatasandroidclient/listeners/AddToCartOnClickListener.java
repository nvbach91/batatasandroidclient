package com.dev.batatasandroidclient.listeners;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;

import com.dev.batatasandroidclient.FragmentCommunicator;
import com.dev.batatasandroidclient.R;
import com.dev.batatasandroidclient.constants.C;
import com.dev.batatasandroidclient.data.Product;
import com.dev.batatasandroidclient.view.Toaster;

/**
 * @author Nguyen Viet Bach
 *         Created by dev on 26.9.2015.
 */
public class AddToCartOnClickListener implements View.OnClickListener {
    private final Product product;
    private final Activity activity;

    public AddToCartOnClickListener(Activity activity, Product product) {
        this.product = product;
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        FragmentCommunicator fc = (FragmentCommunicator) activity;
        fc.addToCart(product);
        Toaster.show(activity, '"' + product.getName(C.LANG) + '"'
                + C.getLabelAddedToCart(C.LANG)
                + " (" + product.getCount() + ")");
    }
}
