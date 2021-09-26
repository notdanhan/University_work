public class Email {
	/*Test class for email as I do not have to implement actual email*/
	private String emailAddress;
	private String fname;
	public Email(String emailAddress, String fname) {
		this.emailAddress = emailAddress;
		this.fname = fname;
	}
	public void SendSuccess(long orderno, String content, float tcost, Address address, Address bAddress) {
		try {
			String temp1 = address.stringify();
			String temp2 = bAddress.stringify();
			System.out.println("Email to "+ emailAddress + " with content:\n"+"Hey "+fname+", Your order: "+orderno+" With content:\n" + content + "\nTotalling: "+tcost+" has been Sent to "+ temp1 +"With billing details sent to " + temp2 + "\nThank You for your Custom!");
		} catch (Exception e) {
			System.out.println("Something went wrong!");
			try {
				System.out.println(content);
				System.out.println(tcost);
				System.out.println(address.stringify());
				System.out.println(bAddress.stringify());
			}catch (Exception a) {
				System.out.println("Whoops!");
			}
		}
	}

	public void SendFailure() {
		System.out.println("Email sent to: " + emailAddress+ "\nHey "+fname+",\nWe regret to inform you that your transaction was not processed");
	}
}