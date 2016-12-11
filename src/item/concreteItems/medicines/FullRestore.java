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
public class FullRestore extends Medicine {
	private static final String NAME = "FULL RESTORE";
	private static final String DESCRIPTION = "A medicine that fully restores the HP and heals any status problems of a single Pokémon.";
	private static final int PRICE = 3000;
	private static final Stat STAT = Stat.HP;
	private static final int HEAL_VALUE = 0;

	/**
	 * @param name
	 * @param description
	 * @param price
	 */
	public FullRestore() {
		super(NAME, DESCRIPTION, PRICE, STAT, HEAL_VALUE);
	}
	
	@Override
	public void useMedicine(Pokemon p) {
		p.resetHealth();
	}
}
