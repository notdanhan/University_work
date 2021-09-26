import java.awt.*;

public class Sprite2D {

	//member data
	private double x,y;
	private double xSpeed = 0;
	private Image myImage;

	//Constructor
	public Sprite2D(Image i) {
		x = Math.random() * 525;
		y = Math.random() * 545;
		xSpeed = Math.random() * 10;
		myImage = i;
	}

	//public Interface
	public void moveEnemy() {
		switch((int)(Math.random() * 4)) {
			case 0:
				x += xSpeed;
				break;
			case 1:
				x -= xSpeed;
				break;
			case 2:
				y += xSpeed;
				break;
			case 3:
				y -= xSpeed;
				break;
		}
		/*Stop sprites from leaving the canvas by rubberbanding*/
		if (x < 0) {
			x = 0;
		} else if (x > 525) {
			x = 525;
		}

		if (y < 0) {
			y = 0;
		} else if (y > 545) {
			y = 545;
		}
	}

	public void setPosition(double xx, double yy) {
		x = xx;
		y = yy;
	}

	public void movePlayer(int i) {
		if (i == 1) {
			if (x < 525) {
				x += 25;
			} else {
				x = 525;
			}
		} else if (i == -1) {
			if (x > 0) {
				x -= 25;
			} else {
				x = 0;
			}
		}
	}

	public void setXSpeed(double dx) {
		xSpeed = dx;
	}

	public void paint(Graphics g) {
		g.drawImage(myImage,(int)x,(int)y,null);
	}
}
