package map.tile;

/**
 *
 * @author ellen
 */
public class ForestTile extends Tile implements AttackRisk {

    public ForestTile(Object occupant) {
        super(occupant);
    }

    public ForestTile(Object occupant, String roomName, int row, int col, String direction) {
        super(occupant, roomName, row, col, direction);
    }

    @Override
    public void interact() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
