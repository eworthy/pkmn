/**
 *
 */
package location;

import basicFunctions.UserInput;
import item.Item;
import item.Purchasable;
import java.util.Scanner;
import ui.GameManager;

/**
 * @author ellen
 *
 */
public class Shop extends Location {

    private final static String NAME = "SHOP";
    private final static String DESCRIPTION = "A place to purchase items.";
    private final static String[] OPTIONS = {"Shop",
        "Leave"};
    private final Purchasable[] stock;

    /**
     * Constructs a shop with default name and description,
     * @param stock list of items that can be purchased in the shop
     */
    public Shop(Purchasable[] stock) {
        super(NAME, DESCRIPTION, OPTIONS);
        this.stock = stock;
    }

    /**
     * Constructs a shop with the following information
     * @param name name of the shop
     * @param description description of the shop
     * @param options actions the player can take in this shop
     * @param stock list of items that can be purchased in the shop
     */
    public Shop(String name, String description, String[] options, Purchasable[] stock) {
        super(name, description, options);
        this.stock = stock;
    }

    /**
     * Get the list of items available for purchase in the shop 
     * @return list of names and descriptions and prices of all items available
     * for purchase in the shop.
     */
    public String[] getStocklist() {
        String[] s = new String[stock.length];
        for (int i = 0; i < s.length; i++) {
            s[i] = stock[i].toShopString();
        }
        return s;
    }

    /**
     * Prompts the user to enter how many of the selected item they wish to 
     * purchase; adds the appropriate number of the item to the player's
     * inventory and subtracts the cost from their money.
     * @param itemIndex the index in stock of the item to be purchased
     */
    public void shop(int itemIndex) {
        UserInput.printALine(UserInput.keyInput, stock[itemIndex].toString());
        int quantity = UserInput.getInt(UserInput.keyInput, "How many? ");
        int totalPrice = quantity * stock[itemIndex].getPrice();
        if (UserInput.makeMenu(UserInput.keyInput, "Purchase items? Price: " + totalPrice, UserInput.CHOICES)
                == UserInput.YES) {
            GameManager.receiveItem((Item) stock[itemIndex], quantity);
            GameManager.getPlayer().setMoney(-1 * totalPrice);
        } else if (UserInput.makeMenu(UserInput.keyInput, "New quantity?", UserInput.CHOICES)
                == UserInput.YES) {
            shop(itemIndex);
        }
    }

    @Override
    public void doBehavior(Scanner keyInput) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
