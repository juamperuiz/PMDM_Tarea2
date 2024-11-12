package com.ruizrabadanjuanpedro.pmdm_tarea2;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.snackbar.Snackbar;
import com.ruizrabadanjuanpedro.pmdm_tarea2.databinding.ActivityMainBinding;

/**
 * Clase que representa la Actividad principal
 */
public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private Menu menu;
    private Dialog dialog;
    private Button botonCerrar;

    /**
     * Método que se llama cuando se crea la actividad
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Splashscreen
        SplashScreen.installSplashScreen(this);

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configura el NavController
        FragmentManager fragmentManager = getSupportFragmentManager();
        NavHostFragment navHostFragment = (NavHostFragment) fragmentManager.findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();

        // Mensaje de bienvenida con componente Snackbar
        Snackbar.make(findViewById(R.id.constraint_layout), getString(R.string.WelcomeToast), Snackbar.LENGTH_SHORT).show();

        // Toolbar ejecutada mediante viewBinding
        // TODO: añadir el navcontroller con la flechita hacia atrás
        setSupportActionBar(binding.toolbar);

    }

    /**
     * Método para manejar el clic en un personaje
     * @param game
     * @param view
     */
    public void characterClicked(CharacterData game, View view) {
        // Crear un Bundle para pasar los datos al CharacterDetailFragment
        Bundle bundle = new Bundle();
        bundle.putString("name", game.getName());
        bundle.putString("role", game.getRole());
        bundle.putString("skill", game.getSkill());
        bundle.putString("image", game.getImage()); // Pasa la imagen del juego
        bundle.putString("description", game.getDescription()); // Pasa la descripción o más datos que necesites

        // Navegar al CharacterDetailFragment con el Bundle
        Navigation.findNavController(view).navigate(R.id.gameDetailFragment, bundle);
    }

    /**
     * Método para manejar el clic en el botón de retroceso
     * @return
     */
    @Override
    public boolean onSupportNavigateUp() {
        // Utiliza el método navigateUp del NavController
        return navController.navigateUp() || super.onSupportNavigateUp();
    }

    /**
     * Método para crear el menú
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * Método para manejar la selección de un elemento del menú
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        System.out.println(item.getItemId());

        if (item.getItemId() == R.id.menu_about) {
            openAboutDialog();
        } else if (item.getItemId() == 16908332) {
            return navController.navigateUp() || super.onSupportNavigateUp();
        }

        return true;

    }

    /**
     * Método para abrir el diálogo de información
     */
    public void openAboutDialog() {

        dialog = new Dialog(MainActivity.this, R.style.Base_Theme_PMDM_Tarea2);
        View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.custom_dialog, findViewById(R.id.custom_dialog));

        dialog.setContentView(dialogView);
        dialog.show();

        botonCerrar = dialogView.findViewById(R.id.dialogButton);
        botonCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }

}