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
public class FullHeal extends Medicine {
	private static final String NAME = "FULL HEAL";
	private static final String DESCRIPTION = "Heals all the status problems of one Pokémon.";
	private static final int PRICE = 600;
	private static final Stat STAT = Stat.HP;
	private static final int HEAL_VALUE = 0;

	/**
	 * @param name
	 * @param description
	 * @param price
	 */
	public FullHeal() {
		super(NAME, DESCRIPTION, PRICE, STAT, HEAL_VALUE);
	}
	
	public void useMedicine(Pokemon p) {
		p.removeAllConditions();
		super.useMedicine(p);
	}
}
