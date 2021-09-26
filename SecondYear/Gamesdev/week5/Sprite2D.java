import java.awt.*;

public class Sprite2D {

	//member data
	protected double x,y;
	protected double xSpeed=0;
	protected Image myImage;
	int winWidth;

	//constructor
	public Sprite2D(Image i, int windowWidth) {
		this.myImage = i;
		this.winWidth = windowWidth;
	}

	public void setPosition(double xx, double yy) {
		this.x = xx;
		this.y = yy;
	}

	public void setXSpeed(double speed) {
		this.xSpeed = speed;
	}

	public void paint(Graphics g) {
		g.drawImage(this.myImage,(int)this.x,(int)this.y,null);
	}

	public Dimension getDimensions() {
		return new Dimension((int)this.x,(int)this.y);
	}
}
