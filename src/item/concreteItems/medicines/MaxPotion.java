/**
 * 
 */
package item.concreteItems.medicines;

import item.Medicine;
import pokemon.Pokemon;
import pokemon.Stat;

/**
 * @author ellen
 *
 */
public class MaxPotion extends Medicine {
	private static final String NAME = "MAX POTION";
	private static final String DESCRIPTION = "A medicine that fully restores the HP of one Pokémon.";
	private static final int PRICE = 2500;
	private static final Stat STAT = Stat.HP;
	private static final int HEAL_VALUE = 0;

	/**
	 * @param name
	 * @param description
	 * @param price
	 */
	public MaxPotion() {
		super(NAME, DESCRIPTION, PRICE, STAT, HEAL_VALUE);
	}
	
	@Override
	public void useMedicine(Pokemon p) {
		p.restoreFullHP();
	}
}
