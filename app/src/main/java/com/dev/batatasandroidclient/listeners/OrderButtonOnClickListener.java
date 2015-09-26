package com.dev.batatasandroidclient.listeners;

import android.view.View;

import com.dev.batatasandroidclient.data.Product;

/**
 * Created by dev on 26.9.2015.
 */
public class OrderButtonOnClickListener implements View.OnClickListener {
    private Product product;
    public OrderButtonOnClickListener(Product product){
        this.product = product;
    }
    @Override
    public void onClick(View v) {

    }
}
