package com.dev.batatasandroidclient.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.batatasandroidclient.R;
import com.dev.batatasandroidclient.constants.C;
import com.dev.batatasandroidclient.data.Product;
import com.dev.batatasandroidclient.listeners.AddToCartOnClickListener;
import com.dev.batatasandroidclient.net.ImageLoader;

import java.util.List;

/**
 * @author Nguyen Viet Bach
 *         Created by dev on 26.9.2015.
 */
public class ProductsAdapter extends BaseAdapter {

    private final Activity activity;
    private final List<Product> products;
    private final ImageLoader imageLoader;

    public ProductsAdapter(Activity a, List<Product> p) {
        activity = a;
        products = p;
        imageLoader = new ImageLoader(activity);
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Product getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Product product = products.get(position);
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.product_view, null);
        }

        TextView text = (TextView) view.findViewById(R.id.name);
        TextView price = (TextView) view.findViewById(R.id.price);
        ImageView image = (ImageView) view.findViewById(R.id.image);
        ImageView addToCart = (ImageView) view.findViewById(R.id.add_to_cart);

        text.setText(product.getName(C.LANG));
        price.setText(product.getPriceString());
        imageLoader.DisplayImage(C.BASEURL + C.IMGPATH + product.getImageName(), image);
        addToCart.setOnClickListener(new AddToCartOnClickListener(activity, product));

        return view;
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }
}
