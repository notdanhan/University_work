import java.util.ArrayList;

public class MyStack implements Stack {
	private ArrayList<Object> stackArray;
	private final int depth = 1000;
	private int topIndex;

	public MyStack() {
		stackArray = new ArrayList<Object>();
		topIndex = -1; //Top is always going to be the index of the top value
	}

	public void push(Object n) {
		if (!this.isFull()) {
			stackArray.add(n);
			topIndex++;
		}
	}

	public Object pop() {
		if (topIndex > -1) {
			Object temp = stackArray.get(topIndex);
			stackArray.remove(topIndex);
			topIndex--;
			return temp;
		} else {
			return null;
		}
	}

	// others
	public Object top() {
		/*Get value of top without removing it*/
		if (topIndex > -1) {
			return stackArray.get(topIndex);
		} else {
			return null;
		}
	}
	public boolean isEmpty() {
		return (topIndex == -1) ? true : false;
	}
	public boolean isFull() {
		return (topIndex > 1000) ? true : false;
	}
}
