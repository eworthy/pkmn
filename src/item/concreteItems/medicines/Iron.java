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
public class Iron extends Medicine {
	private static final String NAME = "IRON";
	private static final String DESCRIPTION = "A nutritious drink for Pokémon. It raises the base Defense stat of a single Pokémon.";
	private static final int PRICE = 9800;
	private static final Stat STAT = Stat.DEFENSE;
	private static final int EV_RAISE = 10;
	private static final int MAX = 100;

	/**
	 * @param name
	 * @param description
	 * @param price
	 */
	public Iron() {
		super(NAME, DESCRIPTION, PRICE, STAT, EV_RAISE, MAX);
	}
}
