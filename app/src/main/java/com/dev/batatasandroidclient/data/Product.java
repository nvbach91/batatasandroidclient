package com.dev.batatasandroidclient.data;

import android.content.Context;

import com.dev.batatasandroidclient.R;
import com.dev.batatasandroidclient.constants.C;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Nguyen Viet Bach
 *         Created by dev on 27.9.2015.
 */
public class Product {

    private final Context context;

    private final Integer id;
    private final Integer type;

    private final String name_cz;
    private final String name_en;
    private final String name_vn;

    private final Integer price;
    private final Object price_per_unit;

    private final String description_cz;
    private final String description_en;

    private final String allergens;
    private final String imageName;
    private final String tags;
    private final Integer dishType;
    private final String number;
    private final Integer disable;
    private final String validFrom;

    private Integer count;

    public Product(Context context, JSONObject product) throws JSONException {
        this.context = context;
        this.id = product.getInt("id");
        this.type = product.getInt("type");

        this.name_cz = product.getString("name_cz").trim();
        this.name_en = product.getString("name_en").trim();
        this.name_vn = product.getString("name_vn").trim();

        this.price = product.getInt("price");
        this.price_per_unit = product.get("price_per_unit") == null ? 0 : product.get("price_per_unit");

        this.description_cz = product.getString("description_cz").trim();
        this.description_en = product.getString("description_en").trim();

        this.allergens = product.getString("alergens").trim();
        this.imageName = product.getString("images").trim();

        this.tags = product.getString("tags").trim();
        this.dishType = product.getInt("dishtype");
        this.number = product.getString("number").trim();
        this.disable = product.getInt("disable");
        this.validFrom = product.getString("validfrom").trim();

        this.count = 1;
    }

    public Integer getPrice() {
        return price;
    }

    public String getPriceString() {
        return price + " " + C.CURRENCY;
    }

    public String getImageName() {
        return imageName;
    }


    public String getAllergens() {
        return C.getLabelAllergens(C.LANG) + (allergens.equals("null") ? C.getLabelNoAllergens(C.LANG) : allergens.replaceAll(",", ", "));
    }

    public Integer getId() {
        return id;
    }

    public Integer getType() {
        return type;
    }

    public Object getPrice_per_unit() {
        return price_per_unit;
    }

    public String getTags() {
        return tags;
    }

    public Integer getDishType() {
        return dishType;
    }

    public String getNumber() {
        return number;
    }

    public Integer getDisable() {
        return disable;
    }

    public String getValidFrom() {
        return validFrom;
    }

    public String getName(String lang) {
        switch (lang) {
            case "cs":
                return name_cz;
            default:
                return name_en;
        }
    }

    public String getDescription(String lang) {
        switch (lang) {
            case "cs":
                return description_cz;
            default:
                return description_en;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return !(id != null ? !id.equals(product.id) : product.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public String getCount() {
        return count.toString();
    }

    public void addCount() {
        count++;
    }
}
