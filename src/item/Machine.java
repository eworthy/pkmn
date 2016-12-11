package item;

public abstract class Machine extends Item implements Purchasable {
	private int price;

	/**
	 * @param name
	 * @param description
	 */
	public Machine(String name, String description, int price) {
		super(name, description);
		this.price = price;
	}
	
	public String toShopString() {
		return name + ": " + price;
	}
}
