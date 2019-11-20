package android.eservices.webrequests.data.api.model;

import android.eservices.webrequests.data.api.model.pokemonsearchresponse.PokemonElement;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PokemonSearchResponse {
    //If many pokemon
    @SerializedName("results")
    List<PokemonElement> pokemonElementList;

    public List<PokemonElement> getPokemonElementList() {
        return pokemonElementList;
    }

    public void setPokemonElementList(List<PokemonElement> pokemonElementList) {
        this.pokemonElementList = pokemonElementList;
    }
}
