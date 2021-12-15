package com.ct326.nuigalway.ie;
import java.io.*;
import java.util.ArrayList;
/**
 * Deserialization Test
 * @author Daniel Hannon (19484286)
 */
public class ObjectDeserializationTest {
	public static void main(String[] args) {
		ArrayList<Celebrity> celebrityArrayList = new ArrayList<>();
		try {
			//Create the input streams and define the celebrity object here
			FileInputStream in = new FileInputStream("celebrities");
			ObjectInputStream s = null;
			Celebrity temp;
			try {
				s = new ObjectInputStream(in);
				while (true) {
					//Try to read in Object and add it to the array list
					temp = (Celebrity) s.readObject();
					celebrityArrayList.add(temp);
				}
			} catch(EOFException err) {
				//Do Nothing as this needs to be caught to end the search
			} catch(ClassNotFoundException err) {
				err.printStackTrace();
			} finally {
				if (s!=null) {
					s.close();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(Celebrity celebrity : celebrityArrayList) {
			//Print all the celebrities
			System.out.println(celebrity);
		}
	}
}
