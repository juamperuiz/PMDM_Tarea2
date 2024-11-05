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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.snackbar.Snackbar;
import com.ruizrabadanjuanpedro.pmdm_tarea2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private Menu menu;
    private Dialog dialog;
    private Button botonCerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configura el NavController
        // Crea el nav controller, componente de navegación entre fragments y lo inicializa
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController);

        // Mensaje de bienvenida con componente Snackbar
        Snackbar.make(findViewById(R.id.constraint_layout), getString(R.string.WelcomeToast), Snackbar.LENGTH_SHORT).show();

    }

    // Método para manejar el clic en un juego
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

    @Override
    public boolean onSupportNavigateUp() {
        // Utiliza el método navigateUp del NavController
        return navController.navigateUp() || super.onSupportNavigateUp();
    }

    // Inflar el menú contextual
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    // Acciones de clic en el menú
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