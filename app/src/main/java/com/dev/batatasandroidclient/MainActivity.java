package com.dev.batatasandroidclient;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dev.batatasandroidclient.constants.C;
import com.dev.batatasandroidclient.data.Product;
import com.dev.batatasandroidclient.listeners.ProductOnClickListener;
import com.dev.batatasandroidclient.adapters.ProductsAdapter;
import com.dev.batatasandroidclient.view.LanguageChooser;
import com.dev.batatasandroidclient.view.SplashScreen;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nguyen Viet Bach
 *         Created by dev on 27.9.2015.
 */

public class MainActivity extends Activity {
    private ListView mainList;
    private ProductsAdapter adapter;
    private RequestQueue queue;
    private String savedResponse;
    private SharedPreferences sp;
    private Menu theMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainList = (ListView) findViewById(R.id.mainListView);
        queue = Volley.newRequestQueue(this);

        sp = getSharedPreferences("config", Context.MODE_PRIVATE);

        if (sp.getString("language", "") == "") {
            chooseLanguage();
        } else {
            C.LANGUAGE = sp.getString("language", "");
        }

        splash();

        init();
    }

    private void chooseLanguage() {
        Intent languageChooserIntent = new Intent(MainActivity.this, LanguageChooser.class);
        startActivityForResult(languageChooserIntent, C.LANGUAGECHOOSER_ACTIVITY_CODE);
    }

    private void splash() {
        Intent splashIntent = new Intent(MainActivity.this, SplashScreen.class);
        startActivityForResult(splashIntent, C.SPLASH_ACTIVITY_CODE);
    }

    private void init() {
        String url = C.BASEURL + C.FOODPATH;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        savedResponse = response;
                        populateListView(response);
                        finishActivity(C.SPLASH_ACTIVITY_CODE);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(C.TAG, error.getMessage());
            }
        });
        queue.add(stringRequest);
    }

    private void populateListView(String response) {
        List<Product> products = getProducts(response);
        adapter = new ProductsAdapter(this, products);
        mainList.setAdapter(adapter);
        mainList.setOnItemClickListener(new ProductOnClickListener(MainActivity.this));
    }

    private List<Product> getProducts(String response) {
        List<Product> products = new ArrayList<>();
        try {
            JSONArray productsArray = new JSONObject(response).getJSONArray("products");
            for (int i = 0; i < productsArray.length(); i++) {
                JSONObject p = productsArray.getJSONObject(i);
                Product product = new Product(p);
                products.add(product);
            }
        } catch (Exception e) {
            Log.i(C.TAG, e.getMessage());
        }
        return products;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        theMenu = menu;
        if (C.LANGUAGE == "cs") {
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
                adapter.getImageLoader().clearCache();
                init();
                return true;
            case R.id.reset_settings:
                getSharedPreferences("config", 0).edit().clear().commit();
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                return true;
            case R.id.change_language:
                if (C.LANGUAGE == "cs") {
                    C.LANGUAGE = "en";
                    sp.edit().putString("language", "en").commit();
                    onCreateOptionsMenu(theMenu);
                } else {
                    C.LANGUAGE = "cs";
                    sp.edit().putString("language", "cs").commit();
                    onCreateOptionsMenu(theMenu);
                }
                populateListView(savedResponse);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onDestroy() {
        mainList.setAdapter(null);
        super.onDestroy();
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