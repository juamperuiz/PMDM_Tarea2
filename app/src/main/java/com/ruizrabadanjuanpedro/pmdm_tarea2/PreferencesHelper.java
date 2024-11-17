package com.ruizrabadanjuanpedro.pmdm_tarea2;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import java.util.Locale;

public class PreferencesHelper {

    private static final String PREFS_NAME = "preferences";
    private static final String KEY_LANG = "idioma";

    public static void saveLanguage(Context context, String language) {

        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_LANG, language);
        editor.apply();

        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        // Leemos la información almacenada en el dispositivo y la configuramos como seleccionada
        //Resources res = context.getResources();
        //Configuration conf = res.getConfiguration();
        //DisplayMetrics dm = res.getDisplayMetrics();
        //conf.locale = Locale.forLanguageTag(language);
        //res.updateConfiguration(conf, dm);

    }

    public static String getLanguage(Context context) {

        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(KEY_LANG, null);

    }
}
