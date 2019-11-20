package android.eservices.webrequests.presentation.bookdisplay.list.mapper;

import android.eservices.webrequests.data.api.model.Pokemon;
import android.eservices.webrequests.data.api.model.pokemon.AbilityElement;
import android.eservices.webrequests.data.api.model.pokemon.TypeElement;
import android.eservices.webrequests.data.di.FakeDependencyInjection;
import android.eservices.webrequests.presentation.bookdisplay.list.adapter.PokemonItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class PokemonToViewModelMapper {

    public PokemonItemViewModel map(Pokemon pokemon) {
        PokemonItemViewModel pokemonItemViewModel = new PokemonItemViewModel();

        pokemonItemViewModel.setPokemonId(pokemon.getId());
        pokemonItemViewModel.setPokemonName(FakeDependencyInjection.toUpperCaseFirstChar(pokemon.getSpecies().getName()));

        String typesString = "";
        for (TypeElement typeElem : pokemon.getTypes()) {
            typesString += FakeDependencyInjection.toUpperCaseFirstChar(typeElem.getType().getName()) + " | ";
        }
        typesString = typesString.substring(0,typesString.length()-3);
        pokemonItemViewModel.setPokemonTypes(typesString);

        pokemonItemViewModel.setSpriteUrl(pokemon.getSprites().getFront_default());

        return pokemonItemViewModel;
    }

    public List<PokemonItemViewModel> map(List<Pokemon> pokemonList) {
        List<PokemonItemViewModel> pokemonItemViewModelList = new ArrayList<>();
        for (Pokemon book : pokemonList) {
            pokemonItemViewModelList.add(map(book));
        }
        return pokemonItemViewModelList;
    }




}
