package ie.nuigalway.cs.ct326;

import java.time.LocalDate;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
/**
 * A test class for the customer loyalty system for Assignment 2.
 * @author Daniel Hannon 194848286
 *
 */
public class Assignment2Test {
	
	
	/**
	 * Utility method for calling the makePurchase method for a customer and passing an appropriate
	 * implementation of the PointsCalculator interface.
	 * @param customer	the customer making the purchase
	 * @param amount	the amount of the purchase
	 * @throws NegativePurchaseAmountException	Negative purchase values are not allowed.
	 */
	public static void makePurchase(Customer customer, Money amount) throws NegativePurchaseAmountException {
		//Values from assignment 1
		//Blue 1x
		//Silver 1.15x
		//Gold 1.3x
		//Platinum 1.5x;
		switch(customer.getCustomerTier()) {
			case BLUE:
				//Lambda Function Implementation of PointsCalculator
				customer.makePurchase(amount, (points)->{
					return points;
				});
				break;
			case SILVER:
				//Class implementing PointsCalculator
				customer.makePurchase(amount,new SilverPointsCalculator());
				break;
			case GOLD:
				//anonymous inner class implementation
				PointsCalculator myPointsCalculator = new PointsCalculator() {
					@Override
					public int calculatePoints(int somePoints) {
						return somePoints + ((int)((float)somePoints * 0.3f));
					}
				};
				customer.makePurchase(amount, myPointsCalculator);
				break;
			case PLATINUM:
				//Another Lambda Implementation
				customer.makePurchase(amount, (points)->{
					return points + (points/2);
				});
				break;
		}
	}

	public static void main(String[] args) {
		CurrencyUnit eur = CurrencyUnit.of("EUR");
		Customer c1,c2,c3,c4;
		int nextIndex = 0;
		
		
		Customer [] customers = new Customer[4];									//declare and initialise array for customers
		try {
			c1 = new Customer("Peter", "O'Reilly", LocalDate.of(2004, 01, 03),Tier.SILVER);		//create a new Silver customer and
			customers[nextIndex] = c1;												//add to the array
			nextIndex++;
			
			Assignment2Test.makePurchase(c1, Money.of(eur, 160d));					//call to utility makePurchase method above
		} catch (InvalidRegisterDateException e1) {
			System.out.println(e1.getMessage());
		} catch (NegativePurchaseAmountException e) {
			e.printStackTrace();
		}

		try {
			c2 = new Customer("Orla", "Finn", LocalDate.of(2021, 05, 21),Tier.BLUE);			//create a new Blue customer and
			customers[nextIndex] = c2;												//add to the array
			nextIndex++;
			Assignment2Test.makePurchase(c2, Money.of(eur, 160d));					//call to utility makePurchase method above
		} catch (InvalidRegisterDateException e) {
			System.out.println(e.getMessage());
		} catch (NegativePurchaseAmountException e) {
			e.printStackTrace();
		}

		try {
			c3 = new Customer("Vanessa", "Weathers", LocalDate.of(2014, 01, 01),Tier.GOLD);	//create a new Gold customer and
			customers[nextIndex] = c3;												//add to the array
			nextIndex++;
			Assignment2Test.makePurchase(c3, Money.of(eur, 160d));					//call to utility makePurchase method above
		} catch (InvalidRegisterDateException e) {
			System.out.println(e.getMessage());
		} catch (NegativePurchaseAmountException e) {
			e.printStackTrace();
		}
		
		try {
			c4 = new Customer("Philip", "Grant", LocalDate.of(1999, 9, 16),Tier.PLATINUM);		//create a new Platinium customer and
			customers[nextIndex] = c4;												//add to the array
			nextIndex++;
			Assignment2Test.makePurchase(c4, Money.of(eur, 5000d));					//call to utility makePurchase method above
		} catch (InvalidRegisterDateException e1) {
			System.out.println(e1.getMessage());
		} catch (NegativePurchaseAmountException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < customers.length; i++) {									//loop through the customer array and check for 
																					//anniversaries before printing them out.
			if(customers[i] != null) {
				if(customers[i].getRegisterDate().getDayOfMonth() == LocalDate.now().getDayOfMonth() &&
				   customers[i].getRegisterDate().getMonthValue() == LocalDate.now().getMonthValue()) {
					customers[i].addPoints(100);					
				}
				System.out.println(customers[i]);
			}
		}
	}
}
