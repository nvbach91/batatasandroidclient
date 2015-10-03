package com.dev.batatasandroidclient.listeners;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.batatasandroidclient.R;
import com.dev.batatasandroidclient.cache.FileCache;
import com.dev.batatasandroidclient.constants.C;
import com.dev.batatasandroidclient.data.Product;
import com.dev.batatasandroidclient.util.Utils;
import com.dev.batatasandroidclient.view.DetailsDialog;

import java.io.File;

/**
 * @author Nguyen Viet Bach
 *         Created by dev on 27.9.2015.
 */
public class ProductOnClickListener implements AdapterView.OnItemClickListener {
    private final Activity activity;

    public ProductOnClickListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Product product = (Product) parent.getItemAtPosition(position);
        DetailsDialog dialog = new DetailsDialog(activity, product);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_details);

        ImageView image = (ImageView) dialog.findViewById(R.id.image);
        TextView name = (TextView) dialog.findViewById(R.id.name);
        TextView description = (TextView) dialog.findViewById(R.id.description);
        TextView allergens = (TextView) dialog.findViewById(R.id.allergens);
        TextView price = (TextView) dialog.findViewById(R.id.price);

        FileCache fileCache = new FileCache(activity);
        File f = fileCache.getFile(C.BASEURL + C.IMGPATH + product.getImageName());
        Bitmap bitmap = Utils.decodeFile(f);
        image.setImageBitmap(bitmap);

        name.setText(product.getName(C.LANG));
        description.setText(product.getDescription(C.LANG));
        allergens.setText(product.getAllergens());
        price.setText(product.getPriceString());

        dialog.show();
    }


}
