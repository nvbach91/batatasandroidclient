package com.dev.batatasandroidclient.data;

import android.app.Activity;
import android.graphics.Bitmap;
/**
 * Created by dev on 25.9.2015.
 */
public class Product extends Activity {

    private final String name_en;
    private final Integer price;
    private final String imageName;
    private Bitmap imageBitmap;

    public Product(String name_en, Integer price, String imageName) {
        this.name_en = name_en;
        this.price = price;
        this.imageName = imageName;
    }

    public String getPrice() {
        return price.toString() + " Kč";
    }

    public String getName_en() {
        return name_en;
    }

    public String getImageName() {
        return imageName;
    }

    public Bitmap getImageBitmap() {
        return imageBitmap;
    }

    public void setImageBitmap(Bitmap bm){
        this.imageBitmap = bm;
    }
}
