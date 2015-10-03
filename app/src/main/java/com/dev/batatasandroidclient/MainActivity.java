package com.dev.batatasandroidclient;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.dev.batatasandroidclient.adapters.ViewPagerAdapter;
import com.dev.batatasandroidclient.constants.C;
import com.dev.batatasandroidclient.data.Product;
import com.dev.batatasandroidclient.view.CartFragment;
import com.dev.batatasandroidclient.view.MainFragment;
import com.dev.batatasandroidclient.view.Toaster;

/**
 * @author Nguyen Viet Bach
 *         Created by dev on 27.9.2015.
 */

public class MainActivity extends FragmentActivity implements FragmentCommunicator {
    private Menu theMenu;

    private MainFragment mainFragment;
    private CartFragment cartFragment;

    private MenuItem reloadMenuItem;
    private ImageView reloadIcon;
    private Animation rotation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        C.LANG = getResources().getString(R.string.language);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        reloadIcon = (ImageView) inflater.inflate(R.layout.action_reload, null);
        rotation = AnimationUtils.loadAnimation(this, R.anim.image_rotate);
        rotation.setRepeatCount(Animation.INFINITE);

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(MainActivity.this, getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);

        mainFragment = viewPagerAdapter.getMainFragment();
        cartFragment = viewPagerAdapter.getCartFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        theMenu = menu;

        if (C.LANG.equals("en")) {
            getMenuInflater().inflate(R.menu.menu_main, menu);
        } else {
            getMenuInflater().inflate(R.menu.menu_main_cs, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.reload:
                if (cartFragment.hasEmptyCart()) {
                    reload();
                } else {
                }
                return true;
            case R.id.restart_app:
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                return true;
            case R.id.czech_language:
                C.LANG = "cs";
                changeLanguage();
                return true;
            case R.id.english_language:
                C.LANG = "en";
                changeLanguage();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void changeLanguage() {
        Toaster.show(MainActivity.this,
                getResources().getString(R.string.change_language_message),
                Toast.LENGTH_LONG);
        mainFragment.notifyAdapter();
        cartFragment.notifyAdapter();
        onCreateOptionsMenu(theMenu);
    }

    private void reload() {
        mainFragment.evacuate();
        mainFragment.clearCache();
        mainFragment.init();

        reloadIcon.startAnimation(rotation);

        reloadMenuItem = theMenu.findItem(R.id.reload);
        reloadMenuItem.setActionView(reloadIcon);

        setMenuItemsEnabled(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void reconnect() {
        mainFragment.init();
    }

    @Override
    public void addToCart(Product product) {
        cartFragment.addToCart(product);
    }

    @Override
    public void clearAnim() {
        if (reloadMenuItem != null) {
            reloadMenuItem.getActionView().clearAnimation();
            reloadMenuItem.setActionView(null);
            setMenuItemsEnabled(false);
        }
    }

    @Override
    public void setMenuItemsEnabled(boolean enabled) {
        theMenu.setGroupEnabled(R.id.menu_items, enabled);
    }
}

/*
    // these two methods successfully use volley lib to fetch images,
    // but does not refresh list view once finished fetching
    // now using ImageLoader class + volley
    private void populateListView(String response) {
        try {
            JSONArray productsArray = new JSONObject(response).getJSONArray("products");
            List<Product> products = new ArrayList<>();
            for (int i = 0; i < productsArray.length(); i++) {
                JSONObject t = productsArray.getJSONObject(i);
                String name_en = t.getString("name_en");
                Integer price = t.getInt("price");
                String imageName = t.getString("images");
                Product product = new Product(name_en, price, imageName);
                products.add(product);
                requestImage(product);
            }

            ListAdapter productsAdapter = new ProductsAdapter(this, products);
            mainList.setAdapter(productsAdapter);
            mainList.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String price = ((Product) parent.getItemAtPosition(position)).getPriceString();
                            Toast.makeText(MainActivity.this, price, Toast.LENGTH_LONG).show();
                        }
                    }
            );

        } catch (JSONException e) {
            Log.e(C.TAG, e.getMessage());
        }
    }

    private void requestImage(final Product product) {
        String url = C.BASEURL + C.IMGPATH + product.getImageName();
        ImageRequest request = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        product.setImageBitmap(bitmap);
                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        RequestQueueSingleton.getInstance(this).addToRequestQueue(request);
    }
*/