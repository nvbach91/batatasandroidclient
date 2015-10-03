package com.dev.batatasandroidclient;

import com.dev.batatasandroidclient.data.Product;

/**
 * @author Nguyen Viet Bach
 *         Created by dev on 25.9.2015.
 */
public interface FragmentCommunicator {
    void reconnect();

    void addToCart(Product product);

    void clearAnim();

    void setMenuItemsEnabled(boolean state);
}
