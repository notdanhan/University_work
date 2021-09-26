import java.awt.*;

public class PlayerBullet extends Sprite2D {

	public PlayerBullet(Image i, int WindowWidth) {
		super(i,WindowWidth);
		this.xSpeed = 6;
	}

	public boolean move() {
		this.y -= xSpeed;
		if(this.y < 0) {
			return false;
		} else {
			return true;
		}
	}
}
