package android.eservices.webrequests.presentation.pokemondisplay.list;

import android.eservices.webrequests.data.api.model.Pokemon;
import android.eservices.webrequests.data.api.model.PokemonSearchResponse;
import android.eservices.webrequests.data.api.model.pokemonsearchresponse.PokemonElement;
import android.eservices.webrequests.data.repository.bookdisplay.PokemonDisplayRepository;
import android.eservices.webrequests.presentation.pokemondisplay.list.mapper.PokemonToViewModelMapper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * An implementation of the contract's presenter for the ListFragment
 */
public class PokemonListPresenter implements PokemonListContract.Presenter {
    private PokemonListContract.View view;
    private PokemonToViewModelMapper mapper;
    private PokemonDisplayRepository pokemonDisplayRepository;
    private CompositeDisposable compositeDisposable;

    /**
     * Contructor
     * @param pokemonDisplayRepository the app's repository
     * @param mapper the mapper which maps a Pokemon into a ViewModel
     */
    public PokemonListPresenter(PokemonDisplayRepository pokemonDisplayRepository, PokemonToViewModelMapper mapper) {
        this.pokemonDisplayRepository = pokemonDisplayRepository;
        this.mapper = mapper;
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void searchPokemonByName(String name) {
        final List<Pokemon> tmp = new ArrayList<>();
        compositeDisposable.clear();
        compositeDisposable.add(pokemonDisplayRepository.searchPokemonsByName(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
             .subscribeWith(new DisposableSingleObserver<Pokemon>() {
                 @Override
                 public void onSuccess(Pokemon pokemon) {
                     tmp.add(pokemon);
                    view.displayPokemons(mapper.map(tmp));
                 }

                 @Override
                 public void onError(Throwable e) {
                     System.out.println(e.toString());
                 }
             })
        );

    }

    @Override
    public void searchPokemonByInterval(int offset, int limit) {
        final List<PokemonElement> tmp_interval_elem = new ArrayList<>();
        compositeDisposable.clear();
        compositeDisposable.add(pokemonDisplayRepository.searchPokemonByInterval(offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<PokemonSearchResponse>() {
                    @Override
                    public void onSuccess(PokemonSearchResponse pokemonSearchResponse) {
                        for (PokemonElement p : pokemonSearchResponse.getPokemonElementList()) {
                            tmp_interval_elem.add(p);
                        }
                        view.displayPokemons(mapper.mapElem(tmp_interval_elem));
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e.toString());
                    }
                })

        );

    }

    @Override
    public void attachView(PokemonListContract.View view) {
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
