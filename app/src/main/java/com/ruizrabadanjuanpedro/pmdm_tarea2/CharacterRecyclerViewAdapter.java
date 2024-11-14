package com.ruizrabadanjuanpedro.pmdm_tarea2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import com.ruizrabadanjuanpedro.pmdm_tarea2.databinding.CharacterCardviewBinding;
import android.view.View;

/**
 * Clase que representa el Adaptador del RecyclerView
 * @author Juampe Ruiz
 */
public class CharacterRecyclerViewAdapter extends RecyclerView.Adapter<CharacterViewHolder> {

    private final ArrayList<CharacterData> characters;
    private final Context context;

    /**
     * Constructor de la clase
     * @param characters
     * @param context
     */
    public CharacterRecyclerViewAdapter(ArrayList<CharacterData> characters, Context context){
        this.characters = characters;
        this.context = context;
    }

    /**
     * Método para crear un ViewHolder
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return
     */
    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CharacterCardviewBinding binding = CharacterCardviewBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent,false
        );
        return new CharacterViewHolder(binding);
    }

    /**
     * Método para actualizar el contenido del ViewHolder
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {

        CharacterData currentCharacter = this.characters.get(position);
        holder.bind(currentCharacter);
        holder.itemView.setOnClickListener(view -> itemClicked(currentCharacter, view));

    }

    /**
     * Método para obtener el número de elementos en la lista
     * @return Int número de elementos
     */
    @Override
    public int getItemCount() {
        return characters.size();
    }

    /**
     * Método para manejar el clic en un elemento de la lista
     * @param currentCharacter
     * @param view
     */
    private void itemClicked(CharacterData currentCharacter, View view) {
        ((MainActivity) context).characterClicked(currentCharacter, view);
    }
}

