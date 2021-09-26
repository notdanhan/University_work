public class TestTransaction  {
	public TestTransaction(){
		System.out.println("Test Transaction created!");
	};
	public static void main(String[] args) {
		TestTransaction one = new TestTransaction();
		System.out.println("Test Transaction 1");
		one.transaction1();
		System.out.println("******************");
		System.out.println("Test Transaction 2");
		one.transaction2();
	}
	public void transaction1() {
		Customer customer = new Customer("Robert","Paulson","t.durden1998@email.com");
		ShoppingCart cart = new ShoppingCart();
		cart.addItem(new Item("Soap",3.24f));
		cart.addItem(new Item("Spray Paint",5.50f));
		cart.addItem(new Item("Ikea Furniture",19.99f));
		cart.listShopping();
		cart.close();
		Order order = new Order(customer,cart);
		order.processOrder();
		order.addAddress(new Address("34","Memory Lane","Churchill Road", "F42XYXY","Croydon"));
		Payment payment = new Payment(order,"Visa",4319000000000000L,"12/21");
		payment.validate();
		payment.Confirm();

	}
	public void transaction2() {
		Customer customer = new Customer("Luigi","Mario","Luigi@mariobros.com");
		ShoppingCart cart = new ShoppingCart();
		cart.addItem(new Item("Plumbers hat",23.45f));
		cart.addItem(new Item("Powerup",19.86f));
		cart.addItem(new Item("Movie",19.93f));
		cart.removeItem(new Item("Star",19.93f));
		cart.close();
		Order order = new Order(customer,cart);
		order.processOrder();
		order.addAddress(new Address("Mushroom Kingdom","Street","Mario Luigi","Mario"));
		Payment myPayment = new Payment(order, "Pizza", 4319999999999999L, "11/12");
		myPayment.validate();
		myPayment.Confirm();
		
	}
}