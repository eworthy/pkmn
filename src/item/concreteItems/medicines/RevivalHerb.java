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
public class RevivalHerb extends Medicine {
	private static final String NAME = "RevivalHerb";
	private static final String DESCRIPTION = "A very bitter medicinal herb. It revives a fainted Pokémon, fully restoring its HP.";
	private static final int PRICE = 2800;
	private static final Stat STAT = Stat.HP;
	private static final int HEAL_VALUE = 0;

	/**
	 * @param name
	 * @param description
	 * @param price
	 */
	public RevivalHerb() {
		super(NAME, DESCRIPTION, PRICE, STAT, HEAL_VALUE);
	}
	
	public void useMedicine(Pokemon p) {
		p.resetHealth();
		p.setFriendliness(-1);
	}
}
