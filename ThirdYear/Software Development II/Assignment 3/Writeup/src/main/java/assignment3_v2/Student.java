package assignment3_v2;

public class Student {
	private int id;
	private String fName;
	private String lName;
	private String email;
	
	public int getId() {
		return this.id;
	}
	
	public String getFirstName() {
		return this.fName;
	}
	
	public String getLastName() {
		return this.lName;
	}
	
	public String getEmailAddress() {
		return this.email;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setFirstName(String fname) {
		this.fName = fname;
	}
	
	public void setLastName(String name) {
		this.lName = name;
	}
	
	public void setEmailAddress(String email) {
		this.email = email;
	}
}
