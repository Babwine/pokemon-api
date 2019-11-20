package android.eservices.webrequests.data.db;

import android.eservices.webrequests.data.entity.PokemonEntity;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {PokemonEntity.class}, version = 1)
public abstract class PokemonDatabase extends RoomDatabase {
    public abstract PokemonDao pokemonDao();
}
