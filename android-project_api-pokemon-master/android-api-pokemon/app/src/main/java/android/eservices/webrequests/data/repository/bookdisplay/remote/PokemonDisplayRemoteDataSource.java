package android.eservices.webrequests.data.repository.bookdisplay.remote;

import android.eservices.webrequests.data.api.PokemonDisplayService;
import android.eservices.webrequests.data.api.model.Pokemon;
import android.eservices.webrequests.data.api.model.PokemonSearchResponse;

import io.reactivex.Single;

/**
 * A class for the distant data source
 */
public class PokemonDisplayRemoteDataSource {
    private PokemonDisplayService pokemonDisplayService;

    /**
     * Constructor
     * @param pokemonDisplayService the service used
     */
    public PokemonDisplayRemoteDataSource(PokemonDisplayService pokemonDisplayService) {
        this.pokemonDisplayService = pokemonDisplayService;
    }

    public Single<Pokemon> getPokemonById(int id) {
        return pokemonDisplayService.getPokemonById(id);
    }

    public Single<Pokemon> searchPokemonByName(String name) {
        return pokemonDisplayService.searchPokemonByName(name);
    }

    public Single<PokemonSearchResponse> searchPokemonByInterval(int offset, int limit) {
        return pokemonDisplayService.getBoundedPokemonList(offset, limit);
    }
}
