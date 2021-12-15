package ie.nuigalway.ct326.assignment5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
/**
 * AccountLoader.java
 * @author Daniel Hannon (19484286)
 */
public class AccountLoader {
	public static void main(String[] args) {
		ArrayList<UserAccount> users = new ArrayList<>();
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader("users.csv"));
			String line;
			while((line = in.readLine())!=null) {
				//This is surrounded in a try catch to deal with any and all possible parsing issues in one fell swoop
				//Uses StringTokenizer because it's easy :)
				try {
					StringTokenizer st = new StringTokenizer(line, ",");
					long UserID = Long.parseLong(st.nextToken());
					String name = st.nextToken();
					String email = st.nextToken();
					users.add(new UserAccount(UserID,name,email));
				} catch(Exception err) {
					System.out.println("Invalid Line! "+line+"\n"+err.getMessage());
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch (IOException err) {
					err.printStackTrace();
				}
			}
		}
		for(UserAccount user:users) {
			System.out.println(user);
		}
		System.out.println("Sorts:");
		//Uses sort defined in UserAccount.compareTo(Object obj)
		System.out.println("\n1. Natural Order\n");
		Collections.sort(users);
		for(UserAccount user:users) {
			System.out.println(user);
		}
		//Uses anonymous inner function as specified by assignment
		System.out.println("\n2. Reverse Natural Order\n");
		Collections.sort(users, new Comparator<>() {
			@Override
			public int compare(UserAccount o1, UserAccount o2) {
				return o2.compareTo(o1);
			}
		});
		for (UserAccount user:users) {
			System.out.println(user);
		}
		//Sort by IDs
		System.out.println("\n3. By UserID\n");
		Collections.sort(users,(o1,o2)->{
			if(!o1.equals(o2)) {
				if(o1.getUserID() < o2.getUserID()) {
					return -1;
				}
				return 1;
			}
			return 0;
		});
		for(UserAccount user:users) {
			System.out.println(user);
		}
		//Natural Sort needed for the binary search
		System.out.println("Performing Natural Order sort");
		Collections.sort(users);
		System.out.println("Searching for \"Enya Brennan\" ");
		int val = Collections.binarySearch(users,new UserAccount(32475598,"Enya Brennan","info@enya.ie"));
		if(val >= 0) {
			System.out.println("Exists in list at position: "+val);
		}
		System.out.println("Creating a map");
		Map<UserAccount, ArrayList<Workspace>> userWorkspaceMap = new HashMap<>();
		for(UserAccount user:users) {
			userWorkspaceMap.put(user,new ArrayList<>());
		}
		Workspace workspace = new Workspace("Cool Workspace","A Cool Place to work",users.get(0));
		workspace.addCollaborator(users.get(4));
		workspace.addCollaborator(users.get(7));
		userWorkspaceMap.get(users.get(0)).add(workspace);
		System.out.println("Retrieving data and printing");
		for(Workspace wkspc:userWorkspaceMap.get(users.get(0))) {
			System.out.println(wkspc);
		}
	}
}
