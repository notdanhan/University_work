package com.ct326.nuigalway.ie;

import java.time.LocalDate;

/**
 * Award Class
 * @author Daniel Hannon (19484286)
 */
@SuppressWarnings("unused")
public class Award {
	private String awardName;
	private String awardOrganization;
	private LocalDate awardDate;

	/**
	 * Constructor
	 * @param awardName the name of the award received
	 * @param awardOrganization the organization behind the award
	 * @param awardDate the date of issue
	 */
	public Award(String awardName,String awardOrganization, LocalDate awardDate) {
		this.awardName = awardName;
		this.awardOrganization = awardOrganization;
		this.awardDate = awardDate;
	}

	public String getAwardName() {
		return awardName;
	}

	public String getAwardOrganization() {
		return awardOrganization;
	}

	public LocalDate getAwardDate() {
		return awardDate;
	}

	public void setAwardName(String awardName) {this.awardName = awardName;}

	public void setAwardOrganization(String awardOrganization) {this.awardOrganization = awardOrganization;}

	public void setAwardDate(LocalDate awardDate) {this.awardDate = awardDate;}

	/**
	 * toString
	 * @return a formatted string for use in conjunction with Celebrity.toString()
	 */
	@Override
	public String toString() {
		return String.format("\tAward: %s\n\tOrganization: %s\n\tAward Date: %s",this.awardName,this.awardOrganization,this.awardDate.toString());
	}
}
