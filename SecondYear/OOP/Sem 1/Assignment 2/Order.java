import java.util.*;
public class Order {
	private ArrayList<Item> OrderItems;
	private Customer customer;
	private ShoppingCart shoppingCart;
	private Address billingAddress;
	private Address deliveryAddress;
	private long orderNo;
	private boolean isConfirmed;
	private float totalCost;

	/*Billing/delivery have same address*/
	Order(Customer customer, ShoppingCart Cart) {
		this.OrderItems = new ArrayList<Item>();
		this.customer= customer;
		this.shoppingCart = Cart;
		this.orderNo = shoppingCart.getCartId();
		this.isConfirmed = false;
		this.totalCost = 0;
	}

	Order(Customer customer, ShoppingCart Cart, Address address) {
		this.OrderItems = new ArrayList<Item>();
		this.customer= customer;
		this.shoppingCart = Cart;
		this.deliveryAddress = address;
		this.billingAddress = address;
		this.orderNo = shoppingCart.getCartId();
		this.isConfirmed = false;
		this.totalCost = 0;
	}
	/*Billing/delivery have different addresses*/
	Order(Customer customer, ShoppingCart Cart, Address deliveryAddress, Address billingAddress) {
		this.OrderItems = new ArrayList<Item>();
		this.customer= customer;
		this.shoppingCart = Cart;
		this.deliveryAddress = deliveryAddress;
		this.billingAddress = billingAddress;
		this.orderNo = shoppingCart.getCartId();
		this.isConfirmed = false;
		this.totalCost = 0;
	}

	private String stringifyOrder() {
		if (this.OrderItems.size() == 0) {
			return "";
		}
		String output = "";
		for(int i = 0;i < OrderItems.size(); i++) {
			output += OrderItems.get(i).toString() + "\n";
		}
		return output;
	}

	public void processOrder() {
		this.totalCost = shoppingCart.getTotalCost();
		int length = shoppingCart.getCartSize();
		for (int i = 0; i < length; i++) {
			Item temp = shoppingCart.takeItem(0);
			if (temp == null) {
				break;
			}
			this.OrderItems.add(temp);
		}
	}

	public void confirmOrder() {
		if(this.OrderItems.size() == 0) {
			processOrder();
			if(this.OrderItems.size() == 0) {
				return;
			}
		}
		this.isConfirmed = true;
	}

	public void sendEmail() {
		Email email = new Email(customer.getEmailAddress(),customer.getFirstName());
		if(isConfirmed) {
			if (this.deliveryAddress!= null && this.billingAddress != null) {
				email.SendSuccess(this.orderNo,this.stringifyOrder(),
				totalCost,this.deliveryAddress,this.billingAddress);
			} else {
				System.out.println("You forgot to add an address!");
			}
		}
		else {
			email.SendFailure();
		}
	}

	public long getOrderNumber() {
		return this.orderNo;
	}

	public boolean getStatus() {
		return this.isConfirmed;
	}

	public void addAddress(Address address) {
		this.deliveryAddress = address;
		this.billingAddress = address;
	}

	public void addAddress(Address delivery, Address billing) {
		this.deliveryAddress = delivery;
		this.billingAddress = billing;
	}
}