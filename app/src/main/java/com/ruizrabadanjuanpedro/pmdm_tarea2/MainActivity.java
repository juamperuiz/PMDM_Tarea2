package com.ruizrabadanjuanpedro.pmdm_tarea2;

import android.app.Dialog;
import android.content.res.Configuration;
import android.os.Bundle;
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

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        String idiomaAlmacenado = PreferencesHelper.getLanguage(this);

        // Establecemos un idioma por defecto si no existe almacenado uno previo
        if (idiomaAlmacenado == null) {
            PreferencesHelper.saveLanguage(this, "es");
            idiomaAlmacenado = "es";
        }

        System.out.println("Idioma almacenado: " + PreferencesHelper.getLanguage(this));

        Locale locale = new Locale(idiomaAlmacenado);
        Configuration config = new Configuration(getResources().getConfiguration());
        config.setLocale(locale);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        System.out.println("Locale actual: " + locale.toString());

        // Inicializamos la Splash Screen de bienvenida de la aplicación
        SplashScreen.installSplashScreen(this);

        // Inflamos el layout
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configuramos el NavController
        FragmentManager fragmentManager = getSupportFragmentManager();
        NavHostFragment navHostFragment = (NavHostFragment) fragmentManager.findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
        navController.addOnDestinationChangedListener(this::onChangeView);

        // Configuramos menú toggle
        configureToggleMenu();

        // Configuramos la navegación
        configureNavigation();

        // Configuramos el icono del menú en la ActionBar
        if (getSupportActionBar() != null) { getSupportActionBar().setDisplayHomeAsUpEnabled(true); }



        // Mensaje de bienvenida tras la SplashScreen mediante el componente Snackbar
        Snackbar.make(findViewById(R.id.drawer_layout), getString(R.string.WelcomeToast), Snackbar.LENGTH_SHORT).show();

    }

    /**
     * Método ejecutado cuando cambiamos la vista con la navegación de la app
     * @param navController Componente navController
     * @param navDestination Destino de la navegación
     * @param bundle
     */
    private void onChangeView(NavController navController, NavDestination navDestination, Bundle bundle) {

        if (toggle == null) { return; }

        if (navDestination.getId() == R.id.gameDetailFragment) {
            toggle.setDrawerIndicatorEnabled(false);
        } else {
            toggle.setDrawerIndicatorEnabled(true);
        }
    }

    /**
     * Método para configurar apertura y cierre del menú drawer
     */
    private void configureToggleMenu() {

        // Configuramos el ActionBarDrawerToggle
        toggle = new ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open_drawer, R.string.close_drawer);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

    }

    /**
     * Método para la configuración de la navegación del NavController
     */
    private void configureNavigation() {

        NavigationUI.setupWithNavController(binding.navView, navController);

        // Manejamos la selección de elementos del menú
        binding.navView.setNavigationItemSelectedListener(menuItem -> {

            // Si queremos volver al menú de inicio
            if (menuItem.getItemId() == R.id.nav_host_fragment) { navController.navigate(R.id.nav_host_fragment); }

            // Si queremos abrir el modal de información sobre la aplicación
            if (menuItem.getItemId() == R.id.menu_about) { openAboutDialog(); }

            // Si queremos ejecutar el cambio de idioma
            if (menuItem.getItemId() == R.id.menu_language) { changeLanguage(); }

            // Ejecutamos el cierre del menú y salimos del método
            binding.drawerLayout.closeDrawers(); // Cerrar el menú
            return true;

        });

    }

    /**
     * Método que se ejecuta si hacemos clic en el cambio de idioma desde el menú drawer
     * Funciona a modo de switch, establece el idioma contrario al que está ejecutándose
     * Alternando entre idioma español e inglés
     */
    private void changeLanguage() {

        String idiomaSeleccionado = PreferencesHelper.getLanguage(this);

        // Establecemos el idioma en las preferencias
        switch (idiomaSeleccionado) {

            case "es":
                PreferencesHelper.saveLanguage(this, "en");
                break;
            case "en":
                PreferencesHelper.saveLanguage(this, "es");
                break;

        }

        // Finalmente, recreamos la vista que será cargada con el nuevo idioma
        invalidateOptionsMenu();
        recreate();

    }

    /**
     * Método que maneja el clic en un elemento del listado de personajes
     * @param character Personaje sobre el que se hace clic
     * @param view
     */
    public void characterClicked(CharacterData character, View view) {

        // Creamos un Bundle para pasar los datos al fragmento CharacterDetailFragment
        Bundle bundle = new Bundle();
        bundle.putString("name", character.getName());
        bundle.putString("role", character.getRole());
        bundle.putString("skill", character.getSkill());
        bundle.putString("image", character.getImage());
        bundle.putString("description", character.getDescription());

        // Navegar al CharacterDetailFragment con el Bundle
        navController.navigate(R.id.gameDetailFragment, bundle);

    }

    /**
     * Método para manejar la selección de un elemento del menú
     * @param item Elemento sobre el que se ha hecho clic
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Maneja los clics en el elemento del menú
        if (toggle.onOptionsItemSelected(item)) { return true; }
        return super.onOptionsItemSelected(item);

    }

    /**
     * Método para abrir el menú de Acerca de
     * Abre un diálogo con la información de la aplicación
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
     * Método para controlar el funcionamiento del botón volver del dispositivo
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