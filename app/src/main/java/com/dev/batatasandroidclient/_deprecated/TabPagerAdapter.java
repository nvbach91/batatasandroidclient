package com.dev.batatasandroidclient._deprecated;

/**
 * Created by dev on 28.9.2015.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.dev.batatasandroidclient.view.CartFragment;
import com.dev.batatasandroidclient.view.MainFragment;

public class TabPagerAdapter extends FragmentPagerAdapter {

    private MainFragment mainFragment;
    private CartFragment cartFragment;

    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
        mainFragment = new MainFragment();
        cartFragment = new CartFragment();
        mainFragment.setRetainInstance(true);
        cartFragment.setRetainInstance(true);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return mainFragment;
            case 1:
                return cartFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    public MainFragment getMainFragment() {
        return mainFragment;
    }

    public CartFragment getCartFragment() {
        return cartFragment;
    }
}