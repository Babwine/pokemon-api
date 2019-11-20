package android.eservices.webrequests.data.api.model;

import android.eservices.webrequests.data.api.model.pokemon.AbilityElement;
import android.eservices.webrequests.data.api.model.pokemon.Species;
import android.eservices.webrequests.data.api.model.pokemon.Sprites;
import android.eservices.webrequests.data.api.model.pokemon.TypeElement;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pokemon {

    private int id;
    private Sprites sprites;
    private Species species;
    @SerializedName("types")
    private List<TypeElement> types;
    private int weight;
    @SerializedName("abilities")
    private List<AbilityElement> abilities;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public List<TypeElement> getTypes() {
        return types;
    }

    public void setTypes(List<TypeElement> types) {
        this.types = types;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<AbilityElement> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<AbilityElement> abilities) {
        this.abilities = abilities;
    }
}
