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
public class LuxuryBall extends PokeBall {

	private static final String NAME = "LUXURY BALL";
	private static final String DESCRIPTION = "A Ball that makes pokemon more friendly";
	private static double CATCH_RATE = 1;
	private static final int PRICE = 200;

	/**
	 * @param name
	 * @param description
	 * @param catchRate
	 */
	public LuxuryBall() {
		super(NAME, DESCRIPTION, CATCH_RATE, PRICE);
	}
	
	@Override
	public boolean catchPokemon(Pokemon pkmn, Player player) {
		boolean successful = super.catchPokemon(pkmn, player);
		if (successful) {
			pkmn.setFriendlinessModifier(2);
		}
		return successful;
	}
}
