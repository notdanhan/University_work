package com.nuig.cs.ct326.assignment1;

import java.time.LocalDate;

/**
 * This class represents a customer who is on the Platinum tier of the loyalty program.
 * @author Daniel Hannon 19484286
 *
 */
public class PlatinumTierCustomer extends Customer {

	/**
	 * Constructor for creating platinum tier customers
	 * @param firstName a String representing the first name of the customer
	 * @param lastName a String representing the surname of the customer
	 */
	public PlatinumTierCustomer(String firstName, String lastName){
		super(firstName, lastName);
	}
	public PlatinumTierCustomer(String firstName, String lastName, LocalDate registerDate) throws InvalidRegisterDateException {
		super(firstName,lastName,registerDate);
	}
	@Override
	public void calculatePointsAndAdd(int pointsToAdd) {
		super.addPoints(pointsToAdd + (int)(pointsToAdd*(50/100.0f)));
	}
	
	@Override
	public String toString() {
		return super.toString() + "\nTier: Platinum";
	}
}
