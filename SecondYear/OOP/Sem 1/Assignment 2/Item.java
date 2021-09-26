public class Item {
	private String name;
	private float cost;
	private long itemId;
	public Item(String name, float cost) {
		this.name = name;
		this.cost = cost;
		this.itemId = createID();
	}
	public String getName() {
		return this.name;
	}
	public float getCost() {
		return this.cost;
	}
	public void setPrice(float nPrice) {
		this.cost = nPrice;
	}
	private long createID() {
		return (long) (Math.random() * 999999);
	}
	@Override
	public String toString() {
		String out = "Item Id: "+ itemId+"\t"+name+"\tPrice: "+cost;
		return out;
	}
}
 