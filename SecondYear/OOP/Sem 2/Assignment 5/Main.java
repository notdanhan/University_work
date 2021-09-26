import java.util.Scanner;
import java.util.Stack;
import java.io.*;

public class Main {
	public static TreeNode<String> createTree() {
		//Create a demo question tree
		TreeNode<String> answer = new TreeNode<String>("Penguin");
		TreeNode<String> answer2 = new TreeNode<String>("Parrot");
		TreeNode<String> question1 = new TreeNode<String>("Can it fly?", answer2, answer);
		answer = new TreeNode<String>("Lion");
		answer2 = new TreeNode<String>("Dog");
		TreeNode<String> question2 = new TreeNode<String>("Does it live in the Jungle? ",answer,answer2);
		TreeNode<String>question3 = new TreeNode<String>("Is it a bird?", question1, question2);

		answer = new TreeNode<String>("Shark");
		answer2 = new TreeNode<String>("Crocodile");
		question1 = new TreeNode<String>("Does it have Scales?",answer2,answer);
		question2 = new TreeNode<String>("Is it a mammal?",question3,question1);
		return question2;
	}

	public static TreeNode<String> loadTree() {
		String myBinaryTree = "";
		File myFile = new File("saveFile.txt");
		TreeNode<String> output = null;
		Stack<TreeNode<String>> myStack = new Stack<TreeNode<String>>();
		try {
			Scanner fileScanner = new Scanner(myFile);
			while(fileScanner.hasNextLine()) {
				myBinaryTree += fileScanner.nextLine();
			}
			fileScanner.close();
			if(myBinaryTree.length() == 0) {
				System.out.println("Empty file returning default file");
				return createTree();
			}
			String[] myArray = myBinaryTree.split(",");
			for(int i = myArray.length - 1; i >= 0; i--) {
				//Since each node either has two children or no children this works
				if(myArray[i].equals("#") && myArray[i-1].equals("#")) {
					//If both children are null it creates a new node and pushes it to the stack
					myStack.push(new TreeNode<String>(myArray[i-2],null,null));
					i-=2;
				} else {
					//Create a question and it's answers using two child nodes
					TreeNode<String> left  = myStack.pop();
					TreeNode<String> right = myStack.pop();
					myStack.push(new TreeNode<String>(myArray[i],left,right));
				}
			}
		} catch(IOException e) {
			System.out.println("No Save file exists! returning the default tree");
			return createTree();
		}
		//Pop top of stack as it is the first question
		output = myStack.pop();
		return output;
	}

	public static void saveTree(TreeNode<String> myTree) {
		//Create File Writer
		File myFile = new File("saveFile.txt");
		FileWriter fileOutput;
		try {
			myFile.createNewFile();
			fileOutput = new FileWriter(myFile);
			fileOutput.write(myTree.toString());
			fileOutput.close();
		} catch(IOException e) {
			System.out.print(e);
		}
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String myGuess = "";
		boolean isRunning = true;
		TreeNode<String> questionTree = createTree();
		TreeNode<String> currentNode = questionTree;
		TreeNode<String> previous = null;
		int path = 0;
		while(isRunning) {
			while(!currentNode.isLeaf()) {
				System.out.print(currentNode.getData()+" ");
				myGuess = input.nextLine().toLowerCase();
				if(myGuess.equals("yes") || myGuess.equals("y")) {
					previous = currentNode;
					currentNode = (TreeNode<String>)currentNode.getLeftChild();
					path = 1;
				} else if(myGuess.equals("no") || myGuess.equals("n")){
					previous = currentNode;
					currentNode = (TreeNode<String>)currentNode.getRightChild();
					path = 2;
				}
			}
			while(true) {
				System.out.print("Is it a "+currentNode.getData() +"? ");
				myGuess = input.nextLine().toLowerCase();
				if(myGuess.equals("yes")||myGuess.equals("y")) {
					System.out.println("I won!");
					break;
				} else if (myGuess.equals("no") || myGuess.equals("n")) {
					System.out.print("I Don't know, what's the correct answer? ");
					String answer = input.nextLine().replace("#","").replace(",","");
					TreeNode<String> newAnswerNode = new TreeNode<String>(answer);
					System.out.print("Distinguishing Question: ");
					//Input sanitisation for saving the tree later
					String newQuestion = input.nextLine().replace("#","").replace(",","");
					while(true) {
						System.out.print("Correct Answer for " + currentNode.getData() + ": ");
						String option = input.nextLine().toLowerCase();
						if(option.equals("yes") || option.equals("y")) {
							TreeNode<String> newQuestionNode = new TreeNode<String>(newQuestion,currentNode,newAnswerNode);
							if(previous != null) {
								if(path == 1) {
									//Yes
									previous.setLeftChild(newQuestionNode);
								} else {
									previous.setRightChild(newQuestionNode);
								}
							} else {
								questionTree = newQuestionNode;
							}
							break;
						} else if(option.equals("no") || option.equals("n")) {
							TreeNode<String> newQuestionNode = new TreeNode<String>(newQuestion,newAnswerNode,currentNode);
							if(previous != null) {
								if(path == 1) {
									//Yes
									previous.setLeftChild(newQuestionNode);
								} else {
									//No
									previous.setRightChild(newQuestionNode);
								}
							} else {
								//Accounting for a tree with a single leaf and nothing else
								questionTree = newQuestionNode;
							}
							break;
						} else {
							System.out.println("Please Input a valid response!");
						}
					}
					break;
				} else {
					System.out.println("Please enter a valid response!");
				}
			}
			while(true) {
				System.out.println("Would you like to:\n a) Play Again?\n b) Save the Tree?\n c) Load another Tree?\n d) Quit?");
				myGuess = input.nextLine().toLowerCase();
				if(myGuess.equals("a")) {
					//Play Again
					previous = null;
					currentNode = questionTree;
					path = 0;
					break;
				} else if(myGuess.equals("b")) {
					saveTree(questionTree);
					System.out.println("Tree Saved!");
				} else if(myGuess.equals("c")) {
					System.out.println("Loading Tree");
					questionTree = loadTree();

				} else if(myGuess.equals("d")) {
					isRunning = false;
					break;
				}
			}
		}
		input.close();
	}
}
