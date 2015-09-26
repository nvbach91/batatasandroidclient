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
    private final String description;
    private final String allergens;

    public Product(String name_en, Integer price, String imageName, String description, String allergens) {
        this.name_en = name_en.trim();
        this.price = price;
        this.imageName = imageName.trim();
        this.description = description.trim();
        this.allergens = allergens.trim();
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

    public String listDetails() {
        return   "{\"name_en\": \"" + name_en + "\", "
                + "\"price\": " + price + "}";
    }

    public String getDescription() {
        return description;
    }

    public String getAllergens() {
        return allergens;
    }
}
