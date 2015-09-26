package com.dev.batatasandroidclient;

import android.app.Activity;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private ListView mainList;
    private ProductsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainList = (ListView) findViewById(R.id.mainListView);
        requestData();
    }

    private void requestData() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = C.BASEURL + C.FOODPATH;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        populateListView2(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(C.TAG, error.getMessage());
            }
        });
        queue.add(stringRequest);
    }

    private void populateListView2(String response) {
        try {
            JSONArray productsArray = new JSONObject(response).getJSONArray("products");
            List<Product> products = new ArrayList<>();
            for (int i = 0; i < productsArray.length(); i++) {
                JSONObject t = productsArray.getJSONObject(i);

                String name_en = t.getString("name_en");
                Integer price = t.getInt("price");
                String imageName = t.getString("images");
                String description = t.getString("description_en");
                String allergens = t.getString("alergens");
                Product product = new Product(name_en, price, imageName, description, allergens);
                products.add(product);
            }
            adapter = new ProductsAdapter(this, products);
            mainList.setAdapter(adapter);
            mainList.setOnItemClickListener(new ProductOnClickListener(MainActivity.this));
        } catch (Exception e) {
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                return true;
            case R.id.clear_cache:
                adapter.getImageLoader().clearCache();
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