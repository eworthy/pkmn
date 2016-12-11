package pokemonStorageSystem;

import basicFunctions.UserInput;
import java.util.Scanner;
import pokemon.Pokemon;
import ui.GameManager;

/**
 *
 * @author ellen
 */
public class PokemonStorageSystem {
    private static Scanner keyInput;
    private static final String TITLE = "Pokemon Storage System:";
    private static final String[] CHOICES = {"Deposit Pokémon",
        "Pick Up Pokémon",
        "Switch Pokémon",
        "Move Items",
        "Back"};
    
    private static PokemonStorageSystem pokemonStorageSystem;
    private static Pokemon[][] storage;
    private static String[] boxNames;
    
    private PokemonStorageSystem() {
        storage = new Pokemon[31][30];
        boxNames = new String[31];
        setInitialBoxNames();
        keyInput = new Scanner(System.in);
    }
    
    /**
     * Returns the singleton instance of PokemonStorageSystem. If the instance 
     * doesn't exist, it will be created.
     * 
     * @return singleton instance
     */
    public static PokemonStorageSystem getInstance() {
        if (pokemonStorageSystem == null) {
            pokemonStorageSystem = new PokemonStorageSystem();
        }
        return pokemonStorageSystem;
    }
    
    private void setInitialBoxNames() {
        for (int i = 0; i < boxNames.length; i++) {
            boxNames[i] = "BOX " + (i + 1);
        }
    }
    
    /**
     * Set the name of a box in the storage system.
     * @param boxIndex the index of the box for which the name will be changed.
     * @param boxName the new name to set
     */
    public void setBoxName(int boxIndex, String boxName) {
        boxNames[boxIndex] = boxName;
    }
    
    public void accessPokemonStorageSystem() {
        int choice = UserInput.makeMenu(keyInput, TITLE, CHOICES) - 1;
        while (choice != CHOICES.length - 1) {
            switch(choice) {
                case 0:
                    depositPokemon();
                    break;
                case 1:
                    getPokemon();
                    break;
                case 2:
                    switchPokemon();
                    break;
                case 3:
                    moveItems();
                    break;
            }
        }
    }
    
    /**
     * Deposit Pokemon from the party into the storage boxes.
     */
    public void depositPokemon() {
        int pkmnIndex = UserInput.makeMenu(keyInput, "Which Pokémon?", 
                UserInput.convertToMakeMenuArray(GameManager.getPartyPokemonList()));
        Pokemon p = GameManager.getPlayer().getPartyMember(pkmnIndex);
        depositPokemon(p);
    }
    
    private void depositPokemon(Pokemon p) {
        int boxIndex = viewBoxList();
        if (addToBox(boxIndex, p)) {
            UserInput.printALine(keyInput, p.getName() + " was placed in " + 
                    boxNames[boxIndex] + ".");
        } else {
            int choice = UserInput.makeMenu(keyInput,
                    boxNames[boxIndex] + " is full. Choose another box?", 
                    UserInput.CHOICES);
            if (choice == UserInput.YES) {
                depositPokemon(p);
            }
        }
    }
    
    /**
     * Get Pokemon from storage into the party. If there is no space in the
     * party, the action cannot be performed.
     * 
     * TODO name v nickname
     */
    public void getPokemon() {
        int boxIndex = viewBoxList();
        int pkmnIndex = viewBoxContents(boxIndex);
        if (GameManager.getPlayer().addToParty(storage[boxIndex][pkmnIndex])) {
            UserInput.printALine(keyInput, storage[boxIndex][pkmnIndex].getName() + 
                    " was added to " + GameManager.getPlayer().getName() + "'s party.");
        }
    }
    
    /**
     * Move Pokemon freely between the party and the storage system.
     */
    public void switchPokemon() {
        
    }
    
    /**
     * View the items held by Pokemon in storage and in the party, and move
     * these freely. No Pokemon can be moved.
     */
    public void moveItems() {
        
    }
    
    /**
     * Print a menu of names of all boxes in the storage system.
     * @return the index of the selected box
     */
    private int viewBoxList() {
        return UserInput.makeMenu(keyInput, TITLE, 
                UserInput.convertToMakeMenuArray(boxNames));
    }
    
    /**
     * Print a menu of all Pokémon within the selected box.
     * @param boxIndex the index of the box to display
     * @return the index of the selected Pokémon
     */
    private int viewBoxContents(int boxIndex) {
        String[] pkmn = new String[30];
        for (int i = 0; i < pkmn.length; i++) {
            pkmn[i] = storage[boxIndex][i].toString();
        }
        return UserInput.makeMenu(keyInput, TITLE,
                UserInput.convertToMakeMenuArray(pkmn));
    }
    
    /**
     * Checks if there is room in the selected box, and adds the Pokémon to
     * the first available spot.
     * @param boxIndex index of the box to add to
     * @param p the Pokémon to add to the box
     * @return true if added successfully, false if not
     */
    private boolean addToBox(int boxIndex, Pokemon p) {
        for (int i = 0; i < storage[boxIndex].length; i++) {
            if (storage[boxIndex][i] == null) {
                storage[boxIndex][i] = p;
                return true;
            }
        }
        return false;
    }
}
