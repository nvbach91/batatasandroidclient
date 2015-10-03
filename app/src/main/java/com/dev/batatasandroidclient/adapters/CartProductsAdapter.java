package com.dev.batatasandroidclient.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.dev.batatasandroidclient.R;
import com.dev.batatasandroidclient.constants.C;
import com.dev.batatasandroidclient.data.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nguyen Viet Bach
 *         Created by dev on 1.10.2015.
 */
public class CartProductsAdapter extends BaseAdapter {

    private final Activity activity;
    private final LayoutInflater inflater;
    private final List<Product> cart;
    private final ListView cartListView;

    public CartProductsAdapter(Activity activity, ListView cartListView, List<Product> cart) {
        this.cart = cart;
        this.activity = activity;
        this.cartListView = cartListView;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return cart.size();
    }

    @Override
    public Object getItem(int position) {
        return cart.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (cart.size() > 0) {
            Product product = cart.get(position);
            View view = convertView;
            if (convertView == null) {
                view = inflater.inflate(R.layout.cart_product_view, null);
            }


            TextView name = (TextView) view.findViewById(R.id.name);
            TextView count = (TextView) view.findViewById(R.id.count);
            TextView total = (TextView) view.findViewById(R.id.total);

            name.setText(product.getName(C.LANG));
            count.setText(product.getCount());
            total.setText(product.getPriceString());

            return view;
        }
        return null;
    }
}
