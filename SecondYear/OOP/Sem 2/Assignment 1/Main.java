import java.util.Scanner;
import java.util.Date;

public class Main {
	public static void main(String[] args) {
		String sequence = "";
		String inpstring = "";
		//Reduce invocations of String.substring() so it runs faster :)
		String currString = "";
		Scanner input = new Scanner(System.in);
		int position = 0;
		System.out.println("Type the alphabet in order (hit enter between letters)");
		System.out.print("Forwards or backwards (f/b)? ");
		while(true) {
			// Take string in and force it to be lowercase
			inpstring = input.nextLine().toLowerCase();
			if(inpstring.equals("f")) {
				sequence = "abcdefghijklmnopqrstuvwxyz";
				break;
			} else if(inpstring.equals("b")) {
				sequence = "zyxwvutsrqponmlkjihgfedcba";
				break;
			}
			System.out.println("Invalid. You must type 'f' or 'b' to start.");
		}
		Date date = new Date();
		long totalTime = date.getTime();
		currString = sequence.substring(0,1);
		while(position != sequence.length()) {
			inpstring = input.nextLine().toLowerCase();
			//Java substrings need (startIndex,endIndex) rather than (startIndex,length) for some reason
			//I use the equals method as it deals with both length and the value at position zero
			if(inpstring.equals(currString)) {
				if(position != sequence.length() - 1) {
					System.out.print("["+currString + ": Correct now type: ");
					currString = sequence.substring(position+1,position+2);
					System.out.print(currString + "]\n");
				} else {
					System.out.println("[Correct! Well Done!]");
				}
				position++;
			}
		}
		date = new Date();
		totalTime =  date.getTime()- totalTime;
		System.out.print("Time taken: " + ((float)totalTime/1000) + " seconds\n");
		//Close Scanner
		input.close();
	}
}
