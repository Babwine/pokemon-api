package android.eservices.webrequests.presentation.bookdisplay.grid.fragment;

import android.eservices.webrequests.presentation.bookdisplay.list.PokemonListContract;
import android.eservices.webrequests.presentation.bookdisplay.list.adapter.PokemonActionInterface;
import android.eservices.webrequests.presentation.bookdisplay.list.adapter.PokemonItemViewModel;

import androidx.fragment.app.Fragment;

import java.util.List;

public class GridFragment extends Fragment implements PokemonListContract.View, PokemonActionInterface {


    public static final String TAB_NAME = "Grid";

    public static GridFragment newInstance() {
        return new GridFragment();
    }

    @Override
    public void displayPokemons(List<PokemonItemViewModel> pokemonItemViewModelList) {

    }

    @Override
    public void onPokemonDetailsAdded() {

    }

    @Override
    public void onPokemonClicked(int pokemonId) {

    }
}
