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
public class Lemonade extends Medicine {
	private static final String NAME = "LEMONADE";
	private static final String DESCRIPTION = "A very sweet drink. It restores the HP of one Pokémon by 80 points.";
	private static final int PRICE = 350;
	private static final Stat STAT = Stat.HP;
	private static final int HEAL_VALUE = 80;

	/**
	 * @param name
	 * @param description
	 * @param price
	 */
	public Lemonade() {
		super(NAME, DESCRIPTION, PRICE, STAT, HEAL_VALUE);
	}
}
