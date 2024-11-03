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

    public void bind (CharacterData game){
        Picasso.get()
                .load(game.getImage())
                .into(binding.image);
        binding.name.setText(game.getName());
        binding.role.setText(game.getRole());
        binding.skill.setText(game.getSkill());
        binding.executePendingBindings(); // Asegura que se apliquen los cambios de inmediato
    }
}

