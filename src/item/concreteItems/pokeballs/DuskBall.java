/**
 * 
 */
package item.concreteItems.pokeballs;

import item.PokeBall;
import pokemon.Pokemon;
import character.Player;
import ui.GameEnvironment;

/**
 * @author ellen
 *
 */
public class DuskBall extends PokeBall {

	private static final String NAME = "DUSK BALL";
	private static final String DESCRIPTION = "A Ball that makes it easier to catch wild in dark places like caves caves and at night";
	private static double CATCH_RATE = 1;
	private static final int PRICE = 200;

	/**
	 * @param name
	 * @param description
	 * @param catchRate
	 */
	public DuskBall() {
		super(NAME, DESCRIPTION, CATCH_RATE, PRICE);
	}

	@Override
	public boolean catchPokemon(Pokemon pkmn, Player player) {
		if (GameEnvironment.isNight()) {
			setCatchRate(3.5);
		}
		return super.catchPokemon(pkmn, player);
	}
}
