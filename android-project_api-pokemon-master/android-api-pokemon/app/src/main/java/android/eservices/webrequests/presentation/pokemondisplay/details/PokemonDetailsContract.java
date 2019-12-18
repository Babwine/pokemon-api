package android.eservices.webrequests.presentation.pokemondisplay.details;

import android.eservices.webrequests.presentation.pokemondisplay.details.adapter.PokemonDetailsItemViewModel;

public interface PokemonDetailsContract {
    interface View {
        void displayPokemon(PokemonDetailsItemViewModel pokemonDetailsItemViewModel);

    }

    interface Presenter {
        void getPokemonById(int id);

        void attachView(PokemonDetailsContract.View view);

        void cancelSubscription();

        void detachView();
    }
}
