package com.ruizrabadanjuanpedro.pmdm_tarea2;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import com.ruizrabadanjuanpedro.pmdm_tarea2.databinding.CharacterDetailFragmentBinding;

/**
 * Clase que representa el Fragmento que muestra los detalles de un personaje
 */
public class CharacterDetailFragment extends Fragment {

    private CharacterDetailFragmentBinding binding;
    private String characterName;

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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflar el layout para este fragmento
        binding = CharacterDetailFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    /**
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Obtener datos del argumento que inicia este fragmento
        if (getArguments() != null) {
            String image = getArguments().getString("image");
            String name = getArguments().getString("name");
            String role = getArguments().getString("role");
            String description = getArguments().getString("description");
            String skill = getArguments().getString("skill");

            // Asignar los datos a los componentes
            Picasso.get()
                    .load(image)
                    .into(binding.image);
            binding.name.setText(name);
            binding.role.setText(role);
            binding.description.setText(Html.fromHtml(description));
            binding.skill.setText(skill);
            characterName = name;

            // Mensaje de selección con un componente Toast
            Toast.makeText(getActivity().getApplicationContext(), getString(R.string.CharacterSelected) + " " + characterName, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Método que cuando es ejecutado el fragmento del detalle del personaje se cambia el título del ActionBar
     */
    @Override
    public void onStart() {
        super.onStart();
        // Cambia el título del ActionBar
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(characterName);
        }
    }
}
