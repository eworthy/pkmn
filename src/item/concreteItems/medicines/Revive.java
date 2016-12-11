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
public class Revive extends Medicine {
	private static final String NAME = "REVIVE";
	private static final String DESCRIPTION = "A medicine that revives a fainted Pokémon. It restores half the Pokémon's maximum HP.";
	private static final int PRICE = 1500;
	private static final Stat STAT = Stat.HP;
	private static final int HEAL_VALUE = 0;

	/**
	 * @param name
	 * @param description
	 * @param price
	 */
	public Revive() {
		super(NAME, DESCRIPTION, PRICE, STAT, HEAL_VALUE);
	}
	
	public void useMedicine(Pokemon p) {
		p.removeAllConditions(); // fainting is meant to remove all conditions
		p.setCurrentHP(p.getMaxHP() / 2); // since currentHP is 0 its okay to just add
	}
}
