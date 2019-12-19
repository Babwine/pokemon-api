package android.eservices.webrequests.data.api;

import android.eservices.webrequests.data.api.model.Pokemon;
import android.eservices.webrequests.data.api.model.PokemonSearchResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * An interface for the service which will manage the communication between the API <code>pokeapi.co</code> and this application
 */
public interface PokemonDisplayService {

    /**
     * This method uses GET to get a Pokémon's main informations using its <code>id</code> to find it
     * @param id the ID of the Pokémon
     * @return an observable usable to get the Pokémon as a Java object
     */
    @GET("pokemon/{pokemonId}")
    Single<Pokemon> getPokemonById(@Path("pokemonId") int id);

    /**
     * This method uses GET to get a Pokémon's main informations using its <code>name</code> to find it
     * @param name the name of the Pokémon
     * @return an observable usable to get the Pokémon as a Java object
     */
    @GET("pokemon/{pokemonName}")
    Single<Pokemon> searchPokemonByName(@Path("pokemonName") String name);

    /**
     * This method uses GET to get a list of some Pokémon information, the list beginning at the index <code>offset</code> and being composed of <code>limit</code> Pokémon, the list being contained in a PokemonSearchResponse object
     * @param offset the index of the first Pokémon of the list
     * @param limit the amount of Pokémon in the list
     * @return
     */
    @GET("pokemon")
    Single<PokemonSearchResponse> getBoundedPokemonList(@Query("offset") int offset, @Query("limit") int limit);
}
