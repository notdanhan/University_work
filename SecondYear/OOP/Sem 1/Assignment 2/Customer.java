public class Customer {
	private String firstName;
	private String lastName;
	private String emailAddress;
	private final long customerId;
	private ShoppingCart customerCart; 
	public Customer(String fName, String lName, String eAddress) {
		this.firstName = fName;
		this.lastName = lName;
		this.emailAddress = eAddress;
		this.customerId = makeCustomerId();
	}
	/*Getters*/
	public long getID() {
		return this.customerId;
	}

	public ShoppingCart getShoppingCart() {
		return this.customerCart;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	/*Setters*/
	public void setShoppingCart(ShoppingCart newCart) {
		this.customerCart = newCart;
	}

	public void setFirtName(String name) {
		this.firstName = name;
	}

	public void setLastName(String name) {
		this.lastName = name;
	}

	public void setEmailAddress(String name) {
		this.emailAddress = name;
	}

	/*Internal Methods*/
	private long makeCustomerId() {
		/*returns an arbritary 6 digit customer ID*/
		return (long) Math.random() * 1000000;
	}
}