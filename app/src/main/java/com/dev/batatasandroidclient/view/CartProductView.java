package com.dev.batatasandroidclient.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev.batatasandroidclient.R;
import com.dev.batatasandroidclient.constants.C;
import com.dev.batatasandroidclient.data.Product;

/**
 * Created by dev on 29.9.2015.
 */
public class CartProductView extends RelativeLayout {
    private Product product;

    public CartProductView(Context context) {
        super(context);
    }

    public void init(Product product){
        this.product = product;
        init();
    }

    private void init(){
        inflate(getContext(), R.layout.cart_product, this);
        TextView name = (TextView) findViewById(R.id.name);
        TextView count = (TextView) findViewById(R.id.count);
        TextView total = (TextView) findViewById(R.id.total);

        name.setText(product.getName(C.LANG));
    }
}
