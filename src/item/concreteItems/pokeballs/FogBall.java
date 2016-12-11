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
public class FogBall extends PokeBall {

	private static final String NAME = "FOG BALL";
	private static final String DESCRIPTION = "A Ball that works better on evasive Pok�mon";
	private static double CATCH_RATE = 1;
	private static final int PRICE = 200;

	/**
	 * @param name
	 * @param description
	 * @param catchRate
	 */
	public FogBall() {
		super(NAME, DESCRIPTION, CATCH_RATE, PRICE);
	}

	@Override
	public boolean catchPokemon(Pokemon pkmn, Player player) {
		for(PkmnType t: pkmn.getTypes()) {
			if (t.equals(PkmnType.FAIRY)) {
				setCatchRate(3);
			} 
		}
		if (pkmn.getEvasiveness() / pkmn.getLevel() > 5) { //5 is standard evasiveness for this i think? check  me
				setCatchRate(3);
		}
		return super.catchPokemon(pkmn, player);
	}
}
