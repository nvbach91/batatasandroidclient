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
 * Created by dev on 28.9.2015.
 */
public class MainFragment extends Fragment {

    private ListView mainList;
    private RequestQueue queue;
    //private SharedPreferences sp;
    private ProductsAdapter adapter;
    private String savedResponse;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_main, container, false);
        mainList = (ListView) mainView.findViewById(R.id.mainListView);
        queue = Volley.newRequestQueue(getActivity());
        //sp = getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);

        /*if (sp.getString("language", "").equals("")) {
            chooseLanguage();
        } else {
            C.LANG = sp.getString("language", "");
        }*/

        splash();

        init();


        return mainView;
    }

    /*private void chooseLanguage() {
        Intent languageChooserIntent = new Intent(getActivity(), LanguageChooser.class);
        getActivity().startActivityForResult(languageChooserIntent, C.LANGUAGECHOOSER_ACTIVITY_CODE);
    }*/

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
                        savedResponse = response;
                        populateListView(response);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                getActivity().finishActivity(C.SPLASH_ACTIVITY_CODE);
                            }
                        }, 3000);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                savedResponse = "";
                Toaster.show(getActivity(), getResources().getString(R.string.connection_error, Toast.LENGTH_LONG));
                mainList.setAdapter(new NoConnectionAdapter(getActivity()));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getActivity().finishActivity(C.SPLASH_ACTIVITY_CODE);
                    }
                }, 3000);
            }
        });
        queue.add(stringRequest);
    }

    public void populateListView(String response) {
        List<Product> products = parseProducts(response);
        adapter = new ProductsAdapter(getActivity(), products);
        mainList.setAdapter(adapter);
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
        if (adapter != null)
            adapter.getImageLoader().clearCache();
    }

    /*public void resetSettings() {
        sp.edit().clear().apply();
    }*/

    public void repopulateListView() {
        if (!savedResponse.isEmpty())
            populateListView(savedResponse);
    }

    public void changeLanguage() {
        if (C.LANG.equals("cs")) {
            C.LANG = "en";
        } else {
            C.LANG = "cs";
        }
        repopulateListView();
    }
}
