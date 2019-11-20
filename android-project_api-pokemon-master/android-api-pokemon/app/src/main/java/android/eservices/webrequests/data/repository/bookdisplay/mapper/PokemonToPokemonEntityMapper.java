package android.eservices.webrequests.data.repository.bookdisplay.mapper;

import android.eservices.webrequests.data.api.model.Pokemon;
import android.eservices.webrequests.data.api.model.pokemon.AbilityElement;
import android.eservices.webrequests.data.api.model.pokemon.TypeElement;
import android.eservices.webrequests.data.di.FakeDependencyInjection;
import android.eservices.webrequests.data.entity.PokemonEntity;

import java.util.List;

public class PokemonToPokemonEntityMapper {
    public PokemonEntity map(Pokemon pokemon) {
        PokemonEntity pokemonEntity = new PokemonEntity();
        pokemonEntity.setId(pokemon.getId());
        pokemonEntity.setName(pokemon.getSpecies().getName());

        String typesString = "";
        for (TypeElement typeElem : pokemon.getTypes()) {
            typesString += FakeDependencyInjection.toUpperCaseFirstChar(typeElem.getType().getName()) + " | ";
        }
        typesString = typesString.substring(0,typesString.length()-3);
        pokemonEntity.setTypes(typesString);

        String abilitiesString = "";
        for (AbilityElement abilityElem : pokemon.getAbilities()) {
            abilitiesString += FakeDependencyInjection.toUpperCaseFirstChar(abilityElem.getAbility().getName()) + " | ";
        }
        abilitiesString = abilitiesString.substring(0,abilitiesString.length()-3);
        pokemonEntity.setAbilities(abilitiesString);

        pokemonEntity.setWeight(pokemon.getWeight());
        pokemonEntity.setSpriteUrl(pokemon.getSprites().getFront_default());

        return pokemonEntity;
    }
}
