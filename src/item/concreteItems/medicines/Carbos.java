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
public class Carbos extends Medicine {
	private static final String NAME = "CARBOS";
	private static final String DESCRIPTION = "A nutritious drink for Pokémon. It raises the base Speed stat of a single Pokémon.";
	private static final int PRICE = 9800;
	private static final Stat STAT = Stat.SPEED;
	private static final int EV_RAISE = 10;
	private static final int MAX = 100;

	/**
	 * @param name
	 * @param description
	 * @param price
	 */
	public Carbos() {
		super(NAME, DESCRIPTION, PRICE, STAT, EV_RAISE, MAX);
	}
}
