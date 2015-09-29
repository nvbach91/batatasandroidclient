package com.dev.batatasandroidclient.view;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * @author Nguyen Viet Bach
 *         Created by dev on 27.9.2015.
 */
public class Toaster {
    public static void show(Context context, String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 25);
        toast.show();
    }

    public static void show(Context context, String message, int toastLength) {
        Toast toast = Toast.makeText(context, message, toastLength);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 25);
        toast.show();
    }
}
