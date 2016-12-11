package item;

public abstract class Berry extends Item implements Usable {

	/**
	 * @param name
	 * @param description
	 */
	public Berry(String name, String description) {
		super(name, description);
	}
	
	public boolean isPersistant() {
		return false;
	}
}
