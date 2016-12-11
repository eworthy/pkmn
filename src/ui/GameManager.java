package ui;

import Exceptions.NoSavedGameException;
import java.util.Random;
import java.util.Scanner;

import basicFunctions.UserInput;
import character.GameCharacter;
import data.JSONConnection;
import item.Item;
import item.Pokedex;
import battle.Battle;
import pokemon.Pokemon;
import character.Player;
import item.concreteItems.genericItems.*;
import item.concreteItems.medicines.*;
import item.concreteItems.pokeballs.*;
import java.util.HashMap;
import ui.enums.GameState;

public class GameManager {
    private static final String saveFile = "/Users/ellen/My Documents/NetBeansProjects/pkmnSave.txt";

    private static final boolean TESTING = true;
    private static final String[] PLACEHOLDER_SPRITE_FILES = {};

    private static final String[] GAME_OPTIONS = {"Load Saved Game", "New Game", "Exit"};

    private static Pokemon[] STARTER_POKEMON;

    private static String[] STARTER_POKEMON_NAMES;
    private static final int GRASS_STARTER = 0;
    private static final int FIRE_STARTER = 3;
    private static final int WATER_STARTER = 6;

    private static final String[] GAME_CHOICES = {"Pokedex",
        "Pokémon",
        "Inventory",
        "Save Game",
        "Walk", //location.getChoice;
        "Exit"};

    private static final int EXIT = GAME_CHOICES.length;

    private static Player player;
    private static String[] partyPkmnList;
    private static int steps = 0;

    public static Pokedex pokedex;
    public static HashMap<String, Item> itemsDex;

    private static GameEnvironment gameEnvironment;
    private static GameState gameState;
    private static GameCharacter[] characters;
    private static GameCharacter conversationCharacter;
    private static Battle battle;

    static int choice;

    public static Random generator;

    public static void main(String[] args) {
        System.out.println("Loading...");
        setUp();
        if (TESTING) {
            GameBoardGUIGridBag g = new GameBoardGUIGridBag();
        }
        choice = UserInput.makeMenu(UserInput.keyInput, "Pokémon: A Text-Based Adventure", GAME_OPTIONS);
        switch (choice) {
            case 1:
                loadGame(UserInput.keyInput);
                break;
            case 2:
                startGame(UserInput.keyInput);
                break;
            case 3:
                if (UserInput.makeMenu(UserInput.keyInput, "Save before quitting?", UserInput.CHOICES) == UserInput.YES) {
                    saveGame(saveFile);
                }
                System.exit(0);
                break;
        }
    }

    /**
     * Set up all needed parts for a new game
     */
    private static void setUp() {
        pokedex = Pokedex.getInstance();
        itemsDex = new HashMap<>();
        initAllItems();
        partyPkmnList = new String[6];
        gameEnvironment = new GameEnvironment("/Users/ellen/My Documents/NetBeansProjects/Pkmn/mapGame1.json");
        JSONConnection.readSpeciesData("/Users/ellen/My Documents/NetBeansProjects/Pkmn/speciesGame1.json");
        characters = JSONConnection.readCharacterData("/Users/ellen/My Documents/NetBeansProjects/Pkmn/testCharacter.json");
        gameState = GameState.MAP;
        
        STARTER_POKEMON = new Pokemon[3];
        STARTER_POKEMON[0] = new Pokemon(FIRE_STARTER, 15);
        STARTER_POKEMON[1] = new Pokemon(WATER_STARTER, 5);
        STARTER_POKEMON[2] = new Pokemon(GRASS_STARTER, 5);

        STARTER_POKEMON_NAMES = new String[3];
        STARTER_POKEMON_NAMES[0] = STARTER_POKEMON[0].getName();
        STARTER_POKEMON_NAMES[1] = STARTER_POKEMON[1].getName();
        STARTER_POKEMON_NAMES[2] = STARTER_POKEMON[2].getName();
    }

    /*----------------------------------------- START MENU FUNCTIONS -----------------------------------------------*/
    public static void loadGame(Scanner keyInput) {
        try {
            JSONConnection.loadGame(saveFile);
            updatePartyPkmnList();
            System.out.println("Welcome back, " + player.getName() + "!");
            mainGameMenu(keyInput);
        } catch (NoSavedGameException e) {
            System.out.println(e.getMessage());
            startGame(keyInput);
        }
    }

