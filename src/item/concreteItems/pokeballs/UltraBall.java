/**
 * 
 */
package item.concreteItems.pokeballs;

import item.PokeBall;

/**
 * @author ellen
 *
 */
public class UltraBall extends PokeBall {

	private static final String NAME = "ULTRA BALL";
	private static final String DESCRIPTION = "A ball with a better catch rate than a Great Ball";
	private static final double CATCH_RATE = 2;
	private static final int PRICE = 500;

	/**
	 * @param name
	 * @param description
	 * @param catchRate
	 */
	public UltraBall() {
		super(NAME, DESCRIPTION, CATCH_RATE, PRICE);
	}
}
