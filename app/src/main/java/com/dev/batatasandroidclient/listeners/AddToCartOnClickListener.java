package com.dev.batatasandroidclient.listeners;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.dev.batatasandroidclient.constants.C;
import com.dev.batatasandroidclient.data.Product;
import com.dev.batatasandroidclient.view.Toaster;

/**
 * @author Nguyen Viet Bach
 *         Created by dev on 26.9.2015.
 */
public class AddToCartOnClickListener implements View.OnClickListener {
    private final Product product;
    private final Context context;

    public AddToCartOnClickListener(Context context, Product product) {
        this.product = product;
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        Toaster.show(context, product.getName(C.LANGUAGE) + C.getLabelAddedToCart(C.LANGUAGE));
    }
}
