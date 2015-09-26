package com.dev.batatasandroidclient.listeners;

import android.util.Log;
import android.view.View;

import com.dev.batatasandroidclient.constants.C;
import com.dev.batatasandroidclient.data.Product;

/**
 * Created by dev on 26.9.2015.
 */
public class AddToCartOnClickListener implements View.OnClickListener {
    private Product product;
    public AddToCartOnClickListener(Product product){
        this.product = product;
    }
    @Override
    public void onClick(View v) {
        Log.i(C.TAG, product.listDetails());
    }
}
