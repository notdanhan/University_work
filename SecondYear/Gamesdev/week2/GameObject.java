import java.awt.*;
import java.awt.geom.Rectangle2D;

public class GameObject {

	//Member Data
	private double x,y;
	private Color c;

	public GameObject() {
		//Set Location
		x = Math.random() * 550;
		y = Math.random() * 550;

		//Create Color
		int r = (int)(Math.random()*256);
		int g = (int)(Math.random()*256);
		int b = (int)(Math.random()*256);
		c = new Color(r,g,b);
	}

	public void move() {
		switch((int)(Math.random() * 4)) {
			case 0:
				x+=5;
				break;
			case 1:
				x -=5;
				break;
			case 2:
				y+=5;
				break;
			case 3:
				y-=5;
				break;
			default:
				break;
		}
	}

	public void paint(Graphics g) {
		g.setColor(c);
		g.fillRect((int)x,(int)y,50,50);
	}
}
