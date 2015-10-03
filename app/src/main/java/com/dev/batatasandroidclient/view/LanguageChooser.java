package com.dev.batatasandroidclient.view;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.dev.batatasandroidclient.R;
import com.dev.batatasandroidclient.constants.C;

/**
 * @author Nguyen Viet Bach
 *         Created by dev on 25.9.2015.
 */
public class LanguageChooser extends Activity {
    private ImageView czech;
    private ImageView english;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.language_chooser);


        czech = (ImageView) findViewById(R.id.czech_language);
        english = (ImageView) findViewById(R.id.english_language);

        czech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPreferredLanguage("cs");
                finish();
            }
        });

        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPreferredLanguage("en");
                finish();
            }
        });
    }

    private void setPreferredLanguage(String lang) {
        SharedPreferences sp = getSharedPreferences("config", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("language", lang);
        C.LANG = lang;
        editor.apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
