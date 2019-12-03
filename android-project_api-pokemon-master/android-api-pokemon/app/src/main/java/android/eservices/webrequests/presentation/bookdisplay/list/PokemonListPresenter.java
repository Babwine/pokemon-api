package android.eservices.webrequests.presentation.bookdisplay.list;

import android.eservices.webrequests.data.api.PokemonDisplayService;
import android.eservices.webrequests.data.api.model.Pokemon;
import android.eservices.webrequests.data.api.model.PokemonSearchResponse;
import android.eservices.webrequests.data.api.model.pokemonsearchresponse.PokemonElement;
import android.eservices.webrequests.data.repository.bookdisplay.PokemonDisplayRepository;
import android.eservices.webrequests.presentation.bookdisplay.list.adapter.PokemonItemViewModel;
import android.eservices.webrequests.presentation.bookdisplay.list.mapper.PokemonToViewModelMapper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class PokemonListPresenter implements PokemonListContract.Presenter {
    private PokemonListContract.View view;
    private PokemonToViewModelMapper mapper;
    private PokemonDisplayRepository pokemonDisplayRepository;
    private CompositeDisposable compositeDisposable;

    final public List<Pokemon> toMapAndDisplay;

    public PokemonListPresenter(PokemonDisplayRepository pokemonDisplayRepository, PokemonToViewModelMapper mapper) {
        this.pokemonDisplayRepository = pokemonDisplayRepository;
        this.mapper = mapper;
        this.compositeDisposable = new CompositeDisposable();
        this.toMapAndDisplay = new ArrayList<>();
    }

    @Override
    public void searchPokemonByName(String name) {
        compositeDisposable.clear();
        compositeDisposable.add(pokemonDisplayRepository.searchPokemonsByName(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
             .subscribeWith(new DisposableSingleObserver<Pokemon>() {
                 @Override
                 public void onSuccess(Pokemon pokemon) {
                     toMapAndDisplay.add(pokemon);
                     //TODO TEMPORAIREMENT COMMENTE
                    //view.displayPokemons(mapper.map(tmp));
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
                            //TODO DECOUVRIR POURQUOI L'APPLICATION NE PASSE JAMAIS PAR ICI
                            tmp_interval_elem.add(p);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e.toString());
                    }
                })

        );
        compositeDisposable.clear();
        //TODO TEMPORAIRE
        PokemonElement theP = new PokemonElement();
        theP.setName("ditto");
        PokemonElement theP2 = new PokemonElement();
        theP2.setName("bulbasaur");
        tmp_interval_elem.add(theP);
        tmp_interval_elem.add(theP2);
        //
        for (PokemonElement p : tmp_interval_elem) {
            this.searchPokemonByName(p.getName());
/*
            compositeDisposable.add(pokemonDisplayRepository.searchPokemonsByName(p.getName())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<Pokemon>() {
                        @Override
                        public void onSuccess(Pokemon pokemon) {
                            tmp_interval.add(pokemon);
                        }

                        @Override
                        public void onError(Throwable e) {
                            System.out.println(e.toString());
                        }
                    })


            );


 */
        }

        //TODO : capter lorsque les requêtes de searchByName sont terminées car sinon ça affiche un truc vide
        view.displayPokemons(mapper.map(toMapAndDisplay));

    }

    @Override
    public void addPokemonDetails(int pokemonId) {
        compositeDisposable.add(pokemonDisplayRepository.addPokemonDetails(pokemonId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableCompletableObserver() {
                @Override
                public void onComplete() {
                    view.onPokemonDetailsAdded();
                }

                @Override
                public void onError(Throwable e) {

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
