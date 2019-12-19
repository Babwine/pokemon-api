package android.eservices.webrequests.data.repository.bookdisplay;

import android.eservices.webrequests.data.api.model.Pokemon;
import android.eservices.webrequests.data.api.model.PokemonSearchResponse;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * An interface for the repository of the application
 */
public interface PokemonDisplayRepository {
    /**
     * A function to get a Pokemon's informations using its <code>id</code>
     * @param id the Pokemon's id
     * @return an observable which contain the Pokemon
     */
    Single<Pokemon> getPokemonById(int id);

    /**
     * A function to get a Pokemon's informations using its <code>name</code>
     * @param name the Pokemon's name
     * @return an observable which contain the Pokemon
     */
    Single<Pokemon> searchPokemonsByName(String name);

    /**
     * A function to get a PokemonSearchResponse object containing a list of the Pokemon whose indexes are between <code>offset</code> and <code>offset</code>+<code>limit</code>
     * @param offset the index of the first Pokemon
     * @param limit the amount of Pokemon in the list
     * @return an observable which contain the PokemonSearchResponse
     */
    Single<PokemonSearchResponse> searchPokemonByInterval(int offset, int limit);

}
