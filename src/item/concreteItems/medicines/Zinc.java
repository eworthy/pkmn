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
public class Zinc extends Medicine {
	private static final String NAME = "ZINC";
	private static final String DESCRIPTION = "A nutritious drink for Pokémon. It raises the base Sp. Def stat of a single Pokémon.";
	private static final int PRICE = 9800;
	private static final Stat STAT = Stat.SP_DEFENSE;
	private static final int EV_RAISE = 10;
	private static final int MAX = 100;

	/**
	 * @param name
	 * @param description
	 * @param price
	 */
	public Zinc() {
		super(NAME, DESCRIPTION, PRICE, STAT, EV_RAISE, MAX);
	}
}
