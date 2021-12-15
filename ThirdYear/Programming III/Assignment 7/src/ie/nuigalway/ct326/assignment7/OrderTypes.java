package ie.nuigalway.ct326.assignment7;

public enum OrderTypes {
	HOODIE("Anniversary Hoodie"),
	MUG("Fan Mug"),
	TSHIRT("TShirt with logo");

	private String description;

	OrderTypes(String orderDescription) {
		this.description = orderDescription;
	}

	public String getDescription() {
		return this.description;
	}
}
