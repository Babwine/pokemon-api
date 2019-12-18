package android.eservices.webrequests.presentation.pokemondisplay.details.activity;

import android.app.Activity;
import android.eservices.webrequests.R;
import android.eservices.webrequests.data.di.FakeDependencyInjection;
import android.eservices.webrequests.presentation.pokemondisplay.details.PokemonDetailsContract;
import android.eservices.webrequests.presentation.pokemondisplay.details.PokemonDetailsPresenter;
import android.eservices.webrequests.presentation.pokemondisplay.details.adapter.PokemonDetailsActionInterface;
import android.eservices.webrequests.presentation.pokemondisplay.details.adapter.PokemonDetailsAdapter;
import android.eservices.webrequests.presentation.pokemondisplay.details.adapter.PokemonDetailsItemViewModel;
import android.eservices.webrequests.presentation.pokemondisplay.details.mapper.PokemonDetailsToViewModelMapper;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PokemonDetailsDisplayActivity extends Activity implements PokemonDetailsContract.View, PokemonDetailsActionInterface {
    private PokemonDetailsContract.Presenter pokemonDetailsPresenter;
    private PokemonDetailsAdapter pokemonDetailsAdapter;
    private RecyclerView recyclerView;

    private int pokemonId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null) this.pokemonId = extras.getInt("pokemonId");
        setContentView(R.layout.activity_details);


        pokemonDetailsAdapter = new PokemonDetailsAdapter(this);
        pokemonDetailsPresenter = new PokemonDetailsPresenter(FakeDependencyInjection.getPokemonDisplayRepository(), new PokemonDetailsToViewModelMapper());
        pokemonDetailsPresenter.attachView(this);
        setupRecyclerView();
        setupDetailsView();
    }

    private void setupDetailsView() {
        pokemonDetailsPresenter.getPokemonById(this.pokemonId);
    }

    private void setupRecyclerView() {
        recyclerView = this.findViewById(R.id.pokemon_details_recycler_view);
        pokemonDetailsAdapter = new PokemonDetailsAdapter(this);
        recyclerView.setAdapter(pokemonDetailsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        pokemonDetailsPresenter.detachView();
    }


    @Override
    public void displayPokemon(PokemonDetailsItemViewModel pokemonDetailsItemViewModel) {
        pokemonDetailsAdapter.bindViewModels(pokemonDetailsItemViewModel);
    }
}
