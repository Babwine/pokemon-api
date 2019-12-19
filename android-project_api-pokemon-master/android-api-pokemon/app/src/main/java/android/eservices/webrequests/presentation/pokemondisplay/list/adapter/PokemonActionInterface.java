package android.eservices.webrequests.presentation.pokemondisplay.list.adapter;

/**
 * An interface which allows to make some actions on classes which inherits from it
 */
public interface PokemonActionInterface {

    /**
     * This function is triggered when a Pokemon view model in the ListFragment is selected
     * @param pokemonId the id of the Pokemon
     */
    void onPokemonClicked(int pokemonId);
}
