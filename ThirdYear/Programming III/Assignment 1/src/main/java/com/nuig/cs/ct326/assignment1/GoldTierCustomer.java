package com.nuig.cs.ct326.assignment1;

import java.time.LocalDate;

/**
 * This class represents a customer who is on the Gold tier of the loyalty program.
 * @author Daniel Hannon 19484286
 *
 */
public final class GoldTierCustomer extends Customer {
	
	/**
	 * A constructor for creating Gold tier customers.
	 * @param firstName a String representing the first name of the customer
	 * @param lastName a String representing the surname of the customer
	 */
	public GoldTierCustomer(String firstName, String lastName) {
		super(firstName, lastName);
	}
	public GoldTierCustomer(String firstname, String lastName, LocalDate registerDate) throws InvalidRegisterDateException {
		super(firstname,lastName,registerDate);
	}
	@Override
	public void calculatePointsAndAdd(int pointsToAdd) {
		super.addPoints(pointsToAdd + (int)(pointsToAdd*(30/100.0f)));
	}
	
	@Override
	public String toString() {
		return super.toString() + "\nTier: Gold";
	}
}
