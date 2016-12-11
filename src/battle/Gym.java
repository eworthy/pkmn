package battle;

import character.GymLeader;
import item.Badge;

public class Gym {

    private final String name;
    private final String description;
    private Badge badge;
    private final GymLeader gymLeader;
    private static final String[] LOCATION_CHOICES = {"Continue",
        "Bag",
        "Pokemon",
        "Back"};

    private final TrainerBattle[] gymBattles;
    private int indexBattlesWon;

    public Gym(String name, String description,
            GymLeader gymLeader, TrainerBattle[] gymBattles) {
        this.name = name;
        this.description = description;
        this.gymLeader = gymLeader;
        this.gymBattles = gymBattles;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Badge getBadge() {
        return badge;
    }

    public GymLeader getGymLeader() {
        return gymLeader;
    }

    public static String[] getLOCATION_CHOICES() {
        return LOCATION_CHOICES;
    }

    public TrainerBattle[] getGymBattles() {
        return gymBattles;
    }
}
