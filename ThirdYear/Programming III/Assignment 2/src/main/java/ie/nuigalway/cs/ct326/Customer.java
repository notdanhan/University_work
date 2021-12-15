package ie.nuigalway.cs.ct326;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

/**
 * This class represents customers who are registered for a loyalty program. Customers can earn loyalty points 
 * based on the amount they spend. 
 * @author Daniel Hannon (19484286)
 *
 */
public class Customer {
	private String firstName;				//the first name of the customer
	private String lastName;				//the surname of the customer
	private LocalDate registerDate;			//the date the customer registered for the loyalty program
	private int customerID;					//unique ID of the customer
	private int pointsEarned;				//the current points earned by the customer
	private Tier customerTier;				//The Tier of the customer

	
	private static int nextCustomerID = 0;	//a static int to hold the ID for the next customer to register
	
	/**
	 * Constructor for instantiating Customer objects
	 * @param firstName a String representing the first name of the customer
	 * @param lastName a String representing the surname of the customer
	 * @param registerDate the LocalDate on which the customer registered for the loyalty program. Cannot be in the future.
	 * @param customerTier the tier in which the customer falls under
	 * @throws InvalidRegisterDateException Thrown if the registration date is in the future.
	 */
	public Customer(String firstName, String lastName, LocalDate registerDate, Tier customerTier) throws InvalidRegisterDateException {
		this.firstName = firstName;
		this.lastName = lastName;
		pointsEarned = 0;
		if(registerDate.isAfter(LocalDate.now())) throw new InvalidRegisterDateException("The value " + registerDate + " was entered for " + firstName + 
				" " + lastName + ". The join date cannot be in the future.");
		this.registerDate = registerDate;
		this.customerTier = customerTier;
		customerID = nextCustomerID;
		nextCustomerID++;
	}
	
	/**
	 * A method for making a customer purchase and adding an appropriate set of points.
	 * @param purchaseAmount the amount spent by the customer for a purchase.
	 * @param calc a PointsCalculator to determine how bonus points are to be applied to the customer.
	 * @throws NegativePurchaseAmountException if the value of the purchase is less than zero.
	 */
	public void makePurchase (Money purchaseAmount, PointsCalculator calc) throws NegativePurchaseAmountException {
		CurrencyUnit eur = CurrencyUnit.of("EUR");
		if(purchaseAmount.isLessThan(Money.of(eur, 0.0d))) throw new NegativePurchaseAmountException("The customer " + firstName + " " + lastName 
				+ " attempted to make a purchase for " + purchaseAmount + ". Cannot make a purchase for a negative amount.");
		if(purchaseAmount.getAmount().doubleValue() >=100) {
			//Divide by 100 and runt through the points calculator
			this.pointsEarned+=calc.calculatePoints(purchaseAmount.getAmountMajorInt()/100);
		}
	}
	
	
	/**
	 * Get the current number of points earned by a customer.
	 * @return the current number of points earned by a customer.
	 */
	public int getPoints() {
		return pointsEarned;
	}
	
	/**
	 * Add points to the customers existing loyalty points
	 * @param pointsToAdd the points to add for the customer
	 */
	public void addPoints(int pointsToAdd) {
		pointsEarned += pointsToAdd;
	}
	
	/**
	 * Get the date that the customer was registered for the loyalty program on.
	 * @return an LocalDate indicating the date that the customer registered on.
	 */
	public LocalDate getRegisterDate() {
		return registerDate;
	}

	public Tier getCustomerTier() {
		return this.customerTier;
	}

	/**
	 * Prints a formatted customer string
	 * @return formatted customer string
	 */
	@Override
	public String toString() {
		String returnVal = "";
		switch(this.customerTier) {
			case BLUE:
				returnVal += "BLUE";
				break;
			case SILVER:
				returnVal += "SILVER";
				break;
			case GOLD:
				returnVal += "GOLD";
				break;
			case PLATINUM:
				returnVal +="PLATINUM";
				break;
		}
		//Construct the return string to match the one defined in the document
		returnVal = returnVal.format("%s %d: %s %s (%s)\nLoyalty Points Balance: %d\n",
				returnVal, this.customerID, this.firstName, this.lastName,
				this.registerDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")),
				this.pointsEarned);
		return returnVal;
	}
}
