package ie.nuigalway.ct326.assignment7;

public class Order {
	private int orderNo;
	private OrderTypes orderType;
	public Order(int orderNo, OrderTypes orderType) {
		this.orderNo = orderNo;
		this.orderType = orderType;
	}

	public int getOrderNo() {
		return this.orderNo;
	}

	public OrderTypes getOrderType() {
		return this.orderType;
	}
}
