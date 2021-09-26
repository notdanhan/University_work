public class TreeNode<T> implements BinaryNodeInterface<T> {
	private T data;
	private TreeNode<T> left;
	private TreeNode<T> right;

	public TreeNode(T inputData) {
		this.data = inputData;
		this.left = null;
		this.right = null;
	}
	public TreeNode(T inputData, TreeNode<T> leftNode, TreeNode<T> rightNode) {
		this.data = inputData;
		this.left = leftNode;
		this.right = rightNode;
	}

	public T getData() {
		return this.data;
	}

	public void setData(T newData) {
		this.data = newData;
	}

	public BinaryNodeInterface<T> getLeftChild() {
		return this.left;
	}

	public BinaryNodeInterface<T> getRightChild() {
		return this.right;
	}

	public void setLeftChild(BinaryNodeInterface<T> leftChild) {
		this.left = (TreeNode<T>)leftChild;
	}

	public void setRightChild(BinaryNodeInterface<T> rightChild) {
		this.right = (TreeNode<T>)rightChild;
	}

	public boolean hasLeftChild() {
		return (this.left == null) ? false : true;
	}

	public boolean hasRightChild() {
		return (this.right == null) ? false : true;
	}

	public boolean isLeaf() {
		return (this.left == null && this.right == null) ? true : false;
	}

	public int getNumberOfNodes() {
		if(this.left == null && this.right == null) {
			return 0;
		} else {
			int total = 0;
			if(this.left!=null) {
				total+=1;
				total += this.left.getNumberOfNodes();
			}
			if(this.right!=null) {
				total+=1;
				total += this.right.getNumberOfNodes();
			}
			return total;
		}
	}

	public int getHeight() {
		//Returns the largest height
		int templeft = 0;
		int tempright = 0;
		if(this.left!= null) {
			templeft = 1 + this.left.getHeight();
		}
		if(this.right != null) {
			tempright = 1 + this.right.getHeight();
		}
		if(templeft > tempright) {
			return templeft;
		} else {
			return tempright;
		}
	}

	public BinaryNodeInterface<T> copy() {
		TreeNode<T> leftCopy = null;
		TreeNode<T> rightCopy = null;
		if(this.left != null) {
			leftCopy = (TreeNode<T>)left.copy();
		}
		if(this.right != null) {
			rightCopy = (TreeNode<T>)right.copy();
		}
		return new TreeNode<T>(this.data,leftCopy,rightCopy);
	}

	@Override
	public String toString() {
		String leftString = "#";
		String rightString = "#";
		if(this.left != null) {
			leftString = this.left.toString();
		}
		if(this.right != null) {
			rightString = this.right.toString();
		}
		return this.data +","+leftString+","+rightString;
	}
}
