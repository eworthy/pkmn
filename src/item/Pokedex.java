package item;

import java.io.Serializable;
import java.util.HashMap;

import pokemon.Pokemon;
import pokemon.Species;

public class Pokedex implements Serializable {

    private final static String NAME = "POKEDEX";
    private static final int NUM_PKMN = 150;

    /**
     * Pokedex singleton instance
     */
    private static Pokedex pokedex;

    private static HashMap<Integer, Species> pkmnInfo;
    private static boolean[] seen;
    private static boolean[] found;

    /**
     *
     */
    private Pokedex() {
        pkmnInfo = new HashMap<>(NUM_PKMN);
        seen = new boolean[NUM_PKMN];
        found = new boolean[NUM_PKMN];
    }

    /**
     * Returns the singleton instance of CourseManager. If the instance doesn't
     * exist, it will be created.
     *
     * @return singleton instance
     */
    public static Pokedex getInstance() {
        if (pokedex == null) {
            pokedex = new Pokedex();
        }
        return pokedex;
    }

    public String getName() {
        return NAME;
    }

    public boolean foundNewPokemon(Pokemon pkmnFound) {
        seenPokemon(pkmnFound);
        boolean before = found[pkmnFound.getPokedexNo()];
        found[pkmnFound.getPokedexNo()] = true;
        return !before;
    }

    private void seenPokemon(Pokemon pkmnFound) {
        seen[pkmnFound.getPokedexNo()] = true;
    }

    public String getPokemonData(int index) {
        if (index >= NUM_PKMN || index < 0) {
            return "Invalid index";
        }
        if (found[index]) {
            return foundPkmnData(index);
        } else if (seen[index]) {
            return seenPkmnData(index);
        } else {
            return "No data found";
        }
    }

    private String foundPkmnData(int index) {
        return pkmnInfo.get(index).toString();
    }

    private String seenPkmnData(int index) {
        return ((Species) pkmnInfo.get(index)).getName();
    }

    public boolean checkFoundPkmn(int index) {
        return found[index];
    }

    public static void addSpecies(Species species) {
        getInstance();
        pkmnInfo.put(species.getPokedexNo(), species);
    }
    
    public static Species getPokemonSpeciesInfo(int pokedexNo) {
        return pkmnInfo.get(pokedexNo);
    }
}
