package android.eservices.webrequests.data.api.model.pokemon;

import android.eservices.webrequests.data.api.model.pokemon.typeelement.Type;

/**
 * A class to represent the Types list of a Pokemon
 */
public class TypeElement {
    private Type type;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
