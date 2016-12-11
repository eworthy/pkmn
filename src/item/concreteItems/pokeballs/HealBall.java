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
public class HealBall extends PokeBall {

	private static final String NAME = "HEAL BALL";
	private static final String DESCRIPTION = "A remedial Pok�ball that restores the caught Pok�mon's HP and eliminates any status problem.";
	private static double CATCH_RATE = 1;
	private static final int PRICE = 200;

	/**
	 * @param name
	 * @param description
	 * @param catchRate
	 */
	public HealBall() {
		super(NAME, DESCRIPTION, CATCH_RATE, PRICE);
	}
	
	@Override
	public boolean catchPokemon(Pokemon pkmn, Player player) {
		boolean successful = super.catchPokemon(pkmn, player);
		if (successful) {
			pkmn.resetHealth();
		}
		return successful;
	}
}
