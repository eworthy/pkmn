package map.tile;

/**
 *
 * @author ellen
 */
public class MarshTile extends Tile implements AttackRisk {

    public MarshTile(Object occupant) {
        super(occupant);
    }

    public MarshTile(Object occupant, String roomName, int row, int col, String direction) {
        super(occupant, roomName, row, col, direction);
    }

    @Override
    public void interact() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
