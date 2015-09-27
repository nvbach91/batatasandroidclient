package com.dev.batatasandroidclient._deprecated;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by dev on 27.9.2015.
 */
public class Config extends Activity {
    public Config (){
    }
    public boolean setPreferredLanguage(String lang){
        SharedPreferences sp = getSharedPreferences("config", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("language", lang);
        return editor.commit();
    }
}
