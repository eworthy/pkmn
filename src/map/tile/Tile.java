package map.tile;

import basicFunctions.Interactive;
import java.io.Serializable;
import map.GridMap;
import map.enums.Direction;
import ui.GameEnvironment;

/**
 *
 * @author ellen
 */
public abstract class Tile implements Serializable, Interactive {
    private boolean isOccupied;
    private Object occupant;
    private boolean exit = false;
    private Tile next;
    private Direction exitDirection;

    public Tile(Object occupant) {
        this.occupant = occupant;
        if (occupant != null) {
            isOccupied = true;
        }
    }
    
    public Tile(Object occupant, Tile next) {
        this(occupant);
        this.next = next;
        exit = true;
    }
    
    public Tile(Object occupant, String roomName, int row, int col, String direction) {
        this(occupant);
        exitDirection = Direction.valueOf(direction);
        /** to do - make sure these are made before i try to get them! */
        GridMap room = GameEnvironment.getRoom(roomName);
        if (room != null) {
            next = room.getTileAt(row, col);
            Direction nextDir = null;
            switch (exitDirection) {
                case NORTH:
                    nextDir = Direction.SOUTH;
                    break;
                case SOUTH:
                    nextDir = Direction.NORTH;
                    break;
                case EAST:
                    nextDir = Direction.WEST;
                    break;
                case WEST:
                    nextDir = Direction.EAST;
                    break;
            }
            room.getTileAt(row, col).setNext(this, nextDir);
        }
        exit = true;
    }
    
    public boolean isOccupied() {
        return isOccupied;
    }
    
    public void setOccupied(boolean b) {
        isOccupied = b;
    }
    
    public Object getOccupant() {
        return occupant;
    }
    
    public void setOccupant(Object o) {
        occupant = o;
        setOccupied(true);
    }

    public boolean isExit() {
        return exit;
    }

    /**
     * The tile to transfer the player to in the next room
     * consider JSON coding of "room(row, col)" to set it
     * @return 
     */
    public Tile getNext() {
        return next;
    }

    public void setNext(Tile next, Direction exitDirection) {
        this.next = next;
        this.exitDirection = exitDirection;
    }

    public Direction getExitDirection() {
        return exitDirection;
    }
}
