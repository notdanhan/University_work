package com.nuig.cs.ct326.assignment1;
import org.joda.money.*;
import java.util.Random;
import java.time.LocalDate;
/**
 * A test class for the customer loyalty system.
 * @author Daniel Hannon 19484286
 *
 */
public class Assignment1Test {

	/**
	 * This Method returns a random (valid) date
	 * @param rng Random Number Generator needed
	 * @return LocalDate with valid date
	 */
	public static LocalDate randomJoinDate(Random rng) {
		//Somewhere between 2000 and 2020
		int year = (rng.nextInt() % 11) + 2010;
		int month = (Math.abs(rng.nextInt() % 12)) + 1;
		int day = 0;
		switch(month) {
			//september april june november
			case 9:
			case 4:
			case 6:
			case 11:
				day = (Math.abs(rng.nextInt()%30)) + 1;
				break;
			case 2:
				//February
				//Leap Year Check
				//As 2000 (The Lowest possible year here) is a leap year I do not need to check if the year
				//divides by 400 or 100
				if(year % 4 == 0) {
					day = (Math.abs(rng.nextInt() % 29)) + 1;
				} else {
					day = (Math.abs(rng.nextInt() % 28)) + 1;
				}
				break;
			default:
				day = (Math.abs(rng.nextInt() % 31)) + 1;
				break;
		}
		return LocalDate.of(year,month,day);
	}

	public static void main(String[] args) {
		//RNG is seeded for reproducability
		Random myRand = new Random(123456789);
		//edge case tests
		Customer c1;
		try {
			System.out.println("Testing Invalid Register Date");
			c1 = new SilverTierCustomer("Jeff","Bezos", LocalDate.of(2121, 02, 03));
			System.out.println(c1);
		} catch ( InvalidRegisterDateException e ) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			try {
				Thread.sleep(10);
			} catch (InterruptedException err) {
				e.printStackTrace();
			}
		}
		try {
			c1 = new SilverTierCustomer("Jeff", "Bezos", LocalDate.of(2002, 02, 03));
			System.out.println("\nTesting Negative Purchase Amounts");
			for (int i = 0; i < 5; i++) {
				//Force Generate Negative values
				float myAmount = (float)myRand.nextInt()/10000000f;
				myAmount = (myAmount > 0) ? myAmount *-1 : myAmount;
				try {
					String moneyStr = String.format("EUR %.2f",myAmount);
					System.out.println("\nTest: " + i + " Amount: " + moneyStr);
					c1.makePurchase(Money.parse(moneyStr));
				} catch(NegativePurchaseAmountException e){
					System.out.println(e.getMessage());
					e.printStackTrace();
					try {
						//To Make Sure the stack trace is printed below the error message
						//as System.err seems to take longer than System.out
						Thread.sleep(10);
					} catch (InterruptedException err) {
						e.printStackTrace();
					}
				}
			}
		} catch(InvalidRegisterDateException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		System.out.println("\nTesting Account Numbers (0 was taken during the negative Purchase test)");
		Customer[] myCust = new Customer[10];
		String[] fNames = {"John","Jane","Mary","Joe","Paul","Laura"};
		String[] lNames = {"Smith","Bloggs","MacMargadh","Papadopoulos","Murphy"};
		int i = 0;
		while(i < 10) {
			try {
				//Assign Random Customer Tiers to people
				switch(Math.abs(myRand.nextInt()) % 4) {
					case 0:
						myCust[i] = new BlueTierCustomer(fNames[Math.abs(myRand.nextInt()%6)],lNames[Math.abs(myRand.nextInt()%5)],randomJoinDate(myRand));
						break;
					case 1:
						myCust[i] = new SilverTierCustomer(fNames[Math.abs(myRand.nextInt()%6)],lNames[Math.abs(myRand.nextInt()%5)],randomJoinDate(myRand));
						break;
					case 2:
						myCust[i] = new GoldTierCustomer(fNames[Math.abs(myRand.nextInt()%6)],lNames[Math.abs(myRand.nextInt()%5)],randomJoinDate(myRand));
						break;
					case 3:
						myCust[i] = new PlatinumTierCustomer(fNames[Math.abs(myRand.nextInt()%6)],lNames[Math.abs(myRand.nextInt()%5)],randomJoinDate(myRand));
						break;
				}
				//Make Random (Valid) Purchase on account
				float purchaseAmount = Math.abs((float)myRand.nextInt()/1000000f);
				String purchaseString = String.format("EUR %.2f",purchaseAmount);
				try {
					myCust[i].makePurchase(Money.parse(purchaseString));
				} catch (NegativePurchaseAmountException err) {
					System.out.println("You should never see this");
					err.printStackTrace();
				}
			} catch (InvalidRegisterDateException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				try {
					Thread.sleep(10);
				} catch(InterruptedException err) {
					err.printStackTrace();
				}
				continue;
			}
			i++;
		}
		for(int j = 0; j< 10; j++) {
			System.out.println(myCust[j]);
		}

		System.out.println("\nTesting Anniversary");
		try {
			LocalDate today = LocalDate.now();
			LocalDate registerDate = today.minusYears(1);
			Customer c2 = new GoldTierCustomer("John","Smith",registerDate);
			System.out.println(c2);
			System.out.println("\nChecking for Anniversary");
			c2.checkAnniversary();
			System.out.println(c2);
		} catch (InvalidRegisterDateException e) {
			e.printStackTrace();
		}
	}
}
