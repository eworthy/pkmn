package item;

import java.util.Random;

import basicFunctions.UserInput;
import battle.Battle;
import pokemon.Pokemon;
import character.Player;
import ui.GameManager;

public abstract class PokeBall extends Item implements Purchasable {

    private static Random generator = new Random();
    private double catchRate;
    private int price;

    private final String[] CHOICES = {"Use", "Give", "Back"};

    /**
     * @param name
     * @param description
     */
    public PokeBall(String name, String description, double catchRate, int price) {
        super(name, description);
        this.catchRate = catchRate;
        this.price = price;
    }

    /**
     *
     * @return
     */
    public String[] getChoices() {
        return CHOICES;
    }

    public boolean isPersistant() {
        return false;
    }

    public double getCatchRate() {
        return catchRate;
    }

    public void setCatchRate(double catchRate) {
        this.catchRate = catchRate;
    }

    public boolean catchPokemon(Pokemon p, Player user) {
        boolean successful = false;
        int catchValue = calculateCatchValue(catchRate, p.getCurrentHP(), p.getMaxHP(), p.getConditionModifier());
        if (catchValue == 255) {
            successful = true;
        } else {
            int catchChance = calculateCatch(catchValue);
            for (int i = 0; i < 3; i++) {
                if (generator.nextInt(20001) < catchChance) {
                    successful = true;
                    p.setTrainer(user);
                    user.addPokemon(p);
                }
            }
        }
        return successful;
    }

    protected int calculateCatchValue(double catchRate, int currentHP, int maxHP, double conditionModifier) {
        return (int) (((3 * maxHP - 2 * currentHP) * (catchRate)) / ((3 * maxHP) * conditionModifier));
    }

    protected int calculateCatch(int catchValue) {
        return (int) (1048560 / (Math.sqrt(Math.sqrt(16711680 / catchValue))));
    }

    public String toShopString() {
        return name + ": " + price;
    }

    public int getPrice() {
        return price;
    }

    public void doBehavior(int makeMenu) {
        switch (makeMenu) {
            case 0: //use it
                if (GameManager.inBattle() == true) {
                    Battle b = GameManager.getBattle();
                    Pokemon p = null;
                    switch (b.getNumOpponents()) {
                        case 2:
                        case 3:
                            p = b.getOpponent(UserInput.makeMenu(UserInput.keyInput, "Choose Target:", b.getOpponentsList()));
                            break;
                        default:
                            p = b.getOpponent();
                            break;
                    }
                    catchPokemon(p, GameManager.getPlayer());
                } else {
                    UserInput.printALine(UserInput.keyInput, "You can't use that right now!");
                }
                break;
            case 1:
                super.give(this);
                break;
            case 2:
                // do nothing because this is just back
                break;
        }
    }
}
