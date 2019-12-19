package android.eservices.webrequests.data.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * A class for a Pokemon entity to stock in the database
 */
@Entity
public class PokemonEntity {
    @NonNull
    @PrimaryKey
    public int id;
    public String name;
    public String types;
    public String abilities;
    public int weight;
    public String spriteUrl;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public void setAbilities(String abilities) {
        this.abilities = abilities;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setSpriteUrl(String spriteUrl) {
        this.spriteUrl = spriteUrl;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTypes() {
        return types;
    }

    public String getAbilities() {
        return abilities;
    }

    public int getWeight() {
        return weight;
    }

    public String getSpriteUrl() {
        return spriteUrl;
    }
}
