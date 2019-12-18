package android.eservices.webrequests.presentation.pokemondisplay.grid.adapter;

import android.eservices.webrequests.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.ArrayList;
import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {



    public static class PokemonViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView typesTextView;
        private ImageView spriteImageView;
        private View v;
        private PokemonItemViewModel pokemonItemViewModel;
        private PokemonActionInterface pokemonActionInterface;


        public PokemonViewHolder(@NonNull View v, final PokemonActionInterface pokemonActionInterface) {
            super(v);
            this.v = v;
            nameTextView = v.findViewById(R.id.pokemon_name_textview);
            typesTextView = v.findViewById(R.id.pokemon_types_textview);
            spriteImageView = v.findViewById(R.id.pokemon_sprite_imageview);
            this.pokemonActionInterface = pokemonActionInterface;
            setupListeners();
        }

        private void setupListeners() {
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pokemonActionInterface.onPokemonClicked(pokemonItemViewModel.getPokemonId());
                }
            });
        }

        void bind(PokemonItemViewModel pokemonItemViewModel) {
            this.pokemonItemViewModel = pokemonItemViewModel;
            nameTextView.setText(pokemonItemViewModel.getPokemonName());
            typesTextView.setText("#"+pokemonItemViewModel.getPokemonId());
            Glide.with(v)
                    .load(pokemonItemViewModel.getSpriteUrl())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .circleCrop()
                    .into(spriteImageView);
        }
    }

    private List<PokemonItemViewModel> pokemonItemViewModelList;
    private PokemonActionInterface pokemonActionInterface;

    public PokemonAdapter(PokemonActionInterface pokemonActionInterface) {
        this.pokemonItemViewModelList = new ArrayList<>();
        this.pokemonActionInterface = pokemonActionInterface;
    }

    public void bindViewModels(List<PokemonItemViewModel> pokemonItemViewModelList) {
        this.pokemonItemViewModelList.clear();
        this.pokemonItemViewModelList.addAll(pokemonItemViewModelList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pokemon, parent, false);
        PokemonViewHolder pokemonViewHolder = new PokemonViewHolder(v,pokemonActionInterface);
        return pokemonViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        holder.bind(pokemonItemViewModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.pokemonItemViewModelList.size();
    }
}
