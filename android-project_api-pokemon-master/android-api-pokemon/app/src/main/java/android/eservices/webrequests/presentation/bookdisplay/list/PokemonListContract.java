package android.eservices.webrequests.presentation.bookdisplay.list;

import android.eservices.webrequests.presentation.bookdisplay.list.adapter.PokemonItemViewModel;

import java.util.List;

public interface PokemonListContract {
    interface View {
        void displayPokemons(List<PokemonItemViewModel> pokemonItemViewModelList);

        void onPokemonDetailsAdded();
    }

    interface Presenter {
        void searchPokemonByName(String keywords);

        void searchPokemonByInterval(int offset, int limit);

        void addPokemonDetails(int pokemonId);

        void attachView(View view);

        void cancelSubscription();

        void detachView();
    }

}
