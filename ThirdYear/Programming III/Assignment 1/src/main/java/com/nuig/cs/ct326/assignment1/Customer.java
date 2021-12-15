package com.nuig.cs.ct326.assignment1;
import org.joda.money.*;
import java.time.*;

/**
 * This class represents customers who are registered for a loyalty program. Customers can earn loyalty points 
 * based on the amount they spend. 
 * @author Daniel Hannon 19484286
 *
 */
public abstract class Customer {

	private static int amountOfAccounts = 0;//The Lowest Account Number in which there is not a value assigned
	private String firstName;				//the first name of the customer
	private String lastName;				//the surname of the customer
	private int pointsEarned;				//the current points earned by the customer
	private LocalDate registerDate;			//The Date they Registered on
	private int accountNumber;				//Their Account Number
	
	
	/**
	 * Constructor for instantiating Customer objects
	 * @param firstName a String representing the first name of the customer
	 * @param lastName a String representing the surname of the customer
	 */
	public Customer(String firstName, String lastName){
		this.firstName = firstName;
		this.lastName = lastName;
		pointsEarned = 0;
		//If no date is passed, it stores today's date
		this.registerDate = LocalDate.now();
		this.accountNumber = amountOfAccounts;
		amountOfAccounts++; //This increments so the next account is assigned a different value
	}

	/**
	 * Alternate Constructor for Customer Objects
	 * @param firstName Customers First name
	 * @param lastName Customers Last Name
	 * @param registerDate Date the Customer Registered
	 * @throws InvalidRegisterDateException returned when the register date is in the future
	 */
	public Customer(String firstName, String lastName, LocalDate registerDate) throws InvalidRegisterDateException {
		if(registerDate.isAfter(LocalDate.now())) {
			throw new InvalidRegisterDateException("Register Date is in the future!");
		}
		this.firstName = firstName;
		this.lastName = lastName;
		this.registerDate = registerDate;
		this.pointsEarned = 0;
		this.accountNumber = amountOfAccounts;
		amountOfAccounts++;
	}
	
	/**
	 * A method for calculating the points due to the customer based on their spending and then adding this to
	 * their currently earned points.
	 * @param pointsToAdd int representing the amount of points to add for the customer before any tier bonus has been applied.
	 */
	public abstract void calculatePointsAndAdd(int pointsToAdd);
	
	/**
	 * A method for making a customer purchase and adding an appropriate set of points.
	 * @param purchaseAmount the amount spent by the customer for a purchase.
	 */
	public void makePurchase (Money purchaseAmount) throws NegativePurchaseAmountException {
		if(purchaseAmount.isNegative()) {
			throw new NegativePurchaseAmountException("Value is Less than zero!");
		}
		int qtyEuros = purchaseAmount.getAmountMajorInt();
		if(qtyEuros >=100) {
			calculatePointsAndAdd(qtyEuros / 100);
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
	 * Checks Anniversary
	 * Increments Points if it's the anniversary of when they joined
	 * Unless they joined on Feb 29th, and it's not a leap year, where instead
	 * it increments on March 1st
	 */
	public void checkAnniversary() {
		LocalDate today = LocalDate.now();
		int days = this.registerDate.getDayOfMonth() - today.getDayOfMonth();
		int month = this.registerDate.getMonthValue() - today.getMonthValue();
		int year = this.registerDate.getYear() - today.getYear();
		if(days == 0 && month == 0 && year!=0) {
			this.pointsEarned+=100;
		} else if(this.registerDate.getDayOfMonth() == 29 && this.registerDate.getMonthValue() == 1 && !today.isLeapYear()) {
			//Leap Year Check
			//Use March 1st on Non-Leap Years if the register date is February 29th
			if(today.getDayOfMonth() == 1 && today.getMonthValue() == 2) {
				this.pointsEarned+=100;
			}
		}
	}

	@Override
	public String toString() {
		return "############\nAccount Number: "+this.accountNumber+"\nName: " + this.firstName + " " + this.lastName + "\nRegister Date: " + this.registerDate.toString()+"\n"+"Points Earned: "+this.pointsEarned;
	}
}
