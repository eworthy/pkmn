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
public class HyperPotion extends Medicine {
	private static final String NAME = "HYPER POTION";
	private static final String DESCRIPTION = "Heals a Pokemon for 200 HP";
	private static final int PRICE = 1200;
	private static final Stat STAT = Stat.HP;
	private static final int HEAL_VALUE = 200;

	/**
	 * @param name
	 * @param description
	 * @param price
	 */
	public HyperPotion() {
		super(NAME, DESCRIPTION, PRICE, STAT, HEAL_VALUE);
	}
}
