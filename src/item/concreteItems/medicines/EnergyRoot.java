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
public class EnergyRoot extends Medicine {
	private static final String NAME = "EnergyRoot";
	private static final String DESCRIPTION = "A very bitter root. It restores the HP of one Pokémon by 200 points.";
	private static final int PRICE = 800;
	private static final Stat STAT = Stat.HP;
	private static final int HEAL_VALUE = 200;

	/**
	 * @param name
	 * @param description
	 * @param price
	 */
	public EnergyRoot() {
		super(NAME, DESCRIPTION, PRICE, STAT, HEAL_VALUE);
	}
	
	@Override
	public void useMedicine(Pokemon p) {
		p.setFriendliness(-1);
		super.useMedicine(p);
	}
}
