package character;

import map.enums.Direction;
import java.io.Serializable;

/**
 *
 * @author ellen
 */
public class GameCharacter implements Serializable {
    private String name;
    private String[] spriteFiles;
    private String[] dialogue;
    private int index;
    /** 
     * We need to know this so we know what we're interacting with,
     * or if we've been seen for a battle, and for drawing sprites
     */
    private Direction facing;
    
    public GameCharacter(String name, String[] spriteFiles) {
        this.name = name;
        this.spriteFiles = spriteFiles;
        index = 0;
    }
    
    public GameCharacter(String name, String[] dialogue, String[] spriteFiles) {
        this(name, spriteFiles);
        this.dialogue = dialogue;
    }
    
    public GameCharacter(String name, String[] dialogue, int index) {
        this(name, dialogue);
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getDialogue() {
        return dialogue;
    }

    public void setDialogue(String[] dialogue) {
        this.dialogue = dialogue;
    }

    /**
     * TODO: Handle other stuff like giving item, options, etc
     * @return 
     */
    public String interact() {
        String s = dialogue[index];
        if (index < dialogue.length - 1) {
            index++;
        }
        return s;
    }

    public Direction getFacing() {
        return facing;
    }

    public void setFacing(Direction facing) {
        this.facing = facing;
    }
}
