package item;

import basicFunctions.UserInput;

public abstract class GenericItem extends Item implements Purchasable, Usable {
    
    private final String[] CHOICES = {"Give", "Info", "Back"};
    private final int price;

    /**
     * @param name
     * @param description
     * @param price
     */
    public GenericItem(String name, String description, int price) {
        super(name, description);
        this.price = price;
    }

    @Override
    public String[] getChoices() {
        return CHOICES;
    }
    

    @Override
    public boolean isPersistant() {
        return true;
    }

    @Override
    public String toShopString() {
        return name + ": " + price;
    }

    @Override
    public int getPrice() {
        return price;
    }
      
    
    @Override
    public void doBehavior(int makeMenu) {
        switch (makeMenu) {
            case 0:
                super.give(this);
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
