package com.dev.batatasandroidclient.data;

import com.dev.batatasandroidclient.constants.C;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Nguyen Viet Bach
 *         Created by dev on 27.9.2015.
 */
public class Product {

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

    public Product(JSONObject product) throws JSONException {
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
    }

    public String print() {
        return "{\"name_en\": \"" + name_en + "\", "
                + "\"price\": " + price + "}";
    }

    public String getPrice() {
        return price + " " + C.CURRENCY;
    }

    public String getImageName() {
        return imageName;
    }


    public String getAllergens() {
        return C.getLabelAllergens(C.LANGUAGE) + (allergens == "null" ? "none" : allergens.replaceAll(",", ", "));
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
            case "en":
                return name_en;
            default:
                return name_en;
        }
    }

    public String getDescription(String lang) {
        switch (lang) {
            case "cs":
                return description_cz;
            case "en":
                return description_en;
            default:
                return description_en;
        }
    }
}
