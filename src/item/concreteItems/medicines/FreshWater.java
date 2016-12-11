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
public class FreshWater extends Medicine {
	private static final String NAME = "FRESH WATER";
	private static final String DESCRIPTION = "Water with a high mineral content. It restores the HP of one Pokémon by 50 points.";
	private static final int PRICE = 200;
	private static final Stat STAT = Stat.HP;
	private static final int HEAL_VALUE = 50;

	/**
	 * @param name
	 * @param description
	 * @param price
	 */
	public FreshWater() {
		super(NAME, DESCRIPTION, PRICE, STAT, HEAL_VALUE);
	}
}
