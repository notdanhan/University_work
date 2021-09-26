public class Booking {
	private Trip myTrip;
	private int seats;
	private float cost;
	public Booking(Trip newTrip, int newSeats) {
		this.myTrip = newTrip;
		this.seats = newSeats;
		this.cost = 0.0f;
	}
	public Trip getTrip() {
		return this.myTrip;
	}

	public int getSeats() {
		return this.seats;
	}

	public float getCost() {
		return this.cost;
	}

	public void setTrip(Trip trip) {
		this.myTrip = trip;
	}

	public void setSeats(int newSeats) {
		this.seats = newSeats;
	}

	public void setCost(float newCost) {
		this.cost = newCost;
	}
}
