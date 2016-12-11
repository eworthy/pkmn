package item;

public abstract class BattleItem extends Item implements Purchasable, Usable {
	private int price;

	/**
	 * @param name
	 * @param description
	 */
	public BattleItem(String name, String description, int price) {
		super(name, description);
		this.price = price;
	}
	
	public boolean isPersistant() {
		return false;
	}
	
	public String toShopString() {
		return name + ": " + price;
	}
}
