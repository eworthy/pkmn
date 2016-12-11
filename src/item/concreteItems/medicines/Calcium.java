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
public class Calcium extends Medicine {
	private static final String NAME = "CALCIUM";
	private static final String DESCRIPTION = "A nutritious drink for Pokémon. It raises the base Sp. Atk stat of a single Pokémon.";
	private static final int PRICE = 9800;
	private static final Stat STAT = Stat.SP_ATTACK;
	private static final int EV_RAISE = 10;
	private static final int MAX = 100;

	/**
	 * @param name
	 * @param description
	 * @param price
	 */
	public Calcium() {
		super(NAME, DESCRIPTION, PRICE, STAT, EV_RAISE, MAX);
	}
}
