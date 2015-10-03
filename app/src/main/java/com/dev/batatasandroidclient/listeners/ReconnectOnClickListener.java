package com.dev.batatasandroidclient.listeners;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;

import com.dev.batatasandroidclient.FragmentCommunicator;


/**
 * @author Nguyen Viet Bach
 *         Created by dev on 25.9.2015.
 */
public class ReconnectOnClickListener implements AdapterView.OnClickListener {
    private final Activity activity;

    public ReconnectOnClickListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        FragmentCommunicator communicator = (FragmentCommunicator) activity;
        communicator.reconnect();
    }
}
