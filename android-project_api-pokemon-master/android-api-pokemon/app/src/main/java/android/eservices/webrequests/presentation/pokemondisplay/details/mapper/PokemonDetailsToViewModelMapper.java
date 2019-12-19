package android.eservices.webrequests.presentation.pokemondisplay.details.mapper;

import android.eservices.webrequests.data.api.model.Pokemon;
import android.eservices.webrequests.data.api.model.pokemon.AbilityElement;
import android.eservices.webrequests.data.api.model.pokemon.TypeElement;
import android.eservices.webrequests.data.di.FakeDependencyInjection;
import android.eservices.webrequests.presentation.pokemondisplay.details.adapter.PokemonDetailsItemViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A class for a mapper from a Pokemon into a Pokemon details based view model
 */
public class PokemonDetailsToViewModelMapper {

    /**
     * A function to map the Pokemon <code>pokemon</code> into a details view model
     * @param pokemon the Pokemon to map
     * @return the mapped view model
     */
    public PokemonDetailsItemViewModel map(Pokemon pokemon) {
        PokemonDetailsItemViewModel pokemonDetailsItemViewModel = new PokemonDetailsItemViewModel();

        pokemonDetailsItemViewModel.setPokemonId(pokemon.getId());
        pokemonDetailsItemViewModel.setPokemonName(FakeDependencyInjection.toUpperCaseFirstChar(pokemon.getSpecies().getName()));
        pokemonDetailsItemViewModel.setSpriteUrl(pokemon.getSprites().getFront_default());


        String typesString = "";
        for (TypeElement type : pokemon.getTypes()) {
            typesString += FakeDependencyInjection.toUpperCaseFirstChar(type.getType().getName());
            typesString += " | ";
        }
        typesString = "Types : "+typesString.substring(0,typesString.length()-3);

        String abilitiesString = "";
        for (AbilityElement ability : pokemon.getAbilities()) {
            abilitiesString += FakeDependencyInjection.toUpperCaseFirstChar(ability.getAbility().getName());
            abilitiesString += " | ";
        }
        abilitiesString = "Abilities : "+abilitiesString.substring(0,abilitiesString.length()-3);


        pokemonDetailsItemViewModel.setPokemonTypes(typesString);
        pokemonDetailsItemViewModel.setPokemonAblities(abilitiesString);

        return pokemonDetailsItemViewModel;
    }


    /**
     * A function to map every Pokemon in the list into the corresponding details view model
     * @param pokemonList the list of Pokemon to map
     * @return the mapped view models list
     */
    public List<PokemonDetailsItemViewModel> map(List<Pokemon> pokemonList) {
        List<PokemonDetailsItemViewModel> pokemonDetailsItemViewModelList = new ArrayList<>();
        for (Pokemon pokemon : pokemonList) {
            pokemonDetailsItemViewModelList.add(map(pokemon));
        }
        return pokemonDetailsItemViewModelList;
    }

}
