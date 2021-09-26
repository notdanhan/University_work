package Assignment1;/**
 *
 * @author Michael Schukat, 2019 modified by Daniel Hannon
 */
import java.util.Date;

public class CT255_HashFunction1 {

	public static void main(String[] args) {
		int res = 0;
		int res1 = 0;
		int collisions_found = 0;

		if (args != null && args.length > 0) { // Check for <input> value
			res = hashF1(args[0]); // call hash function with <input>
			if (res < 0) { // Error
				System.out.println("Error: <input> must be 1 to 64 characters long.");
			}
			else {
				System.out.println("input = " + args[0] + " : Hash = " + res);
				Date time = new Date();
				long timeStart = time.getTime();
				System.out.println("Start searching for collisions");
				while(collisions_found < 10) {
					String test = "";
					for(int i = 0; i < 5; i++) { /*Length 5 string*/
						test+=Character.toString((char)( Math.random() * 78)+48); /*ASCII value "0" is 48
						and "~" is 126*/ 
					}
					res1 = hashF1(test);
					if(res == res1 && args[0].equals(test) == false) { /*Compares hashes and strings to 
					make sure that it is not the exact same string if there's a duplicate*/
						time = new Date();
						long currtime = time.getTime() - timeStart;
						System.out.println(currtime+"ms: Collision Found! " + test);
						collisions_found++;
					}
				}
			}
		}
		else { // No <input> 
			System.out.println("Use: CT255_HashFunction1 <Input>");
		}
	}

	private static int hashF1(String s){
		int ret = -1, i;
		int[] hashA = new int[]{1, 1, 1, 1};

		String filler, sIn;

		filler = new String("ABCDEFGHABCDEFGHABCDEFGHABCDEFGHABCDEFGHABCDEFGHABCDEFGHABCDEFGH");

		if ((s.length() > 64) || (s.length() < 1)) { // String does not have required length
			ret = -1;
		}
		else {
			sIn = s + filler; // Add characters, now have "<input>ABCDEF..."
			sIn = sIn.substring(0, 64); // // Limit string to first 64 characters
			// System.out.println(sIn); // FYI
			for (i = 0; i < sIn.length(); i++){
				char byPos = sIn.charAt(i); // get i'th character
				hashA[0] += (byPos * 17); // Note: A += B means A = A + B
				hashA[1] += (byPos * 31);
				hashA[2] += (byPos * 101);
				hashA[3] += (byPos * 79);
			}

			hashA[0] %= 255;  // % is the modulus operation, i.e. division with rest
			hashA[1] %= 255;
			hashA[2] %= 255;
			hashA[3] %= 255;

			ret = hashA[0] + (hashA[1] * 256) + (hashA[2] * 256 * 256) + (hashA[3] * 256 * 256 * 256);
			if (ret < 0) ret *= -1;
		}
		return ret;
	}
}