package android.eservices.webrequests.presentation.pokemondisplay.list;

import android.eservices.webrequests.presentation.pokemondisplay.list.adapter.PokemonItemViewModel;

import java.util.List;

/**
 * An interface to represent the contract between the Presenter and the View for the ListFragment
 */
public interface PokemonListContract {
    /**
     * The Fragment's view
     */
    interface View {
        /**
         * A function to display all Pokemon-based view models in the list <code>pokemonItemViewModelList</code>
         * @param pokemonItemViewModelList the list of Pokemon-based view models
         */
        void displayPokemons(List<PokemonItemViewModel> pokemonItemViewModelList);

    }

    /**
     * The Fragment's presenter
     */
    interface Presenter {
        /**
         * A function to display a Pokemon by its name : <code>keywords</code>
         * @param keywords the name of the searched Pokemon
         */
        void searchPokemonByName(String keywords);

        /**
         * A function to display a list of Pokemon from the index <code>offset</code> to the index <code>offset</code>+<code>limit</code>
         * @param offset the first index
         * @param limit the amount of Pokemon
         */
        void searchPokemonByInterval(int offset, int limit);

        /**
         * A function to attach the previously coded view
         * @param view the view to attach
         */
        void attachView(View view);

        /**
         * A function to cancel all subscriptions
         */
        void cancelSubscription();

        /**
         * A function to detach the previously attached view
         */
        void detachView();
    }

}
