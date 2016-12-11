/**
 *
 */
package location;

import item.Purchasable;
import map.GridMap;
import map.tile.Tile;
import pokemon.Pokemon;

/**
 * @author ellen
 *
 */
public class PokemonCenter extends Shop {

    private final static String NAME = "POKEMON CENTER";
    private final static String DESCRIPTION = "A place to heal your pokemon, and purchase items.";
    private final static String[] OPTIONS = {"Rest Pokemon",
        "Shop",
        "Leave"};

    private static GridMap map;

    /**
     *
     */
    public PokemonCenter(Purchasable[] stock) {
        super(NAME, DESCRIPTION, OPTIONS, stock);
    }

    public void restPokemon(Pokemon[] playerPkmn) {
        for (Pokemon p : playerPkmn) {
            p.resetHealth();
        }
    }

    /**
     * TODO allow for misc npcs in the center 
     * @param charMap 
     */
    public static void setUp(char[][] charMap) {
        map = new GridMap(NAME, charMap);
    }
}
