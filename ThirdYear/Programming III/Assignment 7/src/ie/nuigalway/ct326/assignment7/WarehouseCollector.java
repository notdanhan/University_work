package ie.nuigalway.ct326.assignment7;

public class WarehouseCollector extends Thread {
	private String name;
	private static int completed = 0;
	private int ordersCompleted;
	private int shirtOrders;
	private int mugOrders;
	private int hoodieOrders;
	private boolean alive;
	public WarehouseCollector(String name) {
		super();
		this.name = name;
		this.ordersCompleted = 0;
		this.shirtOrders = 0;
		this.mugOrders = 0;
		this.hoodieOrders = 0;
		this.alive = true;
	}

	public void unAlive() {
		this.alive = false;
	}
	@Override
	public void run() {
		while(alive) {
			try {
				Thread.sleep((long) (Math.random() * 200));
			} catch(InterruptedException err) {
				err.printStackTrace();
			}
			if (completed == 100) {
				this.alive = false;
			}
		}
		System.out.println("Collector "+name+" finished processing "+ordersCompleted+" orders for delivery, including:\n"+shirtOrders+" t-shirt orders,\n"+mugOrders+" mug orders and,\n"+hoodieOrders+" Hoodie Orders");
	}
}
