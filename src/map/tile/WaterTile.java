package map.tile;

import map.GridMap;
import ui.GameManager;

/**
 * 
 * @author ellen
 */
public class WaterTile extends Tile implements Occupiable {
   
    public WaterTile(Object occupant) {
        super(occupant);
    }
    
    public WaterTile(Object occupant, String roomName, int row, int col, String direction) {
        super(occupant, roomName, row, col, direction);
    }
    
    @Override
    public void interact() {
        if (GameManager.getPlayer().hasMove("SURF")) {
            //TODO
        } else {
            System.out.println("It's deep water! Maybe a Pokemon could swim on it?");
        }
    }
}
