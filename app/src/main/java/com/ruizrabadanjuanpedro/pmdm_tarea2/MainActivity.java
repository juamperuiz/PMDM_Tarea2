package com.ruizrabadanjuanpedro.pmdm_tarea2;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.snackbar.Snackbar;
import com.ruizrabadanjuanpedro.pmdm_tarea2.databinding.ActivityMainBinding;

import java.util.Locale;

/**
 * Clase que representa la Actividad principal de la aplicación
 * @author Juampe Ruiz
 */
public class MainActivity extends AppCompatActivity {

    private ActionBarDrawerToggle toggle;
    private ActivityMainBinding binding;
    private NavController navController;
    private Dialog dialog;

    /**
     * Método ejecutado automáticamente en el inicio de la actividad
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Inicializamos la Splash Screen de bienvenida de la aplicación
        SplashScreen.installSplashScreen(this);

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configuramos el NavController
        FragmentManager fragmentManager = getSupportFragmentManager();
        NavHostFragment navHostFragment = (NavHostFragment) fragmentManager.findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
        navController.addOnDestinationChangedListener(this::onChangeView);

        // Configurar menú toggle
        configureToggleMenu();

        // Configurar la navegación
        configureNavigation();

        // Configurar el icono del menú en la ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Idioma por defecto
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String idiomaSeleccionado = sharedPreferences.getString("idioma", "");
        if (idiomaSeleccionado.isEmpty()) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("idioma", "es");
            editor.apply();
        }

        // Mensaje de bienvenida tras la splahscreen mediante el componente Snackbar
        Snackbar.make(findViewById(R.id.drawer_layout), getString(R.string.WelcomeToast), Snackbar.LENGTH_SHORT).show();

    }

    private void onChangeView(NavController navController, NavDestination navDestination, Bundle bundle) {

        if (toggle == null) { return; }

        if (navDestination.getId() == R.id.gameDetailFragment) {
            toggle.setDrawerIndicatorEnabled(false);
        } else {
            toggle.setDrawerIndicatorEnabled(true);
        }
    }

    /**
     *
     */
    private void configureToggleMenu() {

        // Configuramos el ActionBarDrawerToggle
        toggle = new ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open_drawer, R.string.close_drawer);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

    }

    /**
     *
     */
    private void configureNavigation() {

        NavigationUI.setupWithNavController(binding.navView, navController);

        // Manejar la selección de elementos del menú
        binding.navView.setNavigationItemSelectedListener(menuItem -> {
            if (menuItem.getItemId() == R.id.nav_host_fragment) {
                navController.navigate(R.id.nav_host_fragment); // Navegar al fragmento de inicio
            }

            if (menuItem.getItemId() == R.id.menu_about) {
                openAboutDialog();
            }

            if (menuItem.getItemId() == R.id.menu_language) {
                changeLanguage();
            }

            binding.drawerLayout.closeDrawers(); // Cerrar el menú
            return true;
        });

    }

    private void changeLanguage() {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String idiomaSeleccionado = sharedPreferences.getString("idioma", "");
        if (idiomaSeleccionado.isEmpty()) { idiomaSeleccionado = "en"; }

        switch (idiomaSeleccionado) {

            case "es":
                setLanguage("en");
                break;
            case "en":
                setLanguage("es");
                break;

        }

    }

    private void setLanguage(String lang) {

        // Establecemos como preferencia en el dispositivo la selección del usuario

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("idioma", lang);
        editor.apply();

        Resources res = getResources();
        Configuration conf = res.getConfiguration();
        DisplayMetrics dm = res.getDisplayMetrics();
        conf.locale = Locale.forLanguageTag(lang);
        res.updateConfiguration(conf, dm);
        recreate();

    }

    /**
     * Método que maneja el clic en un elemento del listado de personajes
     * @param character Personaje sobre el que se hace clic
     * @param view
     */
    public void characterClicked(CharacterData character, View view) {

        // Crear un Bundle para pasar los datos al fragmento CharacterDetailFragment
        Bundle bundle = new Bundle();
        bundle.putString("name", character.getName());
        bundle.putString("role", character.getRole());
        bundle.putString("skill", character.getSkill());
        bundle.putString("image", character.getImage());
        bundle.putString("description", character.getDescription());

        // Navegar al CharacterDetailFragment con el Bundle
        navController.navigate(R.id.gameDetailFragment, bundle);
        //Navigation.findNavController(view).navigate(R.id.gameDetailFragment, bundle);
    }

    /**
     * Método para manejar la selección de un elemento del menú
     * @param item Elemento sobre el que se ha hecho clic
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Manejar clics en el icono del menú
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);

        /*
        System.out.println(item.getItemId());

        if (item.getItemId() == R.id.menu_about) {
            openAboutDialog();
        } else if (item.getItemId() == 16908332) {
            return navController.navigateUp() || super.onSupportNavigateUp();
        }

        return true;
        */

    }

    /**
     * Método para abrir el menú de Acerca de.
     * Abre un diálogo con la información de la aplicación.
     */
    public void openAboutDialog() {

        dialog = new Dialog(MainActivity.this, R.style.Base_Theme_PMDM_Tarea2);
        View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.custom_dialog, findViewById(R.id.custom_dialog));

        dialog.setContentView(dialogView);
        dialog.show();

        Button botonCerrar = dialogView.findViewById(R.id.dialogButton);
        botonCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }

    /**
     *
     * @return
     */
    @Override
    public boolean onSupportNavigateUp() {
        Fragment navHostFragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);

        if (navHostFragment != null) {
            NavController navController = NavHostFragment.findNavController(navHostFragment);
            return NavigationUI.navigateUp(navController, binding.drawerLayout) || super.onSupportNavigateUp();
        }
        return super.onSupportNavigateUp();
    }

}