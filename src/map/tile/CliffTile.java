package map.tile;

import basicFunctions.UserInput;

/**
 *
 * @author ellen
 */
public class CliffTile extends SteepTile {

    public CliffTile() {
    }

    @Override
    public void interact() {
        UserInput.printALine(UserInput.keyInput, "It's much too steep to climb!");
    }
    
}
