package com.dev.batatasandroidclient.listeners;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.dev.batatasandroidclient.data.Product;

/**
 * Created by dev on 26.9.2015.
 */
public class ProductOnClickListener implements AdapterView.OnItemClickListener {
    private Context context;
    public ProductOnClickListener(Context context){
        this.context = context;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String price = ((Product) parent.getItemAtPosition(position)).getPrice();
        Toast.makeText(context, price, Toast.LENGTH_SHORT).show();
    }
}
