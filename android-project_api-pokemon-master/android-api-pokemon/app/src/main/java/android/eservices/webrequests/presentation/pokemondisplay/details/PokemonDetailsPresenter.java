package android.eservices.webrequests.presentation.pokemondisplay.details;

import android.eservices.webrequests.data.api.model.Pokemon;
import android.eservices.webrequests.data.repository.bookdisplay.PokemonDisplayRepository;
import android.eservices.webrequests.presentation.pokemondisplay.details.mapper.PokemonDetailsToViewModelMapper;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * A class to implement the presenter from the contract for Pokemon Details
 */
public class PokemonDetailsPresenter implements PokemonDetailsContract.Presenter {
    private PokemonDetailsContract.View view;
    private PokemonDetailsToViewModelMapper mapper;
    private PokemonDisplayRepository pokemonDisplayRepository;
    private CompositeDisposable compositeDisposable;

    /**
     * Constructor
     * @param pokemonDisplayRepository the main repository
     * @param mapper the mapper which maps a Pokemon into a pokemon details based view model
     */
    public PokemonDetailsPresenter(PokemonDisplayRepository pokemonDisplayRepository, PokemonDetailsToViewModelMapper mapper) {
        this.pokemonDisplayRepository = pokemonDisplayRepository;
        this.mapper = mapper;
        this.compositeDisposable = new CompositeDisposable();
    }
    @Override
    public void getPokemonById(int id) {
        compositeDisposable.clear();
        compositeDisposable.add(pokemonDisplayRepository.getPokemonById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Pokemon>() {
                    @Override
                    public void onSuccess(Pokemon pokemon) {
                        view.displayPokemon(mapper.map(pokemon));
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e.toString());
                    }
                })
        );
    }


    @Override
    public void attachView(PokemonDetailsContract.View view) {
        this.view = view;
    }

    @Override
    public void cancelSubscription() {
        compositeDisposable.clear();
    }

    @Override
    public void detachView() {
        view = null;
    }
}
