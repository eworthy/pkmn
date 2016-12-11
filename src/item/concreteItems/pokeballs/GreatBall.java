/**
 * 
 */
package item.concreteItems.pokeballs;

import item.PokeBall;

/**
 * @author ellen
 *
 */
public class GreatBall extends PokeBall {

	private static final String NAME = "GREAT BALL";
	private static final String DESCRIPTION = "A ball with a better catch rate than a PokeBall";
	private static final double CATCH_RATE = 1.5;
	private static final int PRICE = 200; //TODO

	/**
	 * @param name
	 * @param description
	 * @param catchRate
	 */
	public GreatBall() {
		super(NAME, DESCRIPTION, CATCH_RATE, PRICE);
	}
}
