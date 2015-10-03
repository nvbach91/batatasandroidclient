package com.dev.batatasandroidclient.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.dev.batatasandroidclient.R;
import com.dev.batatasandroidclient.listeners.ReconnectOnClickListener;

/**
 * @author Nguyen Viet Bach
 *         Created by dev on 25.9.2015.
 */
public class NoConnectionAdapter extends BaseAdapter {
    private static LayoutInflater inflater = null;
    private final Activity activity;

    public NoConnectionAdapter(Activity activity) {
        this.activity = activity;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            view = inflater.inflate(R.layout.no_connection_view, null);
        }

        /*ImageView image = (ImageView) view.findViewById(R.id.reconnect);
        image.setOnClickListener(new ReconnectOnClickListener(activity));*/

        return view;
    }
}