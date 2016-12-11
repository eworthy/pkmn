package map.tile;

import item.Item;
import ui.GameManager;

/**
 * Generic tile
 * @author ellen
 */
public class PlainTile extends Tile implements Occupiable {
    Item item;

    public PlainTile(Item item) {
        super(item);
        this.item = item;
    }

    public PlainTile(Item item, String roomName, int row, int col, String direction) {
        super(item, roomName, row, col, direction);
        this.item = item;
    }
    
    @Override
    public void interact() {
        if (item != null) {
            GameManager.receiveItem(item);
            item = null;
        }
    }
}
