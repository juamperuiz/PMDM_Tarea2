package com.ruizrabadanjuanpedro.pmdm_tarea2;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.ruizrabadanjuanpedro.pmdm_tarea2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configura el NavController
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController);

        // Mensaje de bienvenida con componente Toast
        Toast.makeText(MainActivity.this, getString(R.string.WelcomeToast), Toast.LENGTH_SHORT).show();

    }

    // Método para manejar el clic en un juego
    public void gameClicked(GameData game, View view) {
        // Crear un Bundle para pasar los datos al GameDetailFragment
        Bundle bundle = new Bundle();
        bundle.putString("name", game.getName());
        bundle.putString("role", game.getRole());
        bundle.putString("skill", game.getSkill());
        bundle.putString("image", game.getImage()); // Pasa la imagen del juego
        bundle.putString("description", game.getDescription()); // Pasa la descripción o más datos que necesites

        // Navegar al GameDetailFragment con el Bundle
        Navigation.findNavController(view).navigate(R.id.gameDetailFragment, bundle);
    }
    @Override
    public boolean onSupportNavigateUp() {
        // Utiliza el método navigateUp del NavController
        return navController.navigateUp() || super.onSupportNavigateUp();
    }

}