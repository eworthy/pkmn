package location;

import basicFunctions.UserInput;
import java.util.Scanner;
import pokemon.Pokemon;
import ui.GameManager;

/**
 *
 * @author ellen
 */
public class DayCareCentre extends Location {

    private static final String NAME = "DayCare Center";
    private static final String[] LOCATION_CHOICES = {"Drop Off",
        "Pick Up",
        "Access Pokemon Storage System",
        "Back"};

    private static Pokemon[] pkmnInDaycare;

    public DayCareCentre() {
        super(NAME, "TBD", LOCATION_CHOICES);
        pkmnInDaycare = new Pokemon[2];
    }

    @Override
    public void doBehavior(Scanner keyInput) {
        int choice = UserInput.makeMenu(keyInput, NAME, LOCATION_CHOICES);
        while (choice != LOCATION_CHOICES.length - 1) {
            switch (choice) {
                case 0:
                    dropOff(keyInput);
                    break;
                case 1:
                    pickUp(keyInput);
                    break;
                case 2:
                    accessPokemonStorageSystem();
                    break;
            }
        }
    }

    private void dropOff(Scanner keyInput) {
        int pkmnIndex = UserInput.makeMenu(keyInput,
                "Which Pokemon?",
                UserInput.convertToMakeMenuArray(GameManager.getPartyPokemonList()));
        Pokemon p = GameManager.getPlayer().getPartyMember(pkmnIndex);
        boolean added = false;
        for (int i = 0; i < pkmnInDaycare.length; i++) {
            if (pkmnInDaycare[i] == null) {
                pkmnInDaycare[i] = p;
                added = true;
                UserInput.printALine(keyInput, p.getName() + 
                        " was dropped off at the " + NAME + "!");
            }
        }
        if (!added) {
            UserInput.printALine(keyInput, "Sorry, the " + NAME + " is full.");
//            int choice = UserInput.makeMenu(UserInput.keyInput, "Switch Pokemon?", UserInput.CHOICES);
//            while (choice != UserInput.NO) {
//                int pkmnIndex = UserInput.makeMenu(UserInput.keyInput, 
//                            "Which Pokemon?",
//                            UserInput.convertToMakeMenuArray(GameManager.getPartyPokemonList()));
//            }
        }
    }

    private void pickUp(Scanner keyInput) {
        int pkmnIndex;
        String[] s = UserInput.convertToMakeMenuArray(getPokemonInDaycare());
        pkmnIndex = UserInput.makeMenu(keyInput,
                "Which Pokemon?",
                s);
        boolean added = false;
        while (pkmnIndex != s.length - 1) {
            Pokemon p = pkmnInDaycare[pkmnIndex - 1];
            for (int i = 0; i < GameManager.getPlayer().getParty().length; i++) {
                if (GameManager.getPlayer().getParty()[i] == null) {
                    GameManager.getPlayer().getParty()[i] = p;
                    added = true;
                    UserInput.printALine(keyInput, GameManager.getPlayer().getName() + 
                            "picked up " + p.getName() + "!");
                }
            }
        }
        if (!added) {
            UserInput.printALine(keyInput, "Sorry, your party is full.");
        }
    }
    
    private void accessPokemonStorageSystem() {
        
    }

    private String[] getPokemonInDaycare() {
        String[] s = new String[2];
        for (int i = 0; i < pkmnInDaycare.length; i++) {
            s[i] = pkmnInDaycare[i].toString();
        }
        return s;
    }
}
