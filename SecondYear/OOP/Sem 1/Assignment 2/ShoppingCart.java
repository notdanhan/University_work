import java.util.ArrayList;
public class ShoppingCart {
	private ArrayList<Item> Cart;
	private float TotalCost;
	private boolean locked;
	private String time;
	private final long cartId;

	/*Initilaisation*/
	public ShoppingCart() {
		this.TotalCost = 0;
		this.locked = false;
		this.Cart = new ArrayList<Item>();
		/*The time in which the shopping cart is created depends on the time it is called,
		the customer should have no ability to modify this whatsoever */
		this.time = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").format(new java.util.Date());
		this.cartId = makeCartID();
	}

	/*Locking Mechanism*/
	public void close() {
		this.locked = true;
	}

	public void UnlockCart() {
		this.locked = false;
	}

	/*ShoppingCart Interractions*/
	public void addItem(Item newItem) {
		if (!this.locked) {
			this.Cart.add(newItem);
			this.TotalCost += newItem.getCost();
			System.out.println("Item: "+newItem.getName()+" Has been added to your cart!");
		} else {
			System.out.println("Sorry the shopping cart is closed");
		}
	}

	public void removeItem(Item removItem) {
		if (!this.locked) {
			this.Cart.remove(removItem);
			this.TotalCost -= removItem.getCost();
			System.out.println("Item: " + removItem.getName() + " Has Been removed!");
		} else {
			System.out.println("Cannot remove item, cart is locked");
		}
	}

	public Item takeItem(int index) {
		if(this.Cart.size() <= index) {
			System.out.println("Item Does not exist");
			return null;
		} else {
			Item temp = this.Cart.get(index);
			this.Cart.remove(index);
			this.TotalCost -= temp.getCost();
			return temp;
		}
	}

	public int getCartSize() {
		return this.Cart.size();
	}

	public void listShopping() {
		System.out.println("|\tItem\t\t|\t\tCost\t|");
		for (int i = 0; i < this.Cart.size();i++) {
			Item temp = this.Cart.get(i);
			System.out.println("|\t"+temp.getName()+"\t|\t"+temp.getCost()+
			"\t|");
		}
		System.out.println("|\tTotal:\t\t|\t\t"+TotalCost+"\t|\n");
	}

	/*Getters*/
	public float getTotalCost() {
		return this.TotalCost;
	}
	public String getTime() {
		return this.time;
	}

	public long getCartId() {
		return this.cartId;
	}

	/*Internal methods*/
	private long makeCartID() {
		return (long) (Math.random() * 1000000);
	}
}
