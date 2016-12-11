package pokemon;

import character.Player;
import ui.GameManager;

public class Egg {

    private final Pokemon pokemon;
    private final int startSteps;

    public Egg(Player trainer, int pokedexNo, int[] ivs, Nature nature) {
        startSteps = GameManager.getSteps();
        if (nature == null) {
            pokemon = new Pokemon(trainer, pokedexNo, ivs);
        } else {
            pokemon = new Pokemon(trainer, pokedexNo, ivs, nature);
        }
    }

    public Pokemon hatch() {
        if (hatchTimer() <= 0) {
            return pokemon;
        }
        return null;
    }

    public int hatchTimer() {
        return pokemon.getSpecies().getHatchSteps() - 
                (GameManager.getSteps() - startSteps);
    }

}
