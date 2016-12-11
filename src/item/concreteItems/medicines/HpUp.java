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
public class HpUp extends Medicine {
	private static final String NAME = "HP Up";
	private static final String DESCRIPTION = "A nutritious drink for Pokémon. It raises the base HP of a single Pokémon.";
	private static final int PRICE = 9800;
	private static final Stat STAT = Stat.HP;
	private static final int EV_RAISE = 10;
	private static final int MAX = 100;

	/**
	 * @param name
	 * @param description
	 * @param price
	 */
	public HpUp() {
		super(NAME, DESCRIPTION, PRICE, STAT, EV_RAISE, MAX);
	}
	
	public void useMedicine(Pokemon p) {
		if (p.getHPEV() < 100) {
			p.setHPEV(EV_RAISE, MAX);
		}
		p.setFriendliness(1);
	}
}
