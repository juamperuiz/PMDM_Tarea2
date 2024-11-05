package com.ruizrabadanjuanpedro.pmdm_tarea2;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ruizrabadanjuanpedro.pmdm_tarea2.databinding.CharacterListFragmentBinding;
import java.util.ArrayList;

/**
 * Clase que representa el Fragmento que muestra la lista de juegos
 */
public class CharacterListFragment extends Fragment {

    private CharacterListFragmentBinding binding; // Binding para el layout
    private ArrayList<CharacterData> characters; // Lista de juegos
    private CharacterRecyclerViewAdapter adapter; // Adaptador del RecyclerView

    /**
     * Método que se llama cuando se crea el fragmento
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflar el layout utilizando el binding
        binding = CharacterListFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    /**
     *
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inicializa la lista de juegos
        loadCharacters(); // Cargar los juegos (puedes implementar esta función para obtener datos)

        // Configurar el RecyclerView. Creamos y establecemos el Adapter que transmitirá la información
        // entre la clase y la vista
        adapter = new CharacterRecyclerViewAdapter(characters, getActivity());
        binding.gamesRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.gamesRecyclerview.setAdapter(adapter);

    }

    /**
     * Método que carga la lista de personajes en un Array
     */
    private void loadCharacters() {

        characters = new ArrayList<CharacterData>();

        characters.add(new CharacterData(
                "https://i.imgur.com/NYxHRnf.jpeg",
                getString(R.string.MarioName),
                getString(R.string.Ally),
                getString(R.string.MarioDescription),
                getString(R.string.MarioSkill)
        ));

        characters.add(new CharacterData(
                "https://i.imgur.com/UhTjEeI.jpeg",
                getString(R.string.LuigiName),
                getString(R.string.Ally),
                getString(R.string.LuigiDescription),
                getString(R.string.LuigiSkill)
        ));

        characters.add(new CharacterData(
                "https://i.imgur.com/Tsx6Zb2.jpeg",
                getString(R.string.PeachName),
                getString(R.string.Ally),
                getString(R.string.PeachDescription),
                getString(R.string.PeachSkill)
        ));

        characters.add(new CharacterData(
                "https://i.imgur.com/swNKz34.jpeg",
                getString(R.string.YoshiName),
                getString(R.string.Ally),
                getString(R.string.YoshiDescription),
                getString(R.string.YoshiSkill)
        ));

        characters.add(new CharacterData(
                "https://i.imgur.com/jFMmbEc.jpeg",
                getString(R.string.ToadName),
                getString(R.string.Ally),
                getString(R.string.ToadDescription),
                getString(R.string.ToadSkill)
        ));

        characters.add(new CharacterData(
                "https://i.imgur.com/O40LxL9.jpeg",
                getString(R.string.BowserName),
                getString(R.string.Enemy),
                getString(R.string.BowserDescription),
                getString(R.string.BowserSkill)
        ));

    }

    /**
     * Método que cuando es ejecutado el fragmento del listado de personajes se cambia el título del ActionBar
     */
    @Override
    public void onStart() {
        super.onStart();
        // Cambia el título del ActionBar
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.CharacterList);
        }
    }
}
