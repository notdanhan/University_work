public class Car {
	/*Private variables*/
	private String name;
	private double distance;
	private double totalKm;
	private Engine engine;

	/*Initialization*/
	public Car(String cname) {
		this.name = cname;
		this.distance = 0;
		this.totalKm = 0;
		this.engine = null;
	}

	/*Functions of the Car*/
	public void add(Engine nengine) {
		this.engine = nengine;
	}
	/* Overloading is used to avoid typechecking */
	public void add(Wheel nwheel) {
		try {
			engine.setWheel(nwheel);
		} catch(Exception e) {
			System.out.println("ERROR: No Engine");
		}
	}

	public void drive() {
		try {
			this.distance = engine.move();
			this.totalKm+=this.distance;
		} catch(Exception e) {
			System.out.print("ERROR: No Engine");
		}
	}

	public void printState() {
		/*copies instance of wheel from engine to reduce total amount of lines of code*/
		try {
			Wheel tempWheel = engine.getWheel();
			System.out.println("Configuration: " + this.name);
			System.out.println("Engine name: " + engine.getEnginemodel());
			System.out.printf("Engine turns per liter: %.2f\n", engine.getTpl());
			System.out.printf("Engine Total turn count: %.2f\n", engine.getTotalturns());
			System.out.println("Wheel name: " + tempWheel.getBrand());
			System.out.printf("Wheel radius: %.2f\n", tempWheel.getRadius());
			System.out.printf("Wheel circumference (distance per turn): %.2f\n", tempWheel.turn());
			System.out.printf("Distance this trip: %.2f\n", this.distance);
			System.out.printf("Total distance Travelled: %.2f\n", this.totalKm);
			System.out.printf("Current fuel Status: %.2f\n\n", engine.getFuel());
		} catch(Exception e) {
			System.out.println("ERROR: No Engine");
		}
	}

	/*Setters*/
	public void setDistance(double dist) {
		if(dist > 0) {
			this.distance = dist;
		} else {
			System.out.println("You cannot set a negative distance!");
		}
	}
	public void setFuel(double nfuel) {
		try {
			if (nfuel > 0) {
				engine.setFuel(nfuel);
			} else {
				System.out.println("You Cannot take Fuel Out!");
			}
		} catch (Exception e) {
			System.out.println("ERROR: No Engine");
		}
	}
	public double getFuel() {
		return engine.getFuel();
	}
}