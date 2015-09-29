package com.dev.batatasandroidclient;

import com.dev.batatasandroidclient.data.Product;

/**
 * Created by dev on 29.9.2015.
 */
public interface FragmentCommunicator {
    void reconnect();
    void addToCart(Product product);
}
