package character;

import java.io.Serializable;

public class Mother extends GameCharacter implements Serializable {
    private int money;

    public Mother(String[] spriteFiles) {
        super("Mum", spriteFiles);
        money = 0;
    }

    /**
     * Get the mother's money
     *
     * @return the amount of money
     */
    public int getMoney() {
        return money;
    }

    /**
     * Sets the mother's money
     *
     * @param change the amount to add (negative to subtract)
     */
    public void setMoney(int change) {
        money += change;
    }
}
