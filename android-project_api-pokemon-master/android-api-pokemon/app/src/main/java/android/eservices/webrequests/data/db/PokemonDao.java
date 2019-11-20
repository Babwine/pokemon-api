package android.eservices.webrequests.data.db;

import android.eservices.webrequests.data.entity.PokemonEntity;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface PokemonDao {

    @Query("SELECT * from pokemonentity WHERE id = :id")
    Single<PokemonEntity> loadPokemon(int id);

    @Insert
    Completable addPokemon(PokemonEntity pokemonEntity);

}
