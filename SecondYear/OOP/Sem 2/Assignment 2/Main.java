import javax.swing.JOptionPane;
import java.util.StringTokenizer;

public class Main {
	private ArrayStack stack;
	private String input;
	private String output;
	private boolean looping;
	private boolean valid;
	private int bracketsDepth = 0;
	private static char[] precidence = {'^','*','/','-','+'};

	public Main() {
		stack = new ArrayStack();
		input = "";
		looping = true;
		output = "";
	}

	public int getPriority(char value) {
		int val = -3;
		for(int i = 0; i < precidence.length; i++) {
			if(precidence[i] == value) {
				val = i;
				break;
			}
		}
		return (val + 1)/2;
	}

	public boolean validate(char value) {
		if(value == ')' || value =='(' ) {
			return true;
		}
		for(int i = 0; i < precidence.length; i++) {
			if (precidence[i] == value) {
				return true;
			}
		}
		return false;
	}

	public double evaluate(String inputString) {
		double val1 = 0;
		double val2 = 0;
		boolean prevsum = false;
		while(!stack.isEmpty()) {
			stack.pop();
		}
		StringTokenizer st = new StringTokenizer(inputString);
		while(st.hasMoreTokens()) {
			String temp = st.nextToken();
			//Check if it's an expression or a number
			if(!validate(temp.charAt(0))) {
				try {
					stack.push(Double.parseDouble(temp));
				} catch(Exception e) {
					//This executes If value is something like "." or "..." etc.
					System.out.println("Not a Number!");
					JOptionPane.showMessageDialog(null,"Not a Valid Number!\nPlease Check inputs.");
					System.out.println(e);
					//Returns so the program doesn't crash
					return Double.MAX_VALUE;
				}
			} else {
				try {
					val2 = (double)stack.pop();
					val1 = (double)stack.pop();
				} catch (Exception e) {
					System.out.println("Too many operators!");
					return Double.MAX_VALUE;
				}
				double eval = 0;
				switch(temp.charAt(0)) {
					case '+':
						eval = val1 + val2;
						break;
					case '-':
						eval = val1 - val2;
						break;
					case '/':
						eval = val1 / val2;
						break;
					case '*':
						eval = val1 * val2;
						break;
					case '^':
						eval = Math.pow(val1,val2);
						break;
				}
				prevsum = true;
				System.out.printf("%f %c %f = %f\n",val1,temp.charAt(0),val2,eval);
				stack.push(eval);
			}
		}
		return (double)stack.pop();
	}

	public void loop() {
		while(this.looping) {
			valid = true;
			output = "";
			input = JOptionPane.showInputDialog("Please input an infix expression between 3 and 20 Characters");
			System.out.println(input);
			if(input == null) {
				looping=false;
				break;
			}
			if(input.length() < 3 || input.length() > 20) {
				JOptionPane.showMessageDialog(null,"Expression is not of valid length please enter a value between 3 and 20 characters","Alert",JOptionPane.ERROR_MESSAGE);
				continue;
			}
			for(int i = 0; i < input.length(); i++) {
				char val = input.charAt(i);
				if ((val >= '0' && val <= '9') || val == '.') {
					int start = i;
					while((input.charAt(i) >= '0' && input.charAt(i) <= '9') || input.charAt(i) == '.') {
						i++;
						if (i >= input.length()) break;
					}
					output += input.substring(start,i);
					output += " ";
					i--;
				} else {
					if (stack.isEmpty()) {
						if (this.validate(val)) {
							//Ignore bracket errors
							if (val != ')') {
								stack.push(val);
							}
							/*Ignore Spaces*/
							if (val == '(') {
								bracketsDepth++;
							}
						} else if(val != ' ') {
							JOptionPane.showMessageDialog(null,"Only the following characters are valid: (,),*,+,/,-,^ and numbers 0-9","Invalid Character!",JOptionPane.ERROR_MESSAGE);
							valid=false;
							break;
						}
					} else if (!stack.isFull()) {
						/*Ignore spaces*/
						if (val == ' ') {
							continue;
						}
						if (!this.validate(val)) {
							JOptionPane.showMessageDialog(null,"Only the following characters are valid: (,),*,+,/,-,^ and numbers 0-9","Invalid Character!",JOptionPane.ERROR_MESSAGE);
							valid=false;
							break;
						} else {
							if (val == ')' && bracketsDepth >= 1) {
								char temp = (char) stack.pop();
									while(temp != '(') {
										output += String.valueOf(temp);
										output += " ";
										temp = (char) stack.pop();
									}
									bracketsDepth -= 1;
							} else {
								if((char)stack.top() == '(') {
									stack.push(val);
									bracketsDepth++;
								} else {
									if (this.getPriority((char)stack.top()) >= this.getPriority(val)) {
										stack.push(val);
									} else {
										ArrayStack tempStack = new ArrayStack();
										tempStack.push((char) stack.pop());
										while(!stack.isEmpty()) {
											if(this.getPriority((char)stack.top()) >= this.getPriority(val)) {
												stack.push(val);
												break;
											} else {
												tempStack.push(stack.pop());
											}
										}
										if (stack.isEmpty()) {
											stack.push(val);
										}
										/*Unload temp stack*/
										while(!tempStack.isEmpty()) {
											stack.push(tempStack.pop());
										}
									}
								}
							}
						}
					}
				}
			}
			//Drain Stack to output string
			while(!stack.isEmpty()) {
				char temp = (char) stack.pop();
				if (temp != ')' && temp != '(') {
					output += String.valueOf(temp);
					output += " ";
				}
			}
			System.out.println(output);
			if(valid) {
				double result = evaluate(output);
				String messageBox = "The result of the expression is:\ninfix: " + input +"\nPostfix: " + output +"\nResult: " + result;
				JOptionPane.showMessageDialog(null,messageBox);
			} else {
				continue;
			}
			/*Yes: 0 No: 1 Cancel: 2*/
			switch(JOptionPane.showConfirmDialog(null,"Would you like to run again?")) {
				case 0:
					System.out.println("Running again");
					break;
					default:
					looping = false;
					break;
			}
			//Flush stack to avoid typecasting errors in the next run through
			while(!stack.isEmpty()) {
				stack.pop();
			}
		}
	}

	public static void main(String args[]) {
		Main application = new Main();
		application.loop();
	}
}
