public class Address {
	private String HouseNumber;
	private String StreetName;
	private String City;
	/*Set to string to allow for British and Irish Postcodes*/
	private String ZipCode; 
	private String Country;

	Address(String StreetName, String City, String ZipCode, String Country) {
		this.HouseNumber = "";
		this.StreetName = StreetName;
		this.City = City;
		this.ZipCode = ZipCode;
		this.Country = Country;
	}

	Address(String HouseNumber, String StreetName, String City, String ZipCode, String Country) {
		this.HouseNumber = HouseNumber;
		this.City = City;
		this.StreetName = StreetName;
		this.ZipCode = ZipCode;
		this.Country = Country;
	}
	
	public String stringify() {
		String output = "";
		if (HouseNumber.length()!= 0) {
			output+=HouseNumber+"\n";
		}
		output+=StreetName+"\n";
		output+=City+"\n";
		output+=ZipCode+"\n";
		output+=Country+"\n";
		return output;
	}

	public String getHouseNumber() {
		return this.HouseNumber;
	}

	public String getStreetName() {
		return this.StreetName;
	}

	public String getCity() {
		return this.City;
	}

	public String getZipCode() {
		return this.ZipCode;
	}

	public String getCountry() {
		return this.Country;
	}

	public void setHouseNumber(String HouseNumber) {
		this.HouseNumber = HouseNumber;
	}

	public void setStreetName(String StreetName) {
		this.StreetName = StreetName;
	}

	public void setCity(String City) {
		this.City = City;
	}

	public void setZipCode(String Zip) {
		this.ZipCode = Zip;
	}

	public void setCountry(String Country) {
		this.Country = Country;
	}

}