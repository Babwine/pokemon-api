package android.eservices.webrequests.presentation.pokemondisplay.list.adapter;

/**
 * A class for a view model which will represent a Pokemon
 */
public class PokemonItemViewModel {
    private int pokemonId;
    private String spriteUrl;
    private String pokemonName;

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

}
