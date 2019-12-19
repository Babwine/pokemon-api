package android.eservices.webrequests.data.repository.bookdisplay;


import android.eservices.webrequests.data.api.model.Pokemon;
import android.eservices.webrequests.data.api.model.PokemonSearchResponse;
import android.eservices.webrequests.data.entity.PokemonEntity;
import android.eservices.webrequests.data.repository.bookdisplay.local.PokemonDisplayLocalDataSource;
import android.eservices.webrequests.data.repository.bookdisplay.mapper.PokemonToPokemonEntityMapper;
import android.eservices.webrequests.data.repository.bookdisplay.remote.PokemonDisplayRemoteDataSource;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Single;
import io.reactivex.functions.Function;

/**
 * An implementation of the PokemonDisplayRepository interface
 */
public class PokemonDisplayDataRepository implements PokemonDisplayRepository {

    private PokemonDisplayLocalDataSource pokemonDisplayLocalDataSource;
    private PokemonDisplayRemoteDataSource pokemonDisplayRemoteDataSource;
    private PokemonToPokemonEntityMapper mapper;

    /**
     * Contructor
     * @param pokemonDisplayLocalDataSource the local repo
     * @param pokemonDisplayRemoteDataSource the distant repo
     * @param mapper the mapper used to stock Pokemon entities
     */
    public PokemonDisplayDataRepository(PokemonDisplayLocalDataSource pokemonDisplayLocalDataSource,
                                        PokemonDisplayRemoteDataSource pokemonDisplayRemoteDataSource,
                                        PokemonToPokemonEntityMapper mapper) {
        this.pokemonDisplayLocalDataSource = pokemonDisplayLocalDataSource;
        this.pokemonDisplayRemoteDataSource = pokemonDisplayRemoteDataSource;
        this.mapper = mapper;
    }


    @Override
    public Single<Pokemon> getPokemonById(int id) {
        return pokemonDisplayRemoteDataSource.getPokemonById(id);
    }

    @Override
    public Single<Pokemon> searchPokemonsByName(String name) {
        return pokemonDisplayRemoteDataSource.searchPokemonByName(name);
    }

    @Override
    public Single<PokemonSearchResponse> searchPokemonByInterval(int offset, int limit) {
        return pokemonDisplayRemoteDataSource.searchPokemonByInterval(offset, limit);
    }

}