    public static void startGame(Scanner keyInput) {
        gameState = GameState.CONVERSATION;
        conversationCharacter = characters[0];

        //pretty much all of this needs to be the professor character's dialogue.
        // consider this being partly on a phone call, to still set up name etc?
        UserInput.printALine(keyInput, "Hello!");
        UserInput.printALine(keyInput, "I'm Professor Ebony, an up-and-coming Pokémon researcher!");
        UserInput.printALine(keyInput, "I've come here to the REGION region to study the\n"
                + "Pokémon that live here!");

        String name = UserInput.getString(keyInput, "But enough about me! What's your name?\n"
                + "Enter your name: ");
        while (name.length() == 0) {
            name = UserInput.getString(keyInput, "Don't be shy! What's your name? ");
        }
        makeNewPlayer(name);

        UserInput.printALine(keyInput, "I see! It's very nice to meet you, " + player.getName() + "!");
        UserInput.printDots(keyInput);
        UserInput.printALine(keyInput, "Hmm? What's that?");
        UserInput.printDots(keyInput);
        System.out.println("Oh! You must be wondering why I called you here!");

        choice = UserInput.makeMenu(keyInput, UserInput.CHOICES);
        while (choice != UserInput.YES) {
            UserInput.printALine(keyInput, "Oh... I see.");
            choice = UserInput.makeMenu(keyInput, "Would you like to find out anyway?", UserInput.CHOICES);
        }

        UserInput.printALine(keyInput, "Excellent! Right this way, please!");
        UserInput.printALine(keyInput, "*You followed Professor Ebony.*");
        UserInput.printALine(keyInput, "In order to study the Pokémon living here in the REGION region,\n"
                + "I need to collect data about all of the species!");
        UserInput.printDots(keyInput);
        UserInput.printALine(keyInput, "I'd love to go myself, and see them all in person...");
        UserInput.printALine(keyInput, "*Professor Ebony is staring wistfully into the distance.*");
        UserInput.printDots(keyInput);
        UserInput.printALine(keyInput, "*You're not sure the professor remembers you're there.*");

        choice = UserInput.makeMenu(keyInput, "Get the professor's attention?", UserInput.CHOICES);
        while (choice != UserInput.YES) {
            UserInput.printALine(keyInput, "*The professor is still lost in space...*");
            choice = UserInput.makeMenu(keyInput, "Try again?", UserInput.CHOICES);
        }

        UserInput.printALine(keyInput, "Huh?! Oh! I must have gotten a little distracted there...");
        UserInput.printALine(keyInput, "*Professor Ebony strokes the back of her neck sheepishly.*");
        UserInput.printALine(keyInput, "As I was saying, I'd do it myself if I could, but I'm so busy\n"
                + "in the lab, I'm afraid I just don't have time!");
        UserInput.printALine(keyInput, "What do you say? Would you be able to do it for me?");

        choice = UserInput.makeMenu(keyInput, "*The professor is looking at you hopefully.*", UserInput.CHOICES);
        while (choice != UserInput.YES) {
            UserInput.printALine(keyInput, "Well, that's just too bad! I don't know who else to ask...");
            choice = UserInput.makeMenu(keyInput, "If you change your mind...", UserInput.CHOICES);
        }

        UserInput.printALine(keyInput, "Oh, that's wonderful! Thank you so much!");
        UserInput.printALine(keyInput, "You can use this to store data about all the Pokémon you find.");

        choice = UserInput.makeMenu(keyInput, "*The professor is holding something out to you.*\n"
                + "*Will you take it?*", UserInput.CHOICES);
        while (choice != UserInput.YES) {
            UserInput.printALine(keyInput, "*The professor seems insistent.*");
            choice = UserInput.makeMenu(keyInput, "*Take the item?*", UserInput.CHOICES);
        }

        UserInput.printALine(keyInput, "Received the " + pokedex.getName() + "!");
        UserInput.printALine(keyInput, "And of course, I wouldn't send you into the wilds unprotected!");
        UserInput.printALine(keyInput, "*The professor looks around for something.*");
        UserInput.printALine(keyInput, "Ah, here we go! *Professor Ebony holds up three Pokeballs.*");
        UserInput.printALine(keyInput, "Someone left these with me, and I'm afraid I can't take care of all of them.\n"
                + "You can take one with you on your journey!");

        int confirm = 0;
        while (confirm != UserInput.YES) {
            choice = UserInput.makeMenu(keyInput, "*Which Pokémon will you take?*", STARTER_POKEMON_NAMES);
            confirm = confirmPokemon(keyInput, choice);
        }
        UserInput.printALine(keyInput, getPokemon(STARTER_POKEMON[choice - 1]));

        UserInput.printALine(keyInput, "And there's one last thing, before you go.");
        UserInput.printALine(keyInput, "You'll need these on your journey!");

        UserInput.printALine(keyInput, receiveItem(new BasicPokeBall(), 5));

        UserInput.printALine(keyInput, "Now you're all set to start your journey!");
        UserInput.printALine(keyInput, "I must get back to my research now, I really can't thank you enough!\nGoodbye!");

        mainGameMenu(keyInput);
    }

    /**
     * Save game data to files.
     * @param saveFile
     */
    public static void saveGame(String saveFile) {
        JSONConnection.saveGame(saveFile);
    }

    /*------------------------------------------ MAIN MENU FUNCTIONS -----------------------------------------------*/
    /**
     * No argument for currentOptions means default GAME_CHOICES
     *
     * @param keyInput
     */
    private static void mainGameMenu(Scanner keyInput) {
        while (choice != EXIT) {
            choice = UserInput.makeMenu(keyInput, GAME_CHOICES);
            switch (choice) {
                case 1:
                    openPokedex(keyInput);
                    break;
                case 2:
                    viewParty(keyInput);
                    break;
                case 3:
                    viewInventory(keyInput);
                    break;
                case 4:
                    saveGame(saveFile);
                    break;
                case 5:
                //something like : location.getBehavior();
                default:
                //this is exit
            }
        }
        if (UserInput.makeMenu(keyInput, "Save before quitting?", UserInput.CHOICES) == UserInput.YES) {
            saveGame(saveFile);
        }
        System.exit(0);
    }

    private static void viewInventory(Scanner keyInput) {
        String[] inventoryChoices = UserInput.convertToMakeMenuArray(player.getInventoryTitles());
        choice = UserInput.makeMenu(keyInput, "Select a Bag:", inventoryChoices);
        while (choice != inventoryChoices.length) {
            viewInventorySubunit(keyInput, choice - 1);
            choice = UserInput.makeMenu(keyInput, "Select a Bag:", inventoryChoices);
        }
        mainGameMenu(keyInput);
    }

    private static void viewInventorySubunit(Scanner keyInput, int inventoryIndex) {
        String[] items = UserInput.convertToMakeMenuArray(player.getInventoryList(inventoryIndex));
        choice = UserInput.makeMenu(keyInput, "Select an Item:", items);
        while (choice != items.length) {
            Item currentItem = player.getInventoryItem(inventoryIndex, choice - 1);
            System.out.println(currentItem.toString());
            currentItem.doBehavior(UserInput.makeMenu(keyInput, currentItem.getChoices()));
            choice = UserInput.makeMenu(keyInput, "Select an Item:", items);
        }
    }

    public static void viewBattleInventory(Scanner keyInput) {
        String[] inventoryChoices = new String[5]; //TODO fix implementation to match guidelines
        inventoryChoices[0] = player.getInventoryTitles()[Player.BATTLE_ITEMS];
        inventoryChoices[1] = player.getInventoryTitles()[Player.BERRY_POUCH];
        inventoryChoices[1] = player.getInventoryTitles()[Player.MEDICINE];
        inventoryChoices[1] = player.getInventoryTitles()[Player.POKEBALLS];
        inventoryChoices[inventoryChoices.length - 1] = "Back";

        choice = battleInventoryChoice(keyInput, inventoryChoices);
        while (choice != inventoryChoices.length) {
            viewInventorySubunit(keyInput, choice);
            choice = battleInventoryChoice(keyInput, inventoryChoices);
        }
    }

    /**
     * @param keyInput
     * @param inventoryChoices
     * @return
     */
    private static int battleInventoryChoice(Scanner keyInput, String[] inventoryChoices) {
        choice = UserInput.makeMenu(keyInput, "Select a Bag:", inventoryChoices);
        switch (choice) {
            case 1:
                choice = Player.BATTLE_ITEMS;
                break;
            case 2:
                choice = Player.BERRY_POUCH;
                break;
            case 3:
                choice = Player.MEDICINE;
                break;
            case 4:
                choice = Player.POKEBALLS;
                break;
            case 5:
                choice--; //choice is typically 1 higher than array index, so we need to decrement it to get exit
                break;
        }
        return choice;
    }

    /**
     * @param keyInput
     */
    private static void viewParty(Scanner keyInput) {
        String[] pkmnChoices = UserInput.convertToMakeMenuArray(partyPkmnList);
        choice = UserInput.makeMenu(keyInput, "Select a Pokemon:", pkmnChoices);
        while (choice != pkmnChoices.length) {
            UserInput.printALine(keyInput, viewPkmn(choice - 1));
            choice = UserInput.makeMenu(keyInput, "Select a Pokemon:", pkmnChoices);
        }
        mainGameMenu(keyInput);
    }

    private static String viewPkmn(int choice) {
        if (partyPkmnList[choice].length() == 0) {
            return "No pokemon available";
        }
        return player.getPartyMember(choice).pkmnSummary();
    }

    //TODO this should probably be in trainer!!!
    public static String[] getPartyPokemonList() {
        if (inBattle()) {
            for (int i = 0; i < partyPkmnList.length; i++) {
                if (player.getPartyMember(i) != null) {
                    partyPkmnList[i] = player.getPartyMember(i).toString();
                }
            }
        }
        return partyPkmnList;
    }

    private static void openPokedex(Scanner keyInput) {
        UserInput.printALine(keyInput,
                pokedex.getPokemonData(UserInput.getInt(keyInput, "Enter the pokedex no: ")));

    }

