package ie.nuigalway.ct326.assignment5;

/**
 * UserAccount.java
 * @author Daniel Hannon(19484286)
 */
@SuppressWarnings("unused")
public class UserAccount implements Comparable<UserAccount> {
	private long userID;
	private String name;
	private String emailAddress;
	public UserAccount(long userID, String name, String emailAddress) {
		this.userID = userID;
		this.name = name;
		this.emailAddress = emailAddress;
	}

	public long getUserID() {
		return this.userID;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public String getName() {
		return name;
	}

	/**
	 * toString
	 * @return a nicely formatted string showing the data stored in the class
	 */
	@Override
	public String toString() {
		return String.format("ID:\t\t%d\nName:\t%s\nEmail:\t%s\n",userID,name,emailAddress);
	}

	/**
	 * hashCode
	 * @return the hashCode of userID
	 */
	@Override
	public int hashCode() {
		//long doesn't have a hashCode method but Long does
		return ((Long)userID).hashCode();
	}

	/**
	 * Equals
	 * @param obj an object
	 * @return true if the types match and if the userIDs match
	 */
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj instanceof UserAccount) {
			return this.userID == ((UserAccount) obj).userID;
		}
		return false;
	}

	/**
	 * compareTo
	 * @param u Another UserAccount
	 * @return value of comparing the names of the accounts
	 */
	public int compareTo(UserAccount u) {
		return this.name.compareTo(u.name);
	}
}
