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
public class QuickBall extends PokeBall {

	private static final String NAME = "QUICK BALL";
	private static final String DESCRIPTION = "A Pok�ball that provides a better catch rate if it is used at the start of a wild encounter";
	private static double CATCH_RATE = 1;
	private static final int PRICE = 200;

	/**
	 * @param name
	 * @param description
	 * @param catchRate
	 */
	public QuickBall() {
		super(NAME, DESCRIPTION, CATCH_RATE, PRICE);
	}
	
	@Override
	public boolean catchPokemon(Pokemon pkmn, Player player) {
		if (GameManager.getBattle().getNumTurns() == 1) {
			setCatchRate(4);
		}
		return super.catchPokemon(pkmn, player);
	}
}
