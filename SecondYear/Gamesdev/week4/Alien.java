import java.awt.*;

public class Alien extends Sprite2D {
	public Alien(Image i, int windowWidth) {
		/*by putting taking 50 from window width it will return
		false if the obj is touching the wall rather than going through it*/
		super(i,(windowWidth - 50));
		this.xSpeed = 4;
	}

	public boolean move() {
		this.x += this.xSpeed;
		if(this.x > this.winWidth || this.x < 0) {
			return false;
		}
		return true;
	}

	public void reverseDirection() {
		this.xSpeed *= -1;
		this.y += 10;
	}
}
