package com.dev.batatasandroidclient.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.dev.batatasandroidclient.R;
import com.dev.batatasandroidclient.adapters.CartProductsAdapter;
import com.dev.batatasandroidclient.constants.C;
import com.dev.batatasandroidclient.data.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nguyen Viet Bach
 *         Created by dev on 26.9.2015.
 */
public class CartFragment extends Fragment {

    private CartProductsAdapter adapter;
    private Integer totalPrice;
    private TextView totalPriceTV;
    private List<Product> cart;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        totalPrice = 0;
        cart = new ArrayList<>();

        View cartView = inflater.inflate(R.layout.fragment_cart, container, false);
        ListView cartListView = (ListView) cartView.findViewById(R.id.cart_list_view);

        View header = inflater.inflate(R.layout.cart_header, null);
        View footer = inflater.inflate(R.layout.cart_footer, null);

        totalPriceTV = (TextView) footer.findViewById(R.id.total);
        setTotalPrice(totalPrice);

        cartListView.addHeaderView(header);
        cartListView.addFooterView(footer);

        adapter = new CartProductsAdapter(getActivity(), cartListView, cart);
        cartListView.setAdapter(adapter);

        return cartView;
    }

    public void addToCart(Product product) {
        totalPrice += product.getPrice();
        setTotalPrice(totalPrice);
        if (cart.contains(product)) {
            //cart.get(cart.indexOf(product)).addCount();
            product = cart.get(cart.indexOf(product));
            product.addCount();
            //product.addCount();
        } else {
            cart.add(product);
        }
        adapter.notifyDataSetChanged();
    }

    private void setTotalPrice(Integer newPrice) {
        totalPriceTV.setText(newPrice + " " + C.CURRENCY);
    }

    public void notifyAdapter() {
        adapter.notifyDataSetChanged();
    }

    public boolean hasEmptyCart() {
        return cart.isEmpty();
    }
}
