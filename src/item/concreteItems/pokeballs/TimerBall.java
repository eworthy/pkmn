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
public class TimerBall extends PokeBall {

    private static final String NAME = "TIMER BALL";
    private static final String DESCRIPTION = "A Ball that works better the longer the battle has run";
    private static double CATCH_RATE = 1;
    private static final int PRICE = 200;

    /**
     * @param name
     * @param description
     * @param catchRate
     */
    public TimerBall() {
        super(NAME, DESCRIPTION, CATCH_RATE, PRICE);
    }

    @Override
    public boolean catchPokemon(Pokemon pkmn, Player player) {
        int battleTurns = GameManager.getBattle().getNumTurns();
        setCatchRate((battleTurns + 10) / 10);
        return super.catchPokemon(pkmn, player);
    }
}
