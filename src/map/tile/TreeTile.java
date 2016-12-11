package map.tile;

import basicFunctions.UserInput;
import item.Item;
import ui.GameManager;

/**
 *
 * @author ellen
 */
public class TreeTile extends Tile {
    private final Item[] items;
    
    public TreeTile(Item[] items) {
        super(items); //its not really occupied by items though, but by a tree.... todo
        this.items = items;
    }
    
    @Override
    public void interact() {
        UserInput.printALine(UserInput.keyInput, 
                "It's a large tree! Perhaps something will fall if it's hit?");
        if (GameManager.getPlayer().hasMove("HEADBUTT")) {
            hitTree();
        }
    }

    private void hitTree() {
        if (UserInput.yesNoMenu(UserInput.keyInput, "Use HEADBUTT?")) {
            UserInput.printALine(UserInput.keyInput,
                    "(Find which pkmn) used HEADBUTT!");
            int result = GameManager.generator.nextInt(10);
            if (result == 0) {
                GameManager.receiveItem(items[GameManager.generator.nextInt(items.length)]);
            } else if (result == 1) {
                //fight!
                System.out.println("FIGHT! to do");
            }
        }
    }
}
