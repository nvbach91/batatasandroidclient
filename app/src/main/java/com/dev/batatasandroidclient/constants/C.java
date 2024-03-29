package com.dev.batatasandroidclient.constants;

/**
 * @author Nguyen Viet Bach
 *         Created by dev on 25.9.2015.
 *         Contains CONSTANTS, GLOBAL VARIABLES
 */
public final class C {
    public static String LANG = "en";

    public static final String BASEURL = "https://www.batatas.cz";
    public static final String IMGPATH = "/images/products/";
    public static final String FOODPATH = "/api/api/products/search/food/";
    public static final String PLACESPATH = "/api/api/places/";

    public static final int SPLASH_ACTIVITY_CODE = 12;

    public static final String CURRENCY = "Kč";
    public static final String TAG = "NVB";

    public static String getLabelNoAllergens(String lang) {
        switch (lang) {
            case "cs":
                return "žádné";
            default:
                return "none";
        }
    }

    public static String getLabelAllergens(String lang) {
        switch (lang) {
            case "cs":
                return "Alergeny: ";
            default:
                return "Allergens: ";
        }
    }

    public static String getLabelAddedToCart(String lang) {
        switch (lang) {
            case "cs":
                return " vloženo do košíku";
            default:
                return " added to cart";
        }
    }
}
