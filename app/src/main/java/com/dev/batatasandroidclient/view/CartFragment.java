package com.dev.batatasandroidclient.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dev.batatasandroidclient.R;
import com.dev.batatasandroidclient.constants.C;
import com.dev.batatasandroidclient.data.Product;

/**
 * @author Nguyen Viet Bach
 *         Created by dev on 26.9.2015.
 */
public class CartFragment extends Fragment {
    private View cartView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        cartView = inflater.inflate(R.layout.fragment_cart, container, false);
        return cartView;
    }

    public void addToCart(Product product){
        LinearLayout ll = (LinearLayout) cartView.findViewById(R.id.fragment_cart);
        CartProductView cpv = new CartProductView(getActivity());
        cpv.init(product);
        ll.addView(cpv);
    }
}
