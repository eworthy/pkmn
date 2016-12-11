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
public class SuperPotion extends Medicine {
	private static final String NAME = "SUPER POTION";
	private static final String DESCRIPTION = "Heals a Pokemon for 50 HP";
	private static final int PRICE = 700;
	private static final Stat STAT = Stat.HP;
	private static final int HEAL_VALUE = 50;

	/**
	 * @param name
	 * @param description
	 * @param price
	 */
	public SuperPotion() {
		super(NAME, DESCRIPTION, PRICE, STAT, HEAL_VALUE);
	}
}
