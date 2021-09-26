import java.awt.*;

public class Alien extends Sprite2D {
	private boolean isAlive = true;
	private Image[] frames;
	private int framecount;
	public Alien(Image[] newFrames, int windowWidth) {
		/*by putting taking 50 from window width it will return
		false if the obj is touching the wall rather than going through it*/
		super(newFrames[0],(windowWidth - 50));
		this.frames = newFrames;
		this.xSpeed = 4;
		this.framecount = 0;
	}

	public boolean move() {
		this.x += this.xSpeed;
		if((this.x > this.winWidth || this.x < 0)&&isAlive) {
			return false;
		}
		return true;
	}

	public void reverseDirection() {
		this.xSpeed *= -1;
		this.y += 20;
	}

	@Override
	public void setPosition(double xx, double yy) {
		this.x = xx;
		this.y = yy;
		this.isAlive = true;
		this.framecount =0;
	}

	@Override
	public void paint(Graphics g) {
		framecount++;
		if(isAlive) {
			//Using bitshifts to denote framecount
			g.drawImage(this.frames[framecount>>6&0x1],(int)this.x,(int)this.y,null);
		}
		//Epic bitwise OR operator so it goes to zero the moment the number is higher than 127
		// For some bizzare reason java uses 0xabcd to denote binary rather than 0b
		//Quite frankly I do not know why this works but framecount | 0b1111111 does not
		framecount = framecount | 0x01111111;
	}

	public boolean checkCollision(int xx, int yy, int width, int height) {
		if(!isAlive) return false;

		if (((xx < x && xx+width > x) || (xx<x+myImage.getWidth(null) && xx+width > x)) && ((y<yy && y+myImage.getHeight(null)>yy) || (yy<y && yy + height > y))) {
			this.isAlive = false;
			return true;
		}
		return false;
	}

	public double getBottom() {
		return this.y + myImage.getHeight(null);
	}

	public void increaseSpeed() {
		this.xSpeed *= 1.11;
	}

	public boolean isAlive() {
		return this.isAlive;
	}
}
