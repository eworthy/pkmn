/**
 *
 */
package item.concreteItems.medicines;

import item.Medicine;
import pokemon.Pokemon;
import pokemon.Stat;

/**
 * @author ellen
 *
 */
public class CleverWing extends Medicine {

    private static final String NAME = "Clever Wing";
    private static final String DESCRIPTION = "An item for use on a Pok�mon. It slightly increases the base Sp. Def stat of a single Pok�mon.";
    private static final int PRICE = 3000;
    private static final Stat STAT = Stat.SP_DEFENSE;
    private static final int EV_RAISE = 1;

    /**
     * @param name
     * @param description
     * @param price
     */
    public CleverWing() {
        super(NAME, DESCRIPTION, PRICE, STAT, EV_RAISE);
    }

    public void useMedicine(Pokemon p) {
        p.setSpDefenseEV(EV_RAISE);
    }
}
