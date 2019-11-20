package android.eservices.webrequests.data.api;

import android.eservices.webrequests.data.api.model.Pokemon;
import android.eservices.webrequests.data.api.model.PokemonSearchResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokemonDisplayService {
    @GET("pokemon/{pokemonId}")
    Single<Pokemon> getPokemonById(@Path("pokemonId") int id);

    @GET("pokemon/{pokemonName}")
    Single<Pokemon> searchPokemonByName(@Path("pokemonName") String name);

    @GET("pokemon")
    Single<PokemonSearchResponse> getBoundedPokemonList(@Query("offset") int offset, @Query("limit") int limit);
}
