package location;

import java.util.Scanner;

public abstract class Location {

    private String name;
    private String description;
    private String[] locationChoices;

    public Location(String name, String description, String[] locationChoices) {
        this.name = name;
        this.description = description;
        this.locationChoices = locationChoices;
    }

    public String[] getLocationChoices() {
        return locationChoices;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    
    public abstract void doBehavior(Scanner keyInput);
}
