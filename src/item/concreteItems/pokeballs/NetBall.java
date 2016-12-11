/**
 * 
 */
package item.concreteItems.pokeballs;

import item.PokeBall;
import pokemon.PkmnType;
import pokemon.Pokemon;
import character.Player;

/**
 * @author ellen
 *
 */
public class NetBall extends PokeBall {

	private static final String NAME = "NET BALL";
	private static final String DESCRIPTION = "A Ball that works better on Bug or Water Types.";
	private static double CATCH_RATE = 1;
	private static final int PRICE = 200;

	/**
	 * @param name
	 * @param description
	 * @param catchRate
	 */
	public NetBall() {
		super(NAME, DESCRIPTION, CATCH_RATE, PRICE);
	}
	
	@Override
	public boolean catchPokemon(Pokemon pkmn, Player player) {
		for(PkmnType t: pkmn.getTypes()) {
			if (t.equals(PkmnType.BUG) || t.equals(PkmnType.WATER)) {
				setCatchRate(3);
			}
		}
		return super.catchPokemon(pkmn, player);
	}
}
