package com.ruizrabadanjuanpedro.pmdm_tarea2;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Locale;

/**
 * Helper para la ayuda de las funciones de tomar el idioma almacenado en el dispositivo
 * Y establecer la preferencia seleccionada por el usuario
 */
public class PreferencesHelper {

    private static final String PREFS_NAME = "preferences";
    private static final String KEY_LANG = "idioma";

    /**
     * Método para almacenar en el dispositivo la preferencia de idioma del usuario
     * @param context
     * @param language Clave ISO del idioma a almacenar, por ejemplo "es", "en".
     */
    public static void saveLanguage(Context context, String language) {

        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_LANG, language);
        editor.apply();

        Locale locale = new Locale(language);
        Locale.setDefault(locale);

    }

    /**
     * Método para obtener la preferencia del usuario almacenada sobre el idioma de la aplicación
     * @param context
     * @return String Código ISO del idioma almacenado en el dispositivo
     */
    public static String getLanguage(Context context) {

        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(KEY_LANG, null);

    }
}
