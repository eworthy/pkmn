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
public class MoomooMilk extends Medicine {
	private static final String NAME = "Moomoo Milk";
	private static final String DESCRIPTION = "Milk with a very high nutrition content. It restores the HP of one Pokémon by 100 points.";
	private static final int PRICE = 500;
	private static final Stat STAT = Stat.HP;
	private static final int HEAL_VALUE = 100;

	/**
	 * @param name
	 * @param description
	 * @param price
	 */
	public MoomooMilk() {
		super(NAME, DESCRIPTION, PRICE, STAT, HEAL_VALUE);
	}
}
