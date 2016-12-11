package move;

import move.enums.MoveEffect;
import basicFunctions.UserInput;
import move.enums.Category;
import move.enums.CritHitRatio;
import pokemon.Ability;
import pokemon.PkmnType;
import pokemon.Pokemon;
import pokemon.PokemonType;
import ui.GameManager;

/**
 * TODO should moves actually be concrete classes? i don't think so. bc should
 * we have "A QuickAttack" ? no counter point: some have special behaviors that
 * need to be defined
 *
 * @author ellen
 *
 */
public abstract class Move {

    private String name;
    private String description;
    /**
     * Phrase displayed when move is used (aka "Pidgeotto flew up high!")
     */
    private String flavorText;
    private Category category;
    private MoveEffect effect;
    private PkmnType type;
    private int pp;
    private int power;
    private int accuracy;

    private int currentPP;

    public Move(String name, String description, String flavorText,
            Category category, PkmnType type, int pp, MoveEffect effect) {
        this.name = name;
        this.description = description;
        this.flavorText = flavorText;
        this.category = category;
        this.type = type;
        this.pp = pp;
        this.effect = effect;
        power = 0;
        accuracy = 0;
        currentPP = pp;
    }

    public Move(String name, String description, String flavorText,
            Category category, PkmnType type, int pp, MoveEffect effect,
            int power, int accuracy) {
        this(name, description, flavorText, category, type, pp, effect);
        this.power = power;
        this.accuracy = accuracy;
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Move other = (Move) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return name + " [" + type + "]: " + description + "\n"
                + getCategory() + " Power: " + power + "  Accuracy: " + accuracy
                + "\nPP: " + getCurrentPP() + "/" + pp;
    }

    public int getCurrentPP() {
        return currentPP;
    }
    
    public int getPP() {
        return pp;
    }

    public void resetPP() {
        currentPP = pp;
    }

    public void setPP(int mod) {
        currentPP += mod;
        if (currentPP < 0) {
            currentPP = 0;
        }
        if (currentPP > pp) {
            currentPP = pp;
        }
    }

    public void setPP() {
        setPP(-1);
    }

    private String getCategory() {
        switch (category) {
            case PHYSICAL:
                return "P";
            case SPECIAL:
                return "M";
            case OTHER:
                return "O";
            default:
                return "";
        }
    }

    public String getName() {
        return name;
    }

    public String getFlavorText() {
        return flavorText;
    }

    public MoveEffect getEffect() {
        return effect;
    }

    /**
     * Precondition: the move has already hit (hit chance calculation elsewhere)
     *
     * @param user
     * @param target
     * @param isCritHit
     * @return
     */
    public int perform(Pokemon user, Pokemon target, CritHitRatio critHitRatio) {
        UserInput.printALine(UserInput.keyInput, user.getName() + getFlavorText());
        double critHitMultiplier = 1;
        if (critHit(critHitRatio)) {
            critHitMultiplier = 1.5;
            if (user.getAbility() == Ability.SNIPER) {
                critHitMultiplier = 2.25;
            }
        }
        switch (category) {
            case PHYSICAL: //uses Attack & Defense
                return (int) ((((((2 * (user.getLevel() / 5) + 2) * user.getAttack() * (this.power / target.getDefense())) / 50) + 2)
                        * sameTypeAttackBonus(user) * getTypeResistance(target) * ((GameManager.generator.nextInt(15) + 86) / 100))
                        * critHitMultiplier);
            case SPECIAL: //uses SP Attack & SP Defense
                return (int) ((((((2 * (user.getLevel() / 5) + 2) * user.getSpAttack() * (this.power / target.getSpDefense())) / 50) + 2)
                        * sameTypeAttackBonus(user) * getTypeResistance(target) * ((GameManager.generator.nextInt(15) + 86) / 100))
                        * critHitMultiplier);
            case OTHER:
                return performOtherMove(user, target, critHitRatio);
            default:
                return 0;
        }
    }

    /**
     * Allows for irregular behavior of "Other" type moves without requiring
     * overrides of non-abstract methods
     *
     * @param user
     * @param target
     * @param critHitRatio
     * @return
     */
    abstract int performOtherMove(Pokemon user, Pokemon target, CritHitRatio critHitRatio);

    public int perform(Pokemon user, Pokemon target) {
        return perform(user, target, CritHitRatio.LEVEL1);
    }

    private boolean critHit(CritHitRatio critHitRatio) {
        int critHitValue = 0;
        switch (critHitRatio) {
            case LEVEL1:
                critHitValue = 63;
            case LEVEL2:
                critHitValue = 125;
            case LEVEL3:
                critHitValue = 250;
            case LEVEL4:
                critHitValue = 330;
            case LEVEL5:
                critHitValue = 450;
        }
        return GameManager.generator.nextInt(10001) <= critHitValue;
    }

    public int getTypeResistance(Pokemon target) {
        int resistance = 1;
        for (int i = 0; i < target.getTypes().length; i++) {
            resistance *= PokemonType.getResistanceValue(this.type, target.getTypes()[i]);
        }
        return resistance;
    }

    private double sameTypeAttackBonus(Pokemon user) {
        for (PkmnType t : user.getTypes()) {
            if (t.equals(type)) {
                return 1.5;
            }
        }
        return 1;
    }

    public abstract boolean hasTarget();

    public abstract boolean canUseOnSelf();

    public abstract boolean isEnvironmental();

    public int getPower() {
        return power;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public abstract boolean goesFirst();
    
    public void setRemainingPP(int pp) {
        this.pp = pp;
    }
}
