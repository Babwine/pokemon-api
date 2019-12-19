package android.eservices.webrequests.presentation.pokemondisplay.details;

import android.eservices.webrequests.presentation.pokemondisplay.details.adapter.PokemonDetailsItemViewModel;

/**
 * An interface which represents the contract between the Pokemon details view and its presenter
 */
public interface PokemonDetailsContract {
    /**
     * The activity's view
     */
    interface View {
        /**
         * A function to display the Pokemon details
         * @param pokemonDetailsItemViewModel the view model in which the Pokemon details are displayed
         */
        void displayPokemon(PokemonDetailsItemViewModel pokemonDetailsItemViewModel);

    }

    /**
     * The activity's presenter
     */
    interface Presenter {
        /**
         * A function to get the Pokemon using its <code>id</code>
         * @param id the pokemon's ID
         */
        void getPokemonById(int id);

        /**
         * A function to attach the previously coded view
         * @param view the activity's view
         */
        void attachView(PokemonDetailsContract.View view);

        /**
         * A function to cancel all subscriptions
         */
        void cancelSubscription();

        /**
         * A function to detach the previously attach view
         */
        void detachView();
    }
}
