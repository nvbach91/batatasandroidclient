package com.dev.batatasandroidclient.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;

import com.dev.batatasandroidclient.R;
import com.dev.batatasandroidclient.constants.C;
import com.dev.batatasandroidclient.view.CartFragment;
import com.dev.batatasandroidclient.view.MainFragment;

/**
 * @author Nguyen Viet Bach
 *         Created by dev on 28.9.2015.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final Context context;

    private final int PAGE_COUNT = 2;
    private String tabTitles[];
    private MainFragment mainFragment;
    private CartFragment cartFragment;


    public ViewPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        tabTitles = new String[]{
                this.context.getResources().getString(R.string.shop),
                this.context.getResources().getString(R.string.cart)
        };
        mainFragment = new MainFragment();
        cartFragment = new CartFragment();
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return mainFragment;
            case 1:
                return cartFragment;
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    public MainFragment getMainFragment() {
        return mainFragment;
    }

    public CartFragment getCartFragment() {
        return cartFragment;
    }
}
