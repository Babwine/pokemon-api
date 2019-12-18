package android.eservices.webrequests.data.repository.bookdisplay;


import android.eservices.webrequests.data.api.model.Pokemon;
import android.eservices.webrequests.data.api.model.PokemonSearchResponse;
import android.eservices.webrequests.data.entity.PokemonEntity;
import android.eservices.webrequests.data.repository.bookdisplay.local.PokemonDisplayLocalDataSource;
import android.eservices.webrequests.data.repository.bookdisplay.mapper.PokemonToPokemonEntityMapper;
import android.eservices.webrequests.data.repository.bookdisplay.remote.PokemonDisplayRemoteDataSource;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Single;
import io.reactivex.functions.Function;


public class PokemonDisplayDataRepository implements PokemonDisplayRepository {

    private PokemonDisplayLocalDataSource pokemonDisplayLocalDataSource;
    private PokemonDisplayRemoteDataSource pokemonDisplayRemoteDataSource;
    private PokemonToPokemonEntityMapper mapper;

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

    @Override
    public Completable addPokemonDetails(int pokemonId) {
        return pokemonDisplayRemoteDataSource.getPokemonById(pokemonId)
                .map(new Function<Pokemon, PokemonEntity>() {

                    @Override
                    public PokemonEntity apply(Pokemon pokemon) throws Exception {
                        return mapper.map(pokemon);
                    }
                })
                .flatMapCompletable(new Function<PokemonEntity, CompletableSource>() {
                    @Override
                    public CompletableSource apply(PokemonEntity pokemonEntity) throws Exception {
                        return pokemonDisplayLocalDataSource.addPokemonDetails(pokemonEntity);
                    }
                });
    }
}
