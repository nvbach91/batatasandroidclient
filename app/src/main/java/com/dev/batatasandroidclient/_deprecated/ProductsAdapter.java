package com.dev.batatasandroidclient._deprecated;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.batatasandroidclient.R;
import com.dev.batatasandroidclient.constants.C;
import com.dev.batatasandroidclient.data.Product;

import java.util.List;

/**
 * Created by dev on 25.9.2015.
 */
public class ProductsAdapter extends ArrayAdapter<Product> {
    public ProductsAdapter(Context context, List<Product> products) {
        super(context, R.layout.product_view, products);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View customView = layoutInflater.inflate(R.layout.product_view, parent, false);

        Product product = getItem(position);
        TextView name = (TextView) customView.findViewById(R.id.name);
        TextView price = (TextView) customView.findViewById(R.id.price);
        ImageView image = (ImageView) customView.findViewById(R.id.image);

        name.setText(product.getName(C.LANG));
        price.setText(product.getPriceString());
        //image.setImageBitmap(product.getImageBitmap());
        return customView;
    }


}
