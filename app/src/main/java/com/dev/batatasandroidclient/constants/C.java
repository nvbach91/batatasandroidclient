package com.dev.batatasandroidclient.constants;

/**
 * @author Nguyen Viet Bach
 *         Created by dev on 25.9.2015.
 *         Contains CONSTANTS
 */
public final class C {
    public static String LANGUAGE = "en";
    public static final int SPLASH_ACTIVITY_CODE = 12;
    public static final int LANGUAGECHOOSER_ACTIVITY_CODE = 21;

    public static final String getLabelAllergens(String lang) {
        switch(lang) {
            case "cs":
                return "Alergen č. ";
            default:
                return "Allergen # ";
        }
    }

    public static final String getLabelAddedToCart(String lang) {
        switch(lang) {
            case "cs":
                return " přidáno do košíku";
            default:
                return " added to cart";
        }
    }

    public static final String CURRENCY = "Kč";
    public static final String TAG = "NVB";
    public static final String BASEURL = "https://www.batatas.cz";
    public static final String IMGPATH = "/images/products/";
    public static final String FOODPATH = "/api/api/products/search/food/";
    public static final String PLACESPATH = "/api/api/places/";
}
