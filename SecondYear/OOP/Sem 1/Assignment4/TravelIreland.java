public class TravelIreland {
	public static void main(String args[]) {
		BusEireann buseireann = new BusEireann();
		GoBus gobus = new GoBus();
		CityLink citylink = new CityLink();

		/*Print out all Available trips*/
		System.out.println("Printing all Available trips\n");
		System.out.print(buseireann.getAllAvailableTrips());
		System.out.println("-----------------------------------");
		System.out.println(gobus.getAllAvailableTrips());
		System.out.println("-----------------------------------");
		System.out.print(citylink.getAllAvailableTrips());
		System.out.println("-----------------------------------");

		/*Test Bookings*/
		System.out.println("Test Bookings:");
		/*Failed Bookings*/
		System.out.println("These should all fail");
		System.out.println("Invalid Seat Quantities");
		TestBooking(buseireann,new Booking(buseireann.getTrip(5947),61));
		TestBooking(gobus, new Booking(gobus.getTrip(340),-1));
		TestBooking(citylink, new Booking(citylink.getTrip(8643),700));
		System.out.println("Invalid Trip Data");
		TestBooking(buseireann,new Booking(citylink.getTrip(436),1));
		TestBooking(gobus, new Booking(buseireann.getTrip(1694),3));
		TestBooking(citylink, new Booking(gobus.getTrip(4916),2));
		/*Successful Bookings*/
		System.out.println("These Should All Succeed");
		TestBooking(buseireann, new Booking(buseireann.getTrip(913),5));
		TestBooking(gobus, new Booking(gobus.getTrip(340),17));
		TestBooking(citylink, new Booking(citylink.getTrip(9662),12));
	}
	public static void TestBooking(Company company, Booking myBooking) {
		Boolean success = company.makeBooking(myBooking);
		if(success) {
			System.out.print("Booking Successful!\n");
			/*This verifies that the Seat Data within the company changes when a booking is successful*/
			System.out.print(company.getTrip(myBooking.getTrip().getID()));
			System.out.print("Total Cost: " + String.format("%.2f",myBooking.getCost()) +"\n\n");
		} else {
			System.out.print("Transaction Failed!\n\n");
		}
	}
}
