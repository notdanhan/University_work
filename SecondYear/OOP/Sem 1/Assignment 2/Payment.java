import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;

public class Payment {
	private long cardNumber;
	private Date date;
	private String cardType;
	private Boolean isValid;
	private Order order;

	Payment(Order order, String cardType, long cardNumber, String Date) {
		this.order = order;
		this.cardType = cardType;
		this.cardNumber = cardNumber;
		this.isValid = false;
		verifyDate(Date);
	}

	private void verifyDate(String date) {
		try {
			this.date = new SimpleDateFormat("MM/yy").parse(date);
		} catch (Exception e) {
			/*Set to UNIX epoch if the date could not be processed solely 
			for validation purposes*/
			this.date = new GregorianCalendar(1970,Calendar.JANUARY,1).getTime();
		}
	}

	public void validate() {
		if(this.cardType == "Visa" || this.cardType == "MasterCard") {
			if (cardNumber >= 1000000000000000L && cardNumber <= 9999999999999999L) {
				Date today = new Date();
				if(today.compareTo(date) < 0) {
					this.isValid = true;
					return;
				}
			}
		}
		this.isValid = false;
	}

	public void Confirm() {
		if(this.isValid) {
			order.confirmOrder();
			order.sendEmail();
		}
		else {
			/*Just to check if it wasn't validated*/
			this.validate();
			if(this.isValid) {
				order.confirmOrder();
				order.sendEmail();
			}
			else {
				order.sendEmail();
			}
		}
	}

	public void setDate(String Date) {
		verifyDate(Date);
	}

	public void setCardNumber(long CardNumber) {
		this.cardNumber = CardNumber;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
}