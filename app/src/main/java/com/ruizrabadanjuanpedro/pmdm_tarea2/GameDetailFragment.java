package com.ruizrabadanjuanpedro.pmdm_tarea2;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ruizrabadanjuanpedro.pmdm_tarea2.R;
import com.squareup.picasso.Picasso;
import com.ruizrabadanjuanpedro.pmdm_tarea2.databinding.GameDetailFragmentBinding;

public class GameDetailFragment extends Fragment {

    private GameDetailFragmentBinding binding;
    private String characterName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflar el layout para este fragmento
        binding = GameDetailFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

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
            binding.description.setText(description);
            binding.skill.setText(skill);

            characterName = name;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Cambia el título del ActionBar
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(characterName);
        }
    }
}
