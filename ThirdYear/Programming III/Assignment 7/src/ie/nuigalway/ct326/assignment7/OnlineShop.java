package ie.nuigalway.ct326.assignment7;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.concurrent.LinkedBlockingQueue;

public class OnlineShop {
	public static void main(String[] args) {
		BufferedReader in = null;
		StringTokenizer st;
		String input;
		String tempStr;
		Order tempOrder;
		LinkedBlockingQueue<Order> lbq_in = new LinkedBlockingQueue<>();
		LinkedBlockingQueue<Order> lbq_out = new LinkedBlockingQueue<>();
		int tempNum = 0;
		try {
			in = new BufferedReader(new FileReader("orderList.txt"));
			while((input=in.readLine())!=null) {
				st = new StringTokenizer(input);
				tempNum = Integer.parseInt(st.nextToken());
				tempStr = st.nextToken();
				if (tempStr.equals("Fan")) {
					tempOrder = new Order(tempNum,OrderTypes.MUG);
				} else if (tempStr.equals("Anniversary")) {
					tempOrder = new Order(tempNum, OrderTypes.HOODIE);
				} else {
					tempOrder = new Order(tempNum,OrderTypes.TSHIRT);
				}
				lbq_in.add(tempOrder);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
