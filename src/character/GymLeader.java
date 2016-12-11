package character;

import item.Badge;

/**
 *
 * @author ellen
 */
public class GymLeader extends Trainer {
    private final Badge badge;
    
    public GymLeader(String name, String[] dialogue, String[] spriteFiles, Badge badge) {
        super(name, dialogue, spriteFiles);
        this.badge = badge;
    }

    public Badge getBadge() {
        return badge;
    }
}
