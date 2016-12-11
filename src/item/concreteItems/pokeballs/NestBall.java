/**
 * 
 */
package item.concreteItems.pokeballs;

import item.PokeBall;
import pokemon.Pokemon;
import character.Player;

/**
 * @author ellen
 *
 */
public class NestBall extends PokeBall {

	//TODO
	private static final String NAME = "DUSK BALL";
	private static final String DESCRIPTION = "A Ball that makes it easier to catch wild Pok�mon in dark places like caves caves and at night";
	private static double CATCH_RATE = 1;
	private static final int PRICE = 200;

	/**
	 * @param name
	 * @param description
	 * @param catchRate
	 */
	public NestBall() {
		super(NAME, DESCRIPTION, CATCH_RATE, PRICE);
	}
	
	@Override
	public boolean catchPokemon(Pokemon pkmn, Player player) {
		//TODO add more variance?
		double d = (40 - pkmn.getLevel()) / 10;
		if (d < -2) {
			// do nothing, leave catch rate as 1
		} else if (d < 1) {
			setCatchRate(2);
		} else {
			setCatchRate(3);
		}
		return super.catchPokemon(pkmn, player);
	}
}
