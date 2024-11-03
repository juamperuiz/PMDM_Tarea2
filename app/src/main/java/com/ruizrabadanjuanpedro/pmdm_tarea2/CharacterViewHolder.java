package com.ruizrabadanjuanpedro.pmdm_tarea2;

import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import com.ruizrabadanjuanpedro.pmdm_tarea2.databinding.GameCardviewBinding;

public class CharacterViewHolder extends RecyclerView.ViewHolder {

    private final GameCardviewBinding binding;

    public CharacterViewHolder(GameCardviewBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind (CharacterData character){
        Picasso.get()
                .load(character.getImage())
                .into(binding.image);
        binding.name.setText(character.getName());
        binding.role.setText(character.getRole());
        binding.skill.setText(character.getSkill());
        binding.executePendingBindings(); // Asegura que se apliquen los cambios de inmediato
    }
}

