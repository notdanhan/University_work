import java.util.Stack;
import java.util.LinkedList;
import java.util.Iterator;
import java.awt.Graphics;
import java.awt.Image;

public class BadGuy {

	Image myImage;
	int x=0,y=0;
	boolean hasPath=false;
	Stack<Node> path = new Stack<Node>();

	public BadGuy( Image i ) {
		myImage=i;
		x = 30;
		y = 10;
	}

	public void reCalcPath(boolean map[][],int targx, int targy) {
		// TO DO: calculate A* path to targx,targy, taking account of walls defined in map[][]
		LinkedList<Node> openList = new LinkedList<Node>();
		LinkedList<Node> closedList = new LinkedList<Node>();
		Node nodeList[][] = new Node[40][40];
		Node currentNode = null;
		Node nextNode = null;
		Node temp = null;
		path = new Stack<Node>();
		hasPath = false;
		for(int i = 0; i < 40; i++) {
			for(int j = 0; j < 40; j++) {
				nodeList[i][j] = new Node(i,j);
			}
		}
		currentNode = nodeList[this.x][this.y];
		openList.add(currentNode);
		while(!hasPath && !openList.isEmpty()) {
			currentNode = openList.getFirst();
			Iterator<Node> nodeit = openList.iterator();
			while(nodeit.hasNext()) {
				temp = nodeit.next();
				if(temp.f < currentNode.f) {
					currentNode = temp;
				}
			}
			closedList.add(currentNode);
			openList.remove(currentNode);
			if(currentNode.x != targx || currentNode.y != targy) {
				for(int i = -1; i < 2; i++) {
					for(int j = -1; j < 2; j++) {
						int g = 0;
						//Edge Case Testing
						if((i == 0 && j == 0)||(currentNode.x + i < 0 || currentNode.x + i > 39)||(currentNode.y + j < 0 || currentNode.y + j > 39)) {
							continue;
						}
						//Check can you cut the corner
						if((i<0||i>0)&&(j<0||j>0)) {
							g = 14;
							if(map[currentNode.x][currentNode.y + j] || map[currentNode.x + i][currentNode.y]) {
								continue;
							}
						} else {
							g = 10;
						}
						if(map[currentNode.x + i][currentNode.y + j]) {
							closedList.add(nodeList[currentNode.x+i][currentNode.y + j]);
							continue;
						}
						nextNode = nodeList[currentNode.x + i][currentNode.y + j];
						if(!closedList.contains(nextNode)) {
							if(!openList.contains(nextNode)) {
								nextNode.calculateF(currentNode,targx,targy);
								openList.add(nextNode);
								//Overwrite path if it takes longer had you taken another route
							} else if (currentNode.g + g < nextNode.g) {
								nextNode.calculateF(currentNode,targx,targy);
							}
						}
					}
				}
			} else {
				hasPath = true;
				nextNode = currentNode.parent;
				path.push(currentNode);
				while(nextNode != null) {
					currentNode = nextNode;
					path.push(currentNode);
					nextNode = currentNode.parent;
				}
			}
		}
	}

	public void move(boolean map[][],int targx, int targy) {
		this.reCalcPath(map,targx,targy);
		if (hasPath) {
			try{
				//Need to pop twice as it stores the current position in stack position zero
				Node temp = path.pop();
				temp = path.pop();
				x = temp.x;
				y = temp.y;
			} catch(Exception e) {
				System.out.println("Done!");
			}
		}
		else {
			// no path known, so just do a dumb 'run towards' behaviour
			int newx=x, newy=y;
			if (targx<x)
				newx--;
			else if (targx>x)
				newx++;
			if (targy<y)
				newy--;
			else if (targy>y)
				newy++;
			if (!map[newx][newy]) {
				x=newx;
				y=newy;
			}
		}
	}

	public void paint(Graphics g) {
		g.drawImage(myImage, x*20, y*20, null);
	}
}
