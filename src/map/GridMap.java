package map;

import character.Player;
import map.enums.Direction;
import map.tile.*;
import ui.GameManager;

/**
 *
 * @author ellen
 */
public class GridMap {

    private final String name;
    //private final int width, length;
    private final Tile[][] map;
    private int currentRow, currentCol;

    /**
     * Make a GridMap from a file
     * @param name
     * @param map 
     */
    public GridMap(String name, Tile[][] map) {
        this.name = name;
        this.map = map;
        //this.width = map[0].length;
        //this.length = map.length;
    }

    public GridMap(String NAME, char[][] charMap) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getName() {
        return name;
    }

    public boolean move(char direction) {
        switch (direction) {
            case 'w':
                return move(-1, 0, Direction.NORTH);
            case 'a':
                return move(0, -1, Direction.WEST);
            case 's':
                return move(1, 0, Direction.SOUTH);
            case 'd':
                return move(0, 1, Direction.EAST);
            default:
                throw new IllegalArgumentException("Invalid direction");
        }
    }

    private boolean move(int rowChange, int colChange, Direction d) {
        Player p = GameManager.getPlayer();
        if (p.getFacing() != d) {
            p.setFacing(d);
            return true;
        } else if (getTile().isExit() && getTile().getExitDirection() == d) {
            // we already know we're facing the right direction
            Tile t = getTile().getNext();
            p.setLocation(t);
            t.setOccupant(p);
            return true;
        } else {
            Tile t = map[currentRow - rowChange][currentCol - colChange];
            if (t instanceof Occupiable && !t.isOccupied()) {
                currentRow = currentRow - rowChange;
                currentCol = currentCol - colChange;
                t.setOccupant(p);
                GameManager.incrementSteps();
                return true;
            }
            return false;
        }
    }

    public Tile getTileAt(int row, int col) {
        return map[row][col];
    }

    private Tile getTile() {
        return map[currentRow][currentCol];
    }
}
