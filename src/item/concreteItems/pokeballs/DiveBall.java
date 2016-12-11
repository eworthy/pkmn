/**
 * 
 */
package item.concreteItems.pokeballs;

import item.PokeBall;
import battle.Zone;
import pokemon.Pokemon;
import character.Player;
import ui.GameManager;

/**
 * @author ellen
 *
 */
public class DiveBall extends PokeBall {

	private static final String NAME = "DIVE BALL";
	private static final String DESCRIPTION = "A Ball that works better on the ocean floor and on Pok�mon on water";
	private static final double CATCH_RATE = 1;
	private static final int PRICE = 200; //TODO

	/**
	 * @param name
	 * @param description
	 * @param catchRate
	 */
	public DiveBall() {
		super(NAME, DESCRIPTION, CATCH_RATE, PRICE);
	}
	
	public boolean catchPokemon(Pokemon pkmn, Player player) {
		if(GameManager.getBattle().getBattleZone() == Zone.UNDERWATER) {
			setCatchRate(3.5); //TODO check
		}
		return super.catchPokemon(pkmn, player);
	}
}
