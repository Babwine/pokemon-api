package android.eservices.webrequests.data.api.model.pokemon;

import android.eservices.webrequests.data.api.model.pokemon.abilityelement.Ability;

/**
 * A class to represent the Abilities list of a Pokemon
 */
public class AbilityElement {
    private Ability ability;

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }
}
