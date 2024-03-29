package android.eservices.webrequests.presentation.pokemondisplay.list.fragment;

import android.content.Intent;
import android.eservices.webrequests.data.di.FakeDependencyInjection;
import android.eservices.webrequests.presentation.pokemondisplay.details.activity.PokemonDetailsDisplayActivity;
import android.eservices.webrequests.presentation.pokemondisplay.list.PokemonListContract;
import android.eservices.webrequests.presentation.pokemondisplay.list.PokemonListPresenter;
import android.eservices.webrequests.presentation.pokemondisplay.list.adapter.PokemonActionInterface;
import android.eservices.webrequests.presentation.pokemondisplay.list.adapter.PokemonAdapter;
import android.eservices.webrequests.presentation.pokemondisplay.list.adapter.PokemonItemViewModel;
import android.eservices.webrequests.presentation.pokemondisplay.list.mapper.PokemonToViewModelMapper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.appcompat.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.eservices.webrequests.R;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A class for the fragment that displays all Pokémon as a List
 */
public class ListFragment extends Fragment implements PokemonListContract.View, PokemonActionInterface {

    public static final String TAB_NAME = "List";
    private View rootView;
    private PokemonListContract.Presenter pokemonListPresenter;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private PokemonAdapter pokemonAdapter;
    private ProgressBar progressBar;


    private ListFragment() {}

    public static ListFragment newInstance() {
        return new ListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_list, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupSearchView();
        setupRecyclerView();
        progressBar = rootView.findViewById(R.id.progress_bar);

        pokemonListPresenter = new PokemonListPresenter(FakeDependencyInjection.getPokemonDisplayRepository(), new PokemonToViewModelMapper());
        pokemonListPresenter.attachView(this);
        this.displayPokemonXtoXPlusY(0,807);
    }

    /**
     * A function to setup the search bar and its behaviour
     */
    private void setupSearchView() {
        searchView = rootView.findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            private Timer timer = new Timer();

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(final String s) {
                if (s.length() == 0) {
                    pokemonListPresenter.cancelSubscription();
                    pokemonListPresenter.searchPokemonByInterval(0,807);
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    timer.cancel();
                    timer = new Timer();
                    int sleep = 350;
                    if (s.length() == 1)
                        sleep = 5000;
                    else if (s.length() <= 3)
                        sleep = 300;
                    else if (s.length() <= 5)
                        sleep = 200;
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            pokemonListPresenter.searchPokemonByName(s.toLowerCase());
                        }
                    }, sleep);
                }
                return true;
            }
        });
    }

    /**
     * A function to setup the recyclerview used to display the list
     */
    private void setupRecyclerView() {
        recyclerView = rootView.findViewById(R.id.recycler_view);
        pokemonAdapter = new PokemonAdapter(this);
        recyclerView.setAdapter(pokemonAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        pokemonListPresenter.detachView();
    }

    @Override
    public void displayPokemons(List<PokemonItemViewModel> pokemonItemViewModelList) {
        progressBar.setVisibility((View.GONE));
        pokemonAdapter.bindViewModels(pokemonItemViewModelList);
    }


    @Override
    public void onPokemonClicked(int pokemonId) {
        Intent i = new Intent(getContext(), PokemonDetailsDisplayActivity.class);
        i.putExtra("pokemonId", pokemonId);
        startActivity(i);
    }

    /**
     * A function to display a list of Pokémon which the first is the number <code>x</code> and the last is the number <code>x</code>+<code>y</code>
     * @param x the index of the offset of the list
     * @param y the index of the limit of the list
     */
    public void displayPokemonXtoXPlusY(int x, int y) {
        pokemonListPresenter.searchPokemonByInterval(x, y);
    }
}
