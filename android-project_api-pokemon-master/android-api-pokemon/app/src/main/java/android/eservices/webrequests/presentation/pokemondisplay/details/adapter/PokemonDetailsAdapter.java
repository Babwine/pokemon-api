package android.eservices.webrequests.presentation.pokemondisplay.details.adapter;

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

/**
 * A class for the adapter of the activity's recyclerview
 */
public class PokemonDetailsAdapter extends RecyclerView.Adapter<PokemonDetailsAdapter.PokemonViewHolder> {

    public static class PokemonViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView typesTextView;
        private ImageView spriteImageView;
        private TextView abilitiesTextView;
        private View v;
        private PokemonDetailsItemViewModel pokemonDetailsItemViewModel;
        private PokemonDetailsActionInterface pokemonDetailsActionInterface;


        public PokemonViewHolder(@NonNull View v, final PokemonDetailsActionInterface pokemonActionInterface) {
            super(v);
            this.v = v;
            nameTextView = v.findViewById(R.id.pokemon_details_name_textview);
            typesTextView = v.findViewById(R.id.pokemon_details_types_textview);
            spriteImageView = v.findViewById(R.id.pokemon_details_sprite_imageview);
            abilitiesTextView = v.findViewById(R.id.pokemon_details_abilities_textview);
            this.pokemonDetailsActionInterface = pokemonActionInterface;
            setupListeners();
        }

        private void setupListeners() {

        }

        void bind(PokemonDetailsItemViewModel pokemonDetailsItemViewModel) {
            this.pokemonDetailsItemViewModel = pokemonDetailsItemViewModel;
            nameTextView.setText(this.pokemonDetailsItemViewModel.getPokemonName());
            typesTextView.setText(this.pokemonDetailsItemViewModel.getPokemonTypes());
            abilitiesTextView.setText(this.pokemonDetailsItemViewModel.getPokemonAblities());
            Glide.with(v)
                    .load(pokemonDetailsItemViewModel.getSpriteUrl())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .circleCrop()
                    .into(spriteImageView);
        }
    }

    private List<PokemonDetailsItemViewModel> pokemonDetailsItemViewModelList;
    private PokemonDetailsActionInterface pokemonActionInterface;

    /**
     * Contructor
     * @param pokemonActionInterface the interface on which we will listen
     */
    public PokemonDetailsAdapter(PokemonDetailsActionInterface pokemonActionInterface) {
        this.pokemonDetailsItemViewModelList = new ArrayList<>();
        this.pokemonActionInterface = pokemonActionInterface;
    }

    /**
     * A function to bind the view model
     * @param pokemonDetailsItemViewModel the view model to bind
     */
    public void bindViewModels(PokemonDetailsItemViewModel pokemonDetailsItemViewModel) {
        this.pokemonDetailsItemViewModelList.clear();
        this.pokemonDetailsItemViewModelList.add(pokemonDetailsItemViewModel);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PokemonDetailsAdapter.PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_detailed_pokemon, parent, false);
        PokemonDetailsAdapter.PokemonViewHolder pokemonViewHolder = new PokemonDetailsAdapter.PokemonViewHolder(v,pokemonActionInterface);
        return pokemonViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull PokemonDetailsAdapter.PokemonViewHolder holder, int position) {
        holder.bind(pokemonDetailsItemViewModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return pokemonDetailsItemViewModelList.size();
    }

}
