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
public class BasicPotion extends Medicine {
	private static final String NAME = "POTION";
	private static final String DESCRIPTION = "Heals a Pokemon for 20 HP";
	private static final int PRICE = 300;
	private static final Stat STAT = Stat.HP;
	private static final int HEAL_VALUE = 20;

	/**
	 * @param name
	 * @param description
	 * @param price
	 */
	public BasicPotion() {
		super(NAME, DESCRIPTION, PRICE, STAT, HEAL_VALUE);
	}
}
