package android.eservices.webrequests;

import android.app.Application;
import android.eservices.webrequests.data.di.FakeDependencyInjection;

import com.facebook.stetho.Stetho;

/**
 * The main application class
 */
public class PokemonApplication extends Application {

    public static final String API_KEY = "AIzaSyBd1IFgqsRZgDNDCMqycRwNEPehfk2XqkM";

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        FakeDependencyInjection.setContext(this);
    }
}
