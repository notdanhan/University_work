public class Node {
	public int x,y,f,g,h = 0;
	public Node parent = null;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void calculateF(Node parentNode, int targetX, int targetY) {
		this.parent = parentNode;
		if(this.x != this.parent.x && this.y != this.parent.y) {
			this.g = this.parent.g + 14;
		} else {
			this.g = this.parent.g + 10;
		}
		this.h = Math.abs(targetX - this.x) + Math.abs(targetY - this.y);
		this.f = g + h;
	}
}
