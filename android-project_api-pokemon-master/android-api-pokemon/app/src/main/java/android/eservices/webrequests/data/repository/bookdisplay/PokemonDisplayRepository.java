package android.eservices.webrequests.data.repository.bookdisplay;

import android.eservices.webrequests.data.api.model.Pokemon;
import android.eservices.webrequests.data.api.model.PokemonSearchResponse;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface PokemonDisplayRepository {
    Single<Pokemon> getPokemonById(int id);

    Single<Pokemon> searchPokemonsByName(String name);

    Single<PokemonSearchResponse> searchPokemonByInterval(int offset, int limit);

    Completable addPokemonDetails(int pokemonId);
}
