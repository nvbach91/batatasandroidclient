package com.dev.batatasandroidclient.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.batatasandroidclient.R;
import com.dev.batatasandroidclient.data.Product;
import com.dev.batatasandroidclient.listeners.AddToCartOnClickListener;
import com.dev.batatasandroidclient.listeners.AllergensOnClickListener;

/**
 * @author Nguyen Viet Bach
 * Created by dev on 27.9.2015.
 */
public class DetailsDialog extends Dialog {

    private final Product product;
    private final Activity activity;

    public DetailsDialog(Activity activity, Product product) {
        super(activity);
        this.activity = activity;
        this.product = product;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(255, 255, 255, 255)));
        getWindow().setDimAmount(0.7f);
        getWindow().getAttributes().windowAnimations = R.style.DetailsDialogAnimation;

        ImageView addToCart = (ImageView) this.findViewById(R.id.add_to_cart);
        addToCart.setOnClickListener(new AddToCartOnClickListener(activity, product));

        TextView allergens = (TextView) this.findViewById(R.id.allergens);
        allergens.setOnClickListener(new AllergensOnClickListener(getContext()));
    }



    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.dismiss();
        return true;
    }
}
