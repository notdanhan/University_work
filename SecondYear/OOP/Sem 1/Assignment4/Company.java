import java.util.ArrayList;

/*To stay in line with the Design featured in the PDF each bus company is an extension of
an Abstract class Company*/
public abstract class Company  {
	//Used ArrayList to record journeys as it does not require an initalization value
	ArrayList<Trip> journeys;
	String companyName;
	public Company() {
		journeys = new ArrayList<Trip>();
	}

	public Trip getTrip(int ID) {
		for(int i = 0; i < journeys.size(); i++) {
			//Iterates through the ArrayList looking for the ID given, returns null if it does not exist
			if(journeys.get(i).getID() == ID) {
				return journeys.get(i);
			}
		}
		return null;
	}

	public boolean makeBooking(Booking mybooking) {
		//Error handling, make sure the trip exists
		if(mybooking.getTrip() == null || !journeys.contains(mybooking.getTrip())) {
			return false;
		}
		//Makes sure a valid seat quanitiy is entered
		if(mybooking.getTrip().getSeats() > mybooking.getSeats() && mybooking.getSeats() > 0) {
			mybooking.setCost(mybooking.getTrip().getCost() * mybooking.getSeats());
			mybooking.getTrip().setSeats(mybooking.getTrip().getSeats() - mybooking.getSeats());
			return true;
		}
		return false;
	}

	public String getAllAvailableTrips() {
		/*This takes advantage of the new toString tht was produced for trip*/
		String output = "";
		for(int i = 0; i < journeys.size(); i++) {
			output += "Company Name: " + companyName +"\n"+journeys.get(i).toString()+"\n\n";
		}
		return output;
	}
}
