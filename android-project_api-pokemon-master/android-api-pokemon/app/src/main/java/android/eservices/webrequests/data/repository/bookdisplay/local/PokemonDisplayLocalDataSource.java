package android.eservices.webrequests.data.repository.bookdisplay.local;

import android.eservices.webrequests.data.db.PokemonDatabase;
import android.eservices.webrequests.data.entity.PokemonEntity;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * A class for the local data source
 */
public class PokemonDisplayLocalDataSource {
    private PokemonDatabase pokemonDatabase;

    public PokemonDisplayLocalDataSource(PokemonDatabase pokemonDatabase) {
        this.pokemonDatabase = pokemonDatabase;
    }

    public Single<PokemonEntity> loadPokemon(int id) {
        return pokemonDatabase.pokemonDao().loadPokemon(id);
    }

    public Completable addPokemonDetails(PokemonEntity pokemonEntity) {
        return pokemonDatabase.pokemonDao().addPokemon(pokemonEntity);
    }

}
