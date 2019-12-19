package android.eservices.webrequests.presentation.pokemondisplay.grid.mapper;

import android.eservices.webrequests.data.api.model.Pokemon;
import android.eservices.webrequests.data.api.model.pokemonsearchresponse.PokemonElement;
import android.eservices.webrequests.data.di.FakeDependencyInjection;
import android.eservices.webrequests.presentation.pokemondisplay.grid.adapter.PokemonItemViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A class for the mapper which maps a Pokemon into a View Model
 */
public class PokemonToViewModelMapper {
    /**
     * A function to map a Pokemon into a view model
     * @param pokemon the Pokemon to map
     * @return the mapped view model
     */
    public PokemonItemViewModel map(Pokemon pokemon) {
        PokemonItemViewModel pokemonItemViewModel = new PokemonItemViewModel();

        pokemonItemViewModel.setPokemonId(pokemon.getId());
        pokemonItemViewModel.setPokemonName(FakeDependencyInjection.toUpperCaseFirstChar(pokemon.getSpecies().getName()));

        pokemonItemViewModel.setSpriteUrl(pokemon.getSprites().getFront_default());

        return pokemonItemViewModel;
    }
    /**
     * A function to map a PokemonElement, which comes from a PokemonSearchResponse's list, into a view model
     * @param pokemonElem the PokemonElement to map
     * @return the mapped view model
     */
    public PokemonItemViewModel mapElem(PokemonElement pokemonElem) {
        PokemonItemViewModel pokemonItemViewModel = new PokemonItemViewModel();

        int index = pokemonElem.getUrl().substring(0,pokemonElem.getUrl().length()-1).lastIndexOf("/");
        int id = Integer.parseInt(pokemonElem.getUrl().substring(index+1, pokemonElem.getUrl().length()-1));

        pokemonItemViewModel.setPokemonId(id);
        pokemonItemViewModel.setPokemonName(FakeDependencyInjection.toUpperCaseFirstChar(pokemonElem.getName()));

        pokemonItemViewModel.setSpriteUrl("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+id+".png");

        return pokemonItemViewModel;
    }
    /**
     * A function to map all Pokemon in the list into a list of corresponding view models
     * @param pokemonList the list of Pokemon to map
     * @return the list of mapped view models
     */
    public List<PokemonItemViewModel> map(List<Pokemon> pokemonList) {
        List<PokemonItemViewModel> pokemonItemViewModelList = new ArrayList<>();
        for (Pokemon pokemon : pokemonList) {
            pokemonItemViewModelList.add(map(pokemon));
        }
        return pokemonItemViewModelList;
    }
    /**
     * A function to map all PokemonElement in the list into a list of corresponding view models
     * @param pokemonElemList the list of PokemonElement to map
     * @return the list of mapped view models
     */
    public List<PokemonItemViewModel> mapElem(List<PokemonElement> pokemonElemList) {
        List<PokemonItemViewModel> pokemonItemViewModelList = new ArrayList<>();
        for (PokemonElement pokemonElem : pokemonElemList) {
            pokemonItemViewModelList.add(mapElem(pokemonElem));
        }
        return pokemonItemViewModelList;
    }




}
