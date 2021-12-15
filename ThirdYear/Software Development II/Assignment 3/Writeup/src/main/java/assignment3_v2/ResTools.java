package assignment3_v2;

import java.util.HashMap;
/**
 * ResTools.java
 * I realized there was some boilerplate I could mitigate by making a class with a static method
 * to parse query strings
 * @version 1
 * @author Daniel Hannon
 *
 */
public class ResTools {
	public static HashMap<String,String> parseQueryString(String s) {
		if(s == null) {
			return new HashMap<>();
		}
		String[] args = s.split("&");
		HashMap<String,String> res = new HashMap<>();
		for(int i = 0; i < args.length;i++) {
			try {
				String[] temp = args[i].split("=");
				res.put(temp[0], temp[1]);
			} catch(ArrayIndexOutOfBoundsException e) {
				continue;
			}
		}
		return res;
	}
}
