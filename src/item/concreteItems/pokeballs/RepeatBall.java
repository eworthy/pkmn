/**
 * 
 */
package item.concreteItems.pokeballs;

import item.PokeBall;
import pokemon.Pokemon;
import character.Player;
import ui.GameManager;

/**
 * @author ellen
 *
 */
public class RepeatBall extends PokeBall {

	private static final String NAME = "REPEAT BALL";
	private static final String DESCRIPTION = "A Ball that works better on Pok�mon previously captured.";
	private static double CATCH_RATE = 1;
	private static final int PRICE = 200;

	/**
	 * @param name
	 * @param description
	 * @param catchRate
	 */
	public RepeatBall() {
		super(NAME, DESCRIPTION, CATCH_RATE, PRICE);
	}

	@Override
	public boolean catchPokemon(Pokemon pkmn, Player player) {
		if (GameManager.pokedex.checkFoundPkmn((pkmn.getPokedexNo()))) {
			setCatchRate(3);
		}
		return super.catchPokemon(pkmn, player);
	}
}
