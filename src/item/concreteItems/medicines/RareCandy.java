/**
 * 
 */
package item.concreteItems.medicines;

import item.Medicine;
import pokemon.Stat;

/**
 * @author ellen
 *
 */
public class RareCandy extends Medicine {
	private static final String NAME = "RARE CANDY";
	private static final String DESCRIPTION = "A candy that is packed with energy. It raises the level of a single Pokémon by one.";
	private static final int PRICE = 4800;
	private static final Stat STAT = Stat.LEVEL;
	private static final int EFFECT = 1;

	/**
	 * @param name
	 * @param description
	 * @param price
	 */
	public RareCandy() {
		super(NAME, DESCRIPTION, PRICE, STAT, EFFECT);
	}
}
