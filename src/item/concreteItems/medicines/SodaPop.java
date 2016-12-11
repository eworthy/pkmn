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
public class SodaPop extends Medicine {
	private static final String NAME = "SODA POP";
	private static final String DESCRIPTION = "A fizzy soda drink. It restores the HP of one Pokémon by 60 points.";
	private static final int PRICE = 300;
	private static final Stat STAT = Stat.HP;
	private static final int HEAL_VALUE = 60;

	/**
	 * @param name
	 * @param description
	 * @param price
	 */
	public SodaPop() {
		super(NAME, DESCRIPTION, PRICE, STAT, HEAL_VALUE);
	}
}
