import java.util.Date;
import java.text.SimpleDateFormat;
/*
*	As this is an end user class, IE - not controlled by the bus company
*	It does not require any setters beyond the initalization of a trip ID
*	And the ability to modify the amount of Available seats due to seats being
*	Purchased
*/
public class Trip{
	private int ID;
	private int seats;
	private float cost;
	private String startingLocation;
	private String destination;
	private Date dateOfDeparture;
	private Date timeOfDeparture;
	private Date dateOfArrival;
	private Date timeOfArrival;

	public Trip(int ID, float cost, String start, String destination, String departureDate, String departureTime, String arrivalDate, String arrivalTime) {
		this.seats = 60;
		this.ID = ID;
		this.startingLocation = start;
		this.destination = destination;
		this.cost = cost;
		try {
			/*Dates are stored as Date Objects as they occupy less space than a String*/
			this.dateOfDeparture = new SimpleDateFormat("dd/MM/yyyy").parse(departureDate);
			this.timeOfDeparture = new SimpleDateFormat("HH:mm").parse(departureTime);
			this.dateOfArrival = new SimpleDateFormat("dd/MM/yyyy").parse(arrivalDate);
			this.timeOfArrival = new SimpleDateFormat("HH:mm").parse(arrivalTime);
		} catch(Exception e) {
			System.out.printf("Parse Error");
		}
	}

	public int getID() {
		return this.ID;
	}

	public int getSeats() {
		return this.seats;
	}

	public float getCost() {
		return this.cost;
	}

	public String getStartingLocation() {
		return this.startingLocation;
	}

	public String getDestination() {
		return this.destination;
	}

	public Date getDateOfDeparture() {
		return this.dateOfDeparture;
	}

	public Date getTimeOfDeparture() {
		return this.timeOfDeparture;
	}

	public Date getDateOfArrival() {
		return this.dateOfArrival;
	}

	public Date getTimeOfArrival() {
		return this.timeOfArrival;
	}

	public void setSeats(int newSeats) {
		this.seats = newSeats;
	}
	/*It did not make sense not to override toString*/
	@Override
	public String toString() {
		String output = "";
		output += "Trip ID: " + ID;
		output += "\nOrigin: " + startingLocation;
		output += "\nDestination: " + destination;
		output += "\nDeparture Date: " + new SimpleDateFormat("dd/MM/yyyy").format(dateOfDeparture);
		output += "\nDeparture Time: " + new SimpleDateFormat("HH:mm").format(timeOfDeparture);
		output += "\nArrival Date: " + new SimpleDateFormat("dd/MM/yyyy").format(dateOfArrival);
		output += "\nArrival Time: " + new SimpleDateFormat("HH:mm").format(timeOfArrival);
		output += "\nFare: €" + cost +" per passenger";
		output += "\nCurrently Available Seats: " + seats+"\n";
		return output;
	}
}
