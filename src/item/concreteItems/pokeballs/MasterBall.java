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
public class MasterBall extends PokeBall {

	private static final String NAME = "MASTER BALL";
	private static final String DESCRIPTION = "A device that never fails at capture";
	private static final double CATCH_RATE = 0;
	private static final int PRICE = 200;

	/**
	 * @param name
	 * @param description
	 * @param catchRate
	 */
	public MasterBall() {
		super(NAME, DESCRIPTION, CATCH_RATE, PRICE);
	}
	
	@Override
	public boolean catchPokemon(Pokemon pkmn, Player player) {
		return true;
	}
}
