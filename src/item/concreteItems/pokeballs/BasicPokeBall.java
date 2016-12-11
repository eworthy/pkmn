/**
 * 
 */
package item.concreteItems.pokeballs;

import item.PokeBall;

/**
 * @author ellen
 *
 */
public class BasicPokeBall extends PokeBall {

	private static final String NAME = "POKEBALL";
	private static final String DESCRIPTION = "A device for catching wild pokemon";
	private static final double CATCH_RATE = 1;
	private static final int PRICE = 200;

	/**
	 * @param name
	 * @param description
	 * @param catchRate
	 */
	public BasicPokeBall() {
		super(NAME, DESCRIPTION, CATCH_RATE, PRICE);
	}
}
