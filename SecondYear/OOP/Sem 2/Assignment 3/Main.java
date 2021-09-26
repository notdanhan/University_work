import java.lang.Math;

public class Main {
	//private static long Method1Steps,Method2Steps,Method3Steps,Method4Steps = 0;
	private static long Method1Time ,Method2Time, Method3Time, Method4Time = 0;

	public static boolean checkPallindromeLinearStringReverse(String val) {
		String valReversed = ""; //Assignment takes 1 Step
		//Method1Steps++;
		//Initialization, method call, subtraction, comparison
		//Method1Steps+=4;
		for(int i = val.length() - 1; i > -1; i--) {
			//Comparison, subtraction
			//Method1Steps+=2;
			valReversed += val.substring(i,i+1);
			//Addition Call of Method
			//Method1Steps+=2;
		}
		//Check equals return true/false
		//Method1Steps +=2;
		return val.equals(valReversed);
	}

	public static boolean checkPallindromeCompareFirstLast(String val) {
		int i = 0;
		int j = val.length() - 1;
		if (j == 0) {
			return true;
		}
		//Two Assignments a Method Call and a subtraction
		//Method2Steps+=3;
		while(i < j) {
			//Comparison
			//Method2Steps++;
			if(val.charAt(i) != val.charAt(j)) {
				//Valid If/else and return
				//Method2Steps+=4;
				return false;
			}
			//Two Method calls and a comparison
			//Method2Steps+=2;
			i++;
			j--;
			//Two Mathematical operations
			//Method2Steps+=2;
		}
		//return statement
		//Method2Steps++;
		return true;
	}

	public static boolean checkPallindromeStackAndQueue(String val) {
		ArrayStack stack = new ArrayStack(1000000);
		ArrayQueue queue = new ArrayQueue(1000000);
		//Two Initialization calls and two constructor calls
		//Method3Steps+=3;
		//Initialization, comparison, method call
		//Method3Steps+=3;
		for(int i = 0; i < val.length(); i++) {
			//Comparison method call, subtraction
			//Method3Steps+=3;
			stack.push(val.charAt(i));
			queue.enqueue(val.charAt(i));
			// Four method invocations
			//Method3Steps+=4;
		}
		while(!stack.isEmpty()) {
			//Method call and invert
			//Method3Steps+=2;
			if ((char)queue.dequeue() != (char)stack.pop()) {
				//Two Method calls, two typecasts, comparison, return value
				//Method3Steps+=6;
				return false;
			}
			//Two Method Calls, Two Typecasts and a comparison
			//Method3Steps+=5;
		}
		//Comparison while loop
		//Method3Steps+=2;
		//Return
		//Method3Steps+=1;
		return true;
	}

	public static boolean checkPallindromeRecursiveStringReverse(String val) {
		String valReversed = recursiveStringReverse(val);
		//Method4Steps+=2; //Initialization + method call
		//Method Call + return value
		//Method4Steps+=2;
		return val.equals(valReversed);
	}

	public static String recursiveStringReverse(String val) {
		if(val.length() == 1) {
			//If/else and Return
			//Method4Steps+=2;
			return val;
		}
		//If/else 3 method calls addition and Return
		//Method4Steps+=5;
		return recursiveStringReverse(val.substring(1)) + val.substring(0,1);
	}

	public static String intToBinaryString(int val) {
		/*
			Basically this gets the index of the highest power of two
			then the next, and so on and pads zeroes in between to make
			it a binary representation of a number
		*/
		if(val == 0) {
			return "0";
		}
		String output = "";
		int curr = (int)(Math.log(val)/Math.log(2));
		output += "1";
		val -= Math.pow(2,curr);
		int prev = 0;
		while(val != 0) {
			prev = curr;
			curr = (int)(Math.log(val)/Math.log(2));
			while(prev > curr+1) {
				output+="0";
				prev--;
			}
			output+="1";
			val -= Math.pow(2,curr);
		}
		/*Adds trailing zeroes*/
		if(curr > 0) {
			while(curr > 0) {
				output+="0";
				curr--;
			}
		}
		return output;
	}

	/*This was used to generate the data for the graph values*/
	public static String generateBinaryStringXLength(int val) {
		String inpt = "";
		for(; val > 0; val--) {
			if(Math.random()>Math.random()) {
				inpt+="1";
			} else {
				inpt+="0";
			}
		}
		return inpt;
	}
	public static void main(String[] args) {
		System.out.println("length\tmethod1\tmethod2\tmethod3\tmethod4");
		for(int i = 1; i < 1000000; i++) {
			String binaryString = intToBinaryString(i);
			String numstring = String.valueOf(i);
			//String binaryString = intToBinaryString(i);
			//String numstring = Integer.toString(num);
			//Method1Time = System.nanoTime();
			//boolean valid1 = checkPallindromeStackAndQueue(binaryString);
			//Method1Time = System.nanoTime() - Method1Time;
			//Method2Time = System.nanoTime();
			boolean valid2 = checkPallindromeCompareFirstLast(binaryString);
			//Method2Time = System.nanoTime() - Method2Time;
			//Method3Time = System.nanoTime();
			//boolean valid3 = checkPallindromeLinearStringReverse(binaryString);
			//Method3Time = System.nanoTime() - Method3Time;
			//Method4Time = System.nanoTime();
			//boolean valid4 = checkPallindromeRecursiveStringReverse(binaryString);
			//Method4Time = System.nanoTime() - Method4Time;
			/*if(valid1&&valid2&&valid3&&valid4) {
				System.out.println(binaryString + "Is a pallindrome");
			}
			System.out.println("Times: "+Method1Time+"\t"+Method2Time+"\t"+Method3Time+"\t"+Method4Time);*/
			boolean valid_num_string = checkPallindromeCompareFirstLast(numstring);
			if(valid_num_string && valid2) {
				System.out.println(numstring + " & " + binaryString + " Are Both Pallindromes");
			}
			/*if(valid1) {
				System.out.println(binaryString + " is a pallindrome");
			}
			if(valid_num_string) {
				System.out.println(numstring + " is a pallindrome");
			}
			System.out.println(binaryString.length() + "\t"+Method1Steps+"\t"+Method2Steps+"\t"+Method3Steps+"\t"+Method4Steps);
			Method1Steps = 0;
			Method2Steps = 0;
			Method3Steps = 0;
			Method4Steps = 0;*/
		}
	}
}
