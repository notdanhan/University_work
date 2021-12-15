package com.nuig.cs.ct326.assignment1;

import java.time.LocalDate;

/**
 * This class represents a customer who is on the Blue tier of the loyalty program.
 * @author Daniel Hannon 19484286
 *
 */
public final class BlueTierCustomer extends Customer {
	
	/**
	 * A constructor for creating Blue tier customers.
	 * @param firstName a String representing the first name of the customer
	 * @param lastName a String representing the surname of the customer
	 */
	public BlueTierCustomer(String firstName, String lastName) {
		super(firstName, lastName);
	}

	public BlueTierCustomer(String firstName, String lastname, LocalDate registerDate) throws InvalidRegisterDateException {
		super(firstName,lastname,registerDate);
	}

	@Override
	public void calculatePointsAndAdd(int pointsToAdd) {
		super.addPoints(pointsToAdd);
	}
	
	@Override
	public String toString() {
		return super.toString() + "\nTier: Blue";
	}
}
