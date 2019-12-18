package android.eservices.webrequests.presentation.pokemondisplay.list.mapper;

import android.eservices.webrequests.data.api.model.Pokemon;
import android.eservices.webrequests.data.api.model.pokemonsearchresponse.PokemonElement;
import android.eservices.webrequests.data.di.FakeDependencyInjection;
import android.eservices.webrequests.presentation.pokemondisplay.list.adapter.PokemonItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class PokemonToViewModelMapper {

    public PokemonItemViewModel map(Pokemon pokemon) {
        PokemonItemViewModel pokemonItemViewModel = new PokemonItemViewModel();

        pokemonItemViewModel.setPokemonId(pokemon.getId());
        pokemonItemViewModel.setPokemonName(FakeDependencyInjection.toUpperCaseFirstChar(pokemon.getSpecies().getName()));

        pokemonItemViewModel.setSpriteUrl(pokemon.getSprites().getFront_default());

        return pokemonItemViewModel;
    }

    public PokemonItemViewModel mapElem(PokemonElement pokemonElem) {
        PokemonItemViewModel pokemonItemViewModel = new PokemonItemViewModel();

        int index = pokemonElem.getUrl().substring(0,pokemonElem.getUrl().length()-1).lastIndexOf("/");
        int id = Integer.parseInt(pokemonElem.getUrl().substring(index+1, pokemonElem.getUrl().length()-1));

        pokemonItemViewModel.setPokemonId(id);
        pokemonItemViewModel.setPokemonName(FakeDependencyInjection.toUpperCaseFirstChar(pokemonElem.getName()));

        pokemonItemViewModel.setSpriteUrl("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+id+".png");

        return pokemonItemViewModel;
    }

    public List<PokemonItemViewModel> map(List<Pokemon> pokemonList) {
        List<PokemonItemViewModel> pokemonItemViewModelList = new ArrayList<>();
        for (Pokemon pokemon : pokemonList) {
            pokemonItemViewModelList.add(map(pokemon));
        }
        return pokemonItemViewModelList;
    }

    public List<PokemonItemViewModel> mapElem(List<PokemonElement> pokemonElemList) {
        List<PokemonItemViewModel> pokemonItemViewModelList = new ArrayList<>();
        for (PokemonElement pokemonElem : pokemonElemList) {
            pokemonItemViewModelList.add(mapElem(pokemonElem));
        }
        return pokemonItemViewModelList;
    }




}
