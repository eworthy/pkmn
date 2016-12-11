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
public class OldGateau extends Medicine {
	private static final String NAME = "Old Gateau";
	private static final String DESCRIPTION = "Old Chateau's hidden specialty. It heals all the status problems of a single Pokémon.";
	private static final int PRICE = 200;
	private static final Stat STAT = Stat.HP;
	private static final int HEAL_VALUE = 0;

	/**
	 * @param name
	 * @param description
	 * @param price
	 */
	public OldGateau() {
		super(NAME, DESCRIPTION, PRICE, STAT, HEAL_VALUE);
	}
	
	public void useMedicine(Pokemon p) {
		p.removeAllConditions();
		super.useMedicine(p);
	}
}
