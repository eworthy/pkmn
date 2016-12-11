package item;

import basicFunctions.UserInput;
import pokemon.Pokemon;
import pokemon.Stat;
import ui.GameManager;

public abstract class Medicine extends Item implements Purchasable {

    private Stat stat;
    private int effect;
    private int max;
    private int price;

    /**
     * @param name
     * @param description
     */
    public Medicine(String name, String description, int price, Stat stat, int effect) {
        super(name, description);
        this.price = price;
        this.stat = stat;
        this.effect = effect;
    }

    public Medicine(String name, String description, int price, Stat stat, int effect, int max) {
        this(name, description, price, stat, effect);
        this.max = max;
    }

    public boolean isPersistant() {
        return false;
    }

    public String toShopString() {
        return name + ": " + price;
    }

    public int getPrice() {
        return price;
    }

    public void useMedicine(Pokemon p) {
        //these are permanent stat changes, so uses set<Stat> rather than setTemp<Stat>
        switch (stat) {
            case ATTACK:
                if (p.getAttackEV() < 100) {
                    p.setAttackEV(effect, max);
                }
                p.setFriendliness(1);
                break;
            case DEFENSE:
                if (p.getDefenseEV() < 100) {
                    p.setDefenseEV(effect, max);
                }
                p.setFriendliness(1);
                break;
            case SP_ATTACK:
                if (p.getSpAttackEV() < 100) {
                    p.setSpAttackEV(effect, max);
                }
                p.setFriendliness(1);
                break;
            case SP_DEFENSE:
                if (p.getSpDefenseEV() < 100) {
                    p.setSpDefenseEV(effect, max);
                }
                p.setFriendliness(1);
                break;
            case SPEED:
                if (p.getSpeedEV() < 100) {
                    p.setSpeedEV(effect, max);
                }
                p.setFriendliness(1);
                break;
            case ACCURACY:
                if (p.getAccuracyEV() < 100) {
                    p.setAccuracyEV(effect, max);
                }
                p.setFriendliness(1);
                break;
            case EVASIVENESS:
                if (p.getEvasivenessEV() < 100) {
                    p.setEvasivenessEV(effect, max);
                }
                p.setFriendliness(1);
                break;
            case HP: // including conditions + pp
                //Must be overridden for revives
                if (!p.isFainted()) {
                    p.setCurrentHP(effect);
                }
                break;
            case FRIENDLINESS:
                p.setFriendliness(effect);
                break;
            case EXP:
                p.setExp(effect);
                break;
            case LEVEL:
                p.levelUp();
                break;
        }
    }

    @Override
    public String[] getChoices() {
        String[] s = {"Use", "Info", "Back"};
        return s;
    }

    @Override
    public void doBehavior(int makeMenu) {
        switch (makeMenu) {
            case 0:
                useMedicine(GameManager.getPlayer().choosePokemon());
                break;
            case 1:
                UserInput.printALine(UserInput.keyInput, this.toString());
                break;
            case 2:
                break; //GameManager will handle moving on
            default:
                throw new IllegalArgumentException();
        }
    }
}
