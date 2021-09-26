public class Engine {
	/*Private variables*/
	private Wheel wheel; 
	private String engineModel;
	private double tpl;
	private double totalTurns;
	private double fuelLevel;

	/*Initialization*/
	public Engine(String nenginemodel, double ntpl) {
		if (ntpl < 0) {
			ntpl *= -1;
		}
		this.engineModel = nenginemodel;
		this.tpl = ntpl;
		this.totalTurns = 0;
		this.fuelLevel = 0;
		this.wheel = null;
	}

	/*Methods*/
	public double move() {
		try {
			double distance = (tpl * fuelLevel) * wheel.turn();
			this.totalTurns += tpl * fuelLevel;
			this.fuelLevel = 0;
			return distance;
		} catch(Exception e) {
			System.out.println("ERROR: No Wheels!");
			return 0;
		}
	}

	/*Getters*/
	public String getEnginemodel() {
		return this.engineModel;
	}
	public Wheel getWheel() {
		return this.wheel;
	}
	public double getTpl() {
		return this.tpl;
	}
	public double getFuel() {
		return this.fuelLevel;
	}

	public double getTotalturns() {
		return this.totalTurns;
	}

	/*Setters, you cannot individually change tpl 
	or engine model as that would not make sense*/
	public void setWheel(Wheel nwheel) {
		this.wheel = nwheel;
	}
	public void setFuel(double nfuel) {
		/* This is done to simulate a more realisitc 
		model of a car where you can add fuel more than once */
		this.fuelLevel += nfuel;
	}
}