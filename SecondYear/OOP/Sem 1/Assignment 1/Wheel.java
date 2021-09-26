public class Wheel {
	/*Private variables*/
	private String brand;
	private double radius;
	private double circumference;

	/*Initilization*/
	public Wheel(String nbrand, double nradius) {
		if (nradius < 0) {
			nradius *= -1;
		}
		this.brand = nbrand;
		this.radius = nradius;
		this.circumference = 2 * Math.PI * radius;
	}

	/*Getters*/
	public double getRadius() {
		return this.radius;
	}
	public String getBrand() {
		return this.brand;
	}
	/*As engine calls turn(), I called getCircumference() turn() instead*/
	public double turn() {
		return this.circumference;
	}

	/*Realisitcally this would not have any
	setters beyond the initilisation as it is a tyre*/
}