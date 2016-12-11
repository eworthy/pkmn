package item;

public abstract class KeyItem extends Item {

	/**
	 * @param name
	 * @param description
	 */
	public KeyItem(String name, String description) {
		super(name, description);
		// TODO Auto-generated constructor stub
	}

	public boolean isPersistant() {
		return true;
	}
	
	public String toShopString() {
		return null;
	}
}
