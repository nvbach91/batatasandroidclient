package com.dev.batatasandroidclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.dev.batatasandroidclient.adapters.ViewPagerAdapter;
import com.dev.batatasandroidclient.constants.C;
import com.dev.batatasandroidclient.data.Product;
import com.dev.batatasandroidclient.view.CartFragment;
import com.dev.batatasandroidclient.view.MainFragment;

/**
 * @author Nguyen Viet Bach
 *         Created by dev on 27.9.2015.
 */

public class MainActivity extends FragmentActivity implements FragmentCommunicator {
    private Menu theMenu;

    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private MainFragment mainFragment;
    private CartFragment cartFragment;

    //private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        C.LANG = getResources().getString(R.string.language);
        //sp = getSharedPreferences("preferences", Context.MODE_PRIVATE);

        //setPreferredLanguage();

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPagerAdapter = new ViewPagerAdapter(MainActivity.this, getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);

        mainFragment = viewPagerAdapter.getMainFragment();
        cartFragment = viewPagerAdapter.getCartFragment();
    }

    /*private void setPreferredLanguage() {
        C.LANG = getResources().getConfiguration().locale.getLanguage();
        C.LANG = sp.getString("language", C.LANG);
        sp.edit().putString("language", C.LANG).apply();
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        theMenu = menu;
        if (C.LANG.equals("cs")) {
            getMenuInflater().inflate(R.menu.menu_main_cs, menu);
        } else {
            getMenuInflater().inflate(R.menu.menu_main, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.reload:
                mainFragment.evacuate();
                mainFragment.clearCache();
                mainFragment.init();
                return true;
            case R.id.restart_app:
                //mainFragment.resetSettings();
                //mainFragment.clearCache();
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                return true;
            case R.id.change_language:
                mainFragment.changeLanguage();
                onCreateOptionsMenu(theMenu);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
}

/*
    // these two methods successfully use volley lib to fetch images,
    // but does not refresh list view once finished fetching
    // now using ImageLoader class
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
                            String price = ((Product) parent.getItemAtPosition(position)).getPrice();
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