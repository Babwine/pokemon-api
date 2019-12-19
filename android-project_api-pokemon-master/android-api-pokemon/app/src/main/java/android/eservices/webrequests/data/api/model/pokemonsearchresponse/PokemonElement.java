package android.eservices.webrequests.data.api.model.pokemonsearchresponse;

/**
 * A class to represent a Pokemon in the Pokemon list got and stocked in a PokemonSearchResponse object
 */
public class PokemonElement {
    private String name;
    public String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
