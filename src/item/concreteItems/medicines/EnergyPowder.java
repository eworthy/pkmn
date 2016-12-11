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
public class EnergyPowder extends Medicine {
	private static final String NAME = "EnergyPowder";
	private static final String DESCRIPTION = "A very bitter medicine powder. It restores the HP of one Pokémon by 50 points.";
	private static final int PRICE = 500;
	private static final Stat STAT = Stat.HP;
	private static final int HEAL_VALUE = 50;

	/**
	 * @param name
	 * @param description
	 * @param price
	 */
	public EnergyPowder() {
		super(NAME, DESCRIPTION, PRICE, STAT, HEAL_VALUE);
	}
	
	@Override
	public void useMedicine(Pokemon p) {
		p.setFriendliness(-1);
		super.useMedicine(p);
	}
}
