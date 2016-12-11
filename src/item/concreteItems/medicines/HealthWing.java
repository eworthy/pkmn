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
public class HealthWing extends Medicine {
	private static final String NAME = "Health Wing";
	private static final String DESCRIPTION = "An item for use on a Pok�mon. It slightly increases the base HP of a single Pok�mon.";
	private static final int PRICE = 3000;
	private static final Stat STAT = Stat.HP;
	private static final int EV_RAISE = 1;

	/**
	 * @param name
	 * @param description
	 * @param price
	 */
	public HealthWing() {
		super(NAME, DESCRIPTION, PRICE, STAT, EV_RAISE);
	}
	
	public void useMedicine(Pokemon p) {
		p.setHPEV(EV_RAISE);
	}
}
