package map.tile;

import ui.GameManager;

/**
 *
 * @author ellen
 */
public class RockyCliff extends CliffTile implements Climbable {

    @Override
    public void climb() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void interact() {
        if (GameManager.getPlayer().hasMove("CLIMB")) {
            //TODO
        } else {
            System.out.println("It's a rocky cliff! Maybe a Pokemon could climb it?");
        }
    }
}
