package android.eservices.webrequests.presentation.pokemondisplay.details.adapter;

/**
 * A class for a Pokemon details based view model
 */
public class PokemonDetailsItemViewModel {

    private int pokemonId;
    private String spriteUrl;
    private String pokemonName;
    private String pokemonTypes;
    private String pokemonAblities;

    public int getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(int pokemonId) {
        this.pokemonId = pokemonId;
    }

    public String getSpriteUrl() {
        return spriteUrl;
    }

    public void setSpriteUrl(String spriteUrl) {
        this.spriteUrl = spriteUrl;
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }

    public String getPokemonTypes() {
        return pokemonTypes;
    }

    public void setPokemonTypes(String pokemonTypes) {
        this.pokemonTypes = pokemonTypes;
    }

    public String getPokemonAblities() {
        return pokemonAblities;
    }

    public void setPokemonAblities(String pokemonAblities) {
        this.pokemonAblities = pokemonAblities;
    }


}
