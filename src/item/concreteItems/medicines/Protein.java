/**
 * 
 */
package item.concreteItems.medicines;

import item.Medicine;
import pokemon.Stat;

/**
 * @author ellen
 *
 */
public class Protein extends Medicine {
	private static final String NAME = "PROTEIN";
	private static final String DESCRIPTION = "A nutritious drink for Pokémon. It raises the base Attack stat of a single Pokémon.";
	private static final int PRICE = 9800;
	private static final Stat STAT = Stat.ATTACK;
	private static final int EV_RAISE = 10;
	private static final int MAX = 100;

	/**
	 * @param name
	 * @param description
	 * @param price
	 */
	public Protein() {
		super(NAME, DESCRIPTION, PRICE, STAT, EV_RAISE, MAX);
	}
}
