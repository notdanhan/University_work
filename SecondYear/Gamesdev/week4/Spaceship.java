import java.awt.*;

public class Spaceship extends Sprite2D {

	public Spaceship(Image i, int windowWidth) {
		/*hardcoded the position of the left corner if
		the spaceship is touching the wall on the right*/
		super(i,(windowWidth-54));
		this.y = 552;
		this.x = windowWidth/2 - 54/2;
	}

	public void move() {
		this.x += xSpeed;
		if (this.x < 0) {
			this.x = 0;
		} else if(this.x > this.winWidth) {
			this.x = this.winWidth;
		}
	}
}
