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
public class MaxRevive extends Medicine {
	private static final String NAME = "MAX REVIVE";
	private static final String DESCRIPTION = "A medicine that revives a fainted Pokémon. It fully restores the Pokémon's HP.";
	private static final int PRICE = 4000;
	private static final Stat STAT = Stat.HP;
	private static final int HEAL_VALUE = 0;

	/**
	 * @param name
	 * @param description
	 * @param price
	 */
	public MaxRevive() {
		super(NAME, DESCRIPTION, PRICE, STAT, HEAL_VALUE);
	}
	
	public void useMedicine(Pokemon p) {
		p.resetHealth();
	}
}
