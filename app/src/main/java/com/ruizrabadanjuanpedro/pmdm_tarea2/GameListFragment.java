package com.ruizrabadanjuanpedro.pmdm_tarea2;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ruizrabadanjuanpedro.pmdm_tarea2.R;
import com.ruizrabadanjuanpedro.pmdm_tarea2.databinding.GameListFragmentBinding;
import java.util.ArrayList;

public class GameListFragment extends Fragment {

    private GameListFragmentBinding binding; // Binding para el layout
    private ArrayList<GameData> games; // Lista de juegos
    private GameRecyclerViewAdapter adapter; // Adaptador del RecyclerView

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflar el layout utilizando el binding
        binding = GameListFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inicializa la lista de juegos
        loadGames(); // Cargar los juegos (puedes implementar esta función para obtener datos)

        // Configurar el RecyclerView
        adapter = new GameRecyclerViewAdapter(games, getActivity());
        binding.gamesRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.gamesRecyclerview.setAdapter(adapter);


    }

    // Método para cargar juegos (puedes implementar tu lógica aquí)
    private void loadGames() {
        games = new ArrayList<GameData>();
        // Llenar la lista con datos de videojuegos
        games.add(new GameData(
                "https://i.imgur.com/NYxHRnf.jpeg",
                getString(R.string.MarioName),
                getString(R.string.Ally),
                getString(R.string.MarioDescription),
                getString(R.string.MarioSkill)
        ));

        games.add(new GameData(
                "https://i.imgur.com/UhTjEeI.jpeg",
                getString(R.string.LuigiName),
                getString(R.string.Ally),
                getString(R.string.LuigiDescription),
                getString(R.string.LuigiSkill)
        ));

        games.add(new GameData(
                "https://i.imgur.com/Tsx6Zb2.jpeg",
                getString(R.string.PeachName),
                getString(R.string.Ally),
                getString(R.string.PeachDescription),
                getString(R.string.PeachSkill)
        ));

        games.add(new GameData(
                "https://i.imgur.com/swNKz34.jpeg",
                getString(R.string.YoshiName),
                getString(R.string.Ally),
                getString(R.string.YoshiDescription),
                getString(R.string.YoshiSkill)
        ));

        games.add(new GameData(
                "https://i.imgur.com/jFMmbEc.jpeg",
                getString(R.string.ToadName),
                getString(R.string.Ally),
                getString(R.string.ToadDescription),
                getString(R.string.ToadSkill)
        ));

        games.add(new GameData(
                "https://i.imgur.com/O40LxL9.jpeg",
                getString(R.string.BowserName),
                getString(R.string.Enemy),
                getString(R.string.BowserDescription),
                getString(R.string.BowserSkill)
        ));

    }

    @Override
    public void onStart() {
        super.onStart();
        // Cambia el título del ActionBar
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.CharacterList);
        }
    }
}
