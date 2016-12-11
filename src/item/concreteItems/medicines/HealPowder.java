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
public class HealPowder extends Medicine {
	private static final String NAME = "HealPowder";
	private static final String DESCRIPTION = "A very bitter medicine powder. It heals all the status problems of a single Pokémon.";
	private static final int PRICE = 450;
	private static final Stat STAT = Stat.HP;
	private static final int HEAL_VALUE = 0;

	/**
	 * @param name
	 * @param description
	 * @param price
	 */
	public HealPowder() {
		super(NAME, DESCRIPTION, PRICE, STAT, HEAL_VALUE);
	}
	
	@Override
	public void useMedicine(Pokemon p) {
		p.setFriendliness(-1);
		p.removeAllConditions();
	}
}
