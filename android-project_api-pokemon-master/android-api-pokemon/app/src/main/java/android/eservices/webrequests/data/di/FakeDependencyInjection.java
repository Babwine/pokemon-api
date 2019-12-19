package android.eservices.webrequests.data.di;

import android.content.Context;
import android.eservices.webrequests.data.api.PokemonDisplayService;
import android.eservices.webrequests.data.db.PokemonDatabase;
import android.eservices.webrequests.data.repository.bookdisplay.PokemonDisplayDataRepository;
import android.eservices.webrequests.data.repository.bookdisplay.PokemonDisplayRepository;
import android.eservices.webrequests.data.repository.bookdisplay.local.PokemonDisplayLocalDataSource;
import android.eservices.webrequests.data.repository.bookdisplay.mapper.PokemonToPokemonEntityMapper;
import android.eservices.webrequests.data.repository.bookdisplay.remote.PokemonDisplayRemoteDataSource;

import androidx.room.Room;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This class is only needed to make artificial dependencies. To quote our courses :
 *
 * Please never do that in a production app. Ever.
 * For the purpose of our course, this is the best option to cover interesting topics as
 * we don't have time to dig into Dependency Injection frameworks such as the famous Dagger.
 * Singleton are compulsory for some classes, such as the one here. If you don't know why, then ask me.
 * Note that this god object doesn't handle Scopes nor component lifecycles so this really shouldn't be
 * the way to go when you master the craft of your software.
 */
public class FakeDependencyInjection {

    private static Context applicationContext;
    private static Retrofit retrofit;
    private static Gson gson;
    private static PokemonDisplayService pokemonDisplayService;
    private static PokemonDisplayRepository pokemonDisplayRepository;
    private static PokemonDatabase pokemonDatabase;

    /**
     * Returns the service of the application
     * @return the service of the application
     */
    public static PokemonDisplayService getPokemonDisplayService() {
        if (pokemonDisplayService == null) pokemonDisplayService = getRetrofit().create(PokemonDisplayService.class);
        return pokemonDisplayService;
    }

    /**
     * Returns the retrofit of the application
     * @return the retrofit of the application
     */
    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addNetworkInterceptor(new StethoInterceptor())
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://pokeapi.co/api/v2/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();
        }
        return retrofit;
    }

    /**
     * Returns the Gson
     * @return the Gson
     */
    public static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

    /**
     * Returns the database of the application
     * @return the database of the application
     */
    public static PokemonDatabase getPokemonDatabase() {
        if (pokemonDatabase == null) {
            pokemonDatabase = Room.databaseBuilder(applicationContext,
                    PokemonDatabase.class, "book-database").build();
        }
        return pokemonDatabase;
    }

    /**
     * Returns the main repository of the application
     * @return the main repository of the application
     */
    public static PokemonDisplayRepository getPokemonDisplayRepository() {
        if (pokemonDisplayRepository == null) {
            pokemonDisplayRepository = new PokemonDisplayDataRepository(
                    new PokemonDisplayLocalDataSource(getPokemonDatabase()),
                    new PokemonDisplayRemoteDataSource(getPokemonDisplayService()),
                    new PokemonToPokemonEntityMapper()
            );
        }
        return pokemonDisplayRepository;
    }

    /**
     * A function to return the String <code>s</code> with the first char being uppercase
     * @param s the String
     * @return the String with the first char being uppercase
     */
    public static String toUpperCaseFirstChar(String s) {
        return s.substring(0,1).toUpperCase()+s.substring(1);
    }


    /**
     * A function to set a context
     * @param context
     */
    public static void setContext(Context context) {
        applicationContext = context;
    }

}
