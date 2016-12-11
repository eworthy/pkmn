package item;

import basicFunctions.UserInput;
import java.io.Serializable;
import java.util.UUID;
import javax.naming.Context;
import javax.naming.NamingException;
import pokemon.Pokemon;
import ui.GameManager;

public abstract class Item implements Serializable {

    private final UUID itemId;

    protected String name;
    protected String description;
    private static Context context;

    protected Pokemon pokemonHoldingThis;

    /**
     * @param name
     * @param description
     */
    public Item(String name, String description) {
        this.name = name;
        this.description = description;
        itemId = UUID.randomUUID();
        addToItemDex();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Pokemon getPokemonHoldingThis() {
        return pokemonHoldingThis;
    }

    @Override
    public String toString() {
        return name + ": " + description;
    }

    /**
     * Should be overridden as needed for other item types
     *
     * @return
     */
    public abstract String[] getChoices();

    public abstract boolean isPersistant();

    public abstract void doBehavior(int makeMenu);

    public void give(Item item) {
        int pokemon = UserInput.makeMenu(UserInput.keyInput,
                "Which Pokemon?", GameManager.getPartyPokemonList());
        GameManager.getPlayer().getPartyMember(pokemon).setItem(item);
        pokemonHoldingThis = GameManager.getPlayer().getPartyMember(pokemon);
    }

    public void remove(Pokemon p) {
        p.setItem(null);
        pokemonHoldingThis = null;
    }

    /**
     * Get an item back from a serialized item
     *
     * @param itemId the name bound to the item
     * @return the item bound to that name
     * @throws NamingException
     */
    public static Item readItem(String itemId) throws NamingException {
        return (Item) context.lookup(itemId);
    }

    private void addToItemDex() {
        GameManager.itemsDex.putIfAbsent(name, this);
    }
}
