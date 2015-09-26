package com.dev.batatasandroidclient.data;

import android.app.Activity;
import android.graphics.Bitmap;

import com.dev.batatasandroidclient.constants.C;

/**
 * Created by dev on 25.9.2015.
 */
public class Product extends Activity {

    private final String name_en;
    private final Integer price;
    private final String imageName;

    public Product(String name_en, Integer price, String imageName) {
        this.name_en = name_en.trim();
        this.price = price;
        this.imageName = imageName.trim();
    }

    public String getPrice() {
        return price + " " + C.CURRENCY;
    }

    public String getName_en() {
        return name_en;
    }

    public String getImageName() {
        return imageName;
    }
}
