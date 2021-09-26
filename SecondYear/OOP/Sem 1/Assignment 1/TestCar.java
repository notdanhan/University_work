public class TestCar {
	public static void main(String[] args) {
		Car car = new Car("Toyota Corolla");
		Engine engine = new Engine("2ZZ-GE",57);
		car.add(engine);
		Wheel wheel =  new Wheel("Michelin Pilot",21);
		car.add(wheel);
		car.setFuel(500);
		System.out.println("Current Fuel: " + car.getFuel());
		car.drive();
		car.printState();
		car.setFuel(125);
		System.out.println("Current Fuel: " + car.getFuel());
		car.drive();
		car.printState();
		/*Change tyres for the craic*/
		wheel = new Wheel("Pirelli P1",22);
		car.add(wheel);
		car.setFuel(250);
		System.out.println("Current Fuel: " + car.getFuel());
		car.drive();
		car.printState();
	}
}