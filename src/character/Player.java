package character;

import java.util.ArrayList;

import item.BattleItem;
import item.Berry;
import item.GenericItem;
import item.Item;
import item.KeyItem;
import item.Machine;
import item.Medicine;
import item.PokeBall;
import java.util.UUID;
import map.tile.Tile;
import move.Move;
import pokemon.Pokemon;

public class Player extends Trainer{
    private static final String[] DIALOGUE = {};
    
    /**
     * Player singleton instance
     */
    private static Player player;
    private final UUID playerId;

    private static final ArrayList<? extends Item>[] INVENTORY =
            (ArrayList<? extends Item>[])new ArrayList[7];
    private static ArrayList<GenericItem> genericItems;
    private static ArrayList<Medicine> medicinePouch;
    private static ArrayList<PokeBall> pokeballs;
    private static ArrayList<Berry> berryPouch;
    private static ArrayList<BattleItem> battleItems;
    private static ArrayList<Machine> TMsAndHMs;
    private static ArrayList<KeyItem> keyItems;

    public static final transient int GENERAL_ITEMS = 0;
    public static final transient int MEDICINE = 1;
    public static final transient int POKEBALLS = 2;
    public static final transient int BERRY_POUCH = 3;
    public static final transient int BATTLE_ITEMS = 4;
    public static final transient int TMS_AND_HMS = 5;
    public static final transient int KEY_ITEMS = 6;

    private final String[] inventoryTitles = {
        "Items", "Medicine", "Pokeballs", "Berry Pouch", 
        "Battle Items", "TMs and HMs", "Key Items"};

    private static ArrayList<Pokemon> myPkmn;

    private static int money;
    private static Tile tile;

    /**
     * The constructor to make a new player in a new game
     * @param name 
     * TODO allow for updating start location
     */
    private Player(String name, String[] spriteFiles) {
        super(name, DIALOGUE, spriteFiles);
        setUpInventory();
        myPkmn = new ArrayList<>();
        money = 0;
        playerId = UUID.randomUUID();
    }

    /**
     * Returns the singleton instance of Player. If the instance doesn't
     * exist, it will be created.
     * @param name the Player's name
     * @param spriteFiles
     * 
     * @return singleton instance
     */
    public static Player getInstance(String name, String[] spriteFiles) {
        if (player == null) {
            player = new Player(name, spriteFiles);
        }
        return player;
    }
    
    public UUID getPlayerId() {
        return playerId;
    }

    /**
     * Get the player's money
     *
     * @return the amount of money
     */
    public int getMoney() {
        return money;
    }

    /**
     * Sets the player's money
     *
     * @param change the amount to add (negative to subtract)
     */
    public void setMoney(int change) {
        money += change;
    }
    
    public void setLocation(Tile tile) {
        Player.tile = tile;
    }
    
    public Tile getLocation() {
        return tile;
    }
    
    /*------------------------------------------ INVENTORY -----------------------------------------------*/
    public void setUpInventory() {
        genericItems = new ArrayList<>();
        berryPouch = new ArrayList<>();
        pokeballs = new ArrayList<>();
        battleItems = new ArrayList<>();
        medicinePouch = new ArrayList<>();
        TMsAndHMs = new ArrayList<>();
        keyItems = new ArrayList<>();
        
        INVENTORY[GENERAL_ITEMS] = genericItems;
        INVENTORY[BERRY_POUCH] = berryPouch;
        INVENTORY[POKEBALLS] = pokeballs;
        INVENTORY[BATTLE_ITEMS] = battleItems;
        INVENTORY[MEDICINE] = medicinePouch;
        INVENTORY[TMS_AND_HMS] = TMsAndHMs;
        INVENTORY[KEY_ITEMS] = keyItems;
    }

    public void addToInventory(Item i) throws IllegalArgumentException {
        Class<? extends Item> itemClass = i.getClass();
        if (itemClass.getGenericSuperclass().equals(Medicine.class)) {
            medicinePouch.add((Medicine) i);
        } else if (itemClass.getGenericSuperclass().equals(PokeBall.class)) {
            pokeballs.add((PokeBall) i);
        } else if (itemClass.getGenericSuperclass().equals(Berry.class)) {
            berryPouch.add((Berry) i);
        } else if (itemClass.getGenericSuperclass().equals(BattleItem.class)) {
            battleItems.add((BattleItem) i);
        } else if (itemClass.getGenericSuperclass().equals(Machine.class)) {
            TMsAndHMs.add((Machine) i);
        } else if (itemClass.getGenericSuperclass().equals(KeyItem.class)) {
            keyItems.add((KeyItem) i);
        } else if (itemClass.getGenericSuperclass().equals(GenericItem.class)) {
            genericItems.add((GenericItem) i);
        }else {
            throw new IllegalArgumentException("Bad Item");
        }
    }

    public String[] getInventoryTitles() {
        return inventoryTitles;
    }

    public String[] getInventoryList(int i) {
        //TODO: condense duplicates into one listing and show how many you have
        int len = INVENTORY[i].size(); //inventory.get(i).size();
        String[] s = new String[len];
        for (int n = 0; n < len; n++) {
            s[n] = INVENTORY[i].get(n).toString();  //inventory.get(i).get(n).toString();
        }
        return s;
    }
    
    public ArrayList<? extends Item> getSubInventory(int inventoryIndex) {
        return INVENTORY[inventoryIndex];  //inventory.get(inventoryIndex);
    }

    public Item getInventoryItem(int inventoryIndex, int itemIndex) {
        return INVENTORY[inventoryIndex].get(itemIndex); //inventory.get(inventoryIndex).get(itemIndex);
    }
    
    public ArrayList<? extends Item>[] getInventory() {
        return INVENTORY;
    }

    /*------------------------------------------ POKEMON -----------------------------------------------*/
    public ArrayList<Pokemon> addPokemon(Pokemon pokemon) {
        //TODO consider having the attempt to add to party to occur in GameManager
        //this.addToParty(pokemon);
        myPkmn.add(pokemon);
        pokemon.setTrainer(player);
        return myPkmn;
    }

    public String pokemonToString() {
        String s = "";
        for (Pokemon p : myPkmn) {
            s += p.toString() + "\n";
        }
        return s;
    }

    public Pokemon choosePokemon() {
        // TODO Auto-generated method stub
        return null;
    }
    
    public ArrayList<Pokemon> getStoredPokemon() {
        return myPkmn;
    }
    
    public void setStoredPokemon(ArrayList<Pokemon> storedPkmn) {
        myPkmn = storedPkmn;
    }

    public boolean hasMove(String moveName) {
        for (Pokemon p : myPkmn) {
            for (Move m : p.getMoves()) {
                if (m.getName().equals(moveName)) {
                    return true;
                }
            }
        }
        return false;
    }
}
