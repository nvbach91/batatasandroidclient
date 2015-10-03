package com.dev.batatasandroidclient.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dev.batatasandroidclient.FragmentCommunicator;
import com.dev.batatasandroidclient.R;
import com.dev.batatasandroidclient.adapters.ProductsAdapter;
import com.dev.batatasandroidclient.adapters.NoConnectionAdapter;
import com.dev.batatasandroidclient.constants.C;
import com.dev.batatasandroidclient.data.Product;
import com.dev.batatasandroidclient.listeners.ProductOnClickListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nguyen Viet Bach
 *         Created by dev on 25.9.2015.
 */
public class MainFragment extends Fragment {

    private ListView mainList;
    private RequestQueue queue;
    private ProductsAdapter productsAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_main, container, false);
        mainList = (ListView) mainView.findViewById(R.id.main_list_view);
        queue = Volley.newRequestQueue(getActivity());

        splash();

        init();

        return mainView;
    }

    private void splash() {
        Intent splashIntent = new Intent(getActivity(), SplashScreen.class);
        getActivity().startActivityForResult(splashIntent, C.SPLASH_ACTIVITY_CODE);
    }

    public void init() {
        String url = C.BASEURL + C.FOODPATH;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        populateListView(response);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                getActivity().finishActivity(C.SPLASH_ACTIVITY_CODE);
                                FragmentCommunicator fc = (FragmentCommunicator) getActivity();
                                fc.clearAnim();
                                fc.setMenuItemsEnabled(true);
                            }
                        }, 2000);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toaster.show(getActivity(), getResources().getString(R.string.connection_error, Toast.LENGTH_LONG));
                mainList.setAdapter(new NoConnectionAdapter(getActivity()));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getActivity().finishActivity(C.SPLASH_ACTIVITY_CODE);
                        FragmentCommunicator fc = (FragmentCommunicator) getActivity();
                        fc.clearAnim();
                        fc.setMenuItemsEnabled(false);
                    }
                }, 2000);
            }
        });
        queue.add(stringRequest);
    }

    public void populateListView(String response) {
        List<Product> products = parseProducts(response);
        productsAdapter = new ProductsAdapter(getActivity(), products);
        mainList.setAdapter(productsAdapter);
        mainList.setOnItemClickListener(new ProductOnClickListener(getActivity()));
    }

    private List<Product> parseProducts(String response) {
        List<Product> products = new ArrayList<>();
        try {
            JSONArray productsArray = new JSONObject(response).getJSONArray("products");
            for (int i = 0; i < productsArray.length(); i++) {
                JSONObject p = productsArray.getJSONObject(i);
                Product product = new Product(getActivity(), p);
                if (product.getDisable() == 0) {
                    products.add(product);
                }
            }
        } catch (Exception e) {
            Log.i(C.TAG, e.getMessage());
        }
        return products;
    }

    public void evacuate() {
        mainList.setAdapter(null);
    }

    public void clearCache() {
        if (productsAdapter != null)
            productsAdapter.getImageLoader().clearCache();
    }

    public void notifyAdapter() {
        productsAdapter.notifyDataSetChanged();
    }
}