    private static int confirmPokemon(Scanner keyInput, int choice) {
        System.out.println(STARTER_POKEMON[choice - 1].getDescription());
        return confirmChoice(keyInput, "Choose " + STARTER_POKEMON_NAMES[choice - 1] + "?");
    }

    private static int confirmChoice(Scanner keyInput, String message) {
        return UserInput.makeMenu(keyInput, message, UserInput.CHOICES);
    }

    private static void makeNewPlayer(String name) {
        player = Player.getInstance(name, PLACEHOLDER_SPRITE_FILES);

    }

    public static Player getPlayer() {
        return player;
    }

    public static String receiveItem(Item i) {
        try {
            player.addToInventory(i);
        } catch (IllegalArgumentException e) {
            return "You're not supposed to have that...";
        }
        return "Received the " + i.getName() + "!";
    }

    public static String receiveItem(Item i, int numReceived) {
        for (int num = 0; num < numReceived; num++) {
            receiveItem(i);
        }
        return "Received the " + i.getName() + "s !";
    }

    private static String getPokemon(Pokemon pokemon) {
        String s = "";
        nicknamePkmn(pokemon);
        String pkmnName = pokemon.getName();
        if (pokemon.getNickname() != null) {
            pkmnName = pokemon.getNickname();
        }
        player.addPokemon(pokemon);
        if (player.addToParty(pokemon)) {
            s += pkmnName + " joined " + player.getName() + "'s party!" + "\n";
        } else {
            s += pkmnName + " was sent to storage." + "\n";
        }
        updatePartyPkmnList();
        if (pokedex.foundNewPokemon(pokemon)) {
            s += pokemon.getName() + "'s data was added to the Pokedex!\n";
        }
        return s;
    }

    private static String[] updatePartyPkmnList() {
        for (int i = 0; i < partyPkmnList.length; i++) {
            if (player.getPartyMember(i) != null) {
                partyPkmnList[i] = player.getPartyMember(i).toString();
            } else {
                partyPkmnList[i] = "";
            }
        }
        return partyPkmnList;
    }

    private static boolean nicknamePkmn(Pokemon pokemon) {
        Scanner keyInput = new Scanner(System.in);
        choice = UserInput.makeMenu(keyInput, "Give a nickname to the Pokemon?", UserInput.CHOICES);
        if (choice == UserInput.YES) {
            String s = UserInput.getString(keyInput, "What will you call it? ");
            while (s.length() == 0) {
                s = UserInput.getString(keyInput, "Enter a nickname: ");
            }
            pokemon.setNickname(s);
            return true;
        }
        return false;
    }

    public static boolean newCalculations() {
        // TODO allows user to decide between gen 1 - 5 or gen 6 + calculations
        return false;
    }

    public static boolean inBattle() {
        return gameState == GameState.BATTLE;
    }

    public static Battle getBattle() {
        // TODO when we enter a battle we will make a new one and that's what is returned here
        return null;
    }

    public static int getSteps() {
        return steps;
    }

    public static void incrementSteps() {
        steps++;
    }

    public static void setPlayer(Player p) {
        player = p;
        player.setUpInventory();
    }

    public static void setPokedex(Pokedex p) {
        pokedex = p;
    }

    public static GameState getGameState() {
        return gameState;
    }

    public static GameCharacter getConversationPartner() {
        return conversationCharacter;
    }

    private static void initAllItems() {
        Item i;
        i = new DestinyKnot();
        i = new Everstone();
        i = new BasicPotion();
        i = new BerryJuice();
        i = new Calcium();
        i = new Carbos();
        i = new CleverWing();
        i = new EnergyPowder();
        i = new EnergyRoot();
        i = new FreshWater();
        i = new FullHeal();
        i = new FullRestore();
        i = new GeniusWing();
        i = new HealPowder();
        i = new HealthWing();
        i = new HpUp();
        i = new HyperPotion();
        i = new Iron();
        i = new Lemonade();
        i = new MaxPotion();
        i = new MaxRevive();
        i = new MoomooMilk();
        i = new MuscleWing();
        i = new OldGateau();
        i = new Protein();
        i = new RareCandy();
        i = new ResistWing();
        i = new RevivalHerb();
        i = new Revive();
        i = new SodaPop();
        i = new SpeedWing();
        i = new SuperPotion();
        i = new Zinc();
        i = new BasicPokeBall();
        i = new DiveBall();
        i = new DuskBall();
        i = new FogBall();
        i = new GreatBall();
        i = new HealBall();
        i = new LuxuryBall();
        i = new MasterBall();
        i = new NestBall();
        i = new NetBall();
        i = new QuickBall();
        i = new RepeatBall();
        i = new TimerBall();
        i = new UltraBall();
    }
}
