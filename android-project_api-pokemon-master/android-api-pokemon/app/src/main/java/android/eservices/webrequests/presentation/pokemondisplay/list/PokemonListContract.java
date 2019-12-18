package android.eservices.webrequests.presentation.pokemondisplay.list;

import android.eservices.webrequests.presentation.pokemondisplay.list.adapter.PokemonItemViewModel;

import java.util.List;

public interface PokemonListContract {
    interface View {
        void displayPokemons(List<PokemonItemViewModel> pokemonItemViewModelList);

    }

    interface Presenter {
        void searchPokemonByName(String keywords);

        void searchPokemonByInterval(int offset, int limit);


        void attachView(View view);

        void cancelSubscription();

        void detachView();
    }

}
