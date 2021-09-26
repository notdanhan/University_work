import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;

@SuppressWarnings("serial")
public class InvadersApplication extends JFrame implements Runnable, KeyListener {

	//member data
	private static final Dimension WindowSize = new Dimension(600,600);
	private BufferStrategy strategy;
	private static final int NUMALIENS = 30;
	private Alien[] AliensArray = new Alien[NUMALIENS];
	private Spaceship PlayerShip;
	private Thread t;
	private ArrayList<PlayerBullet> bullets;
	private ImageIcon bulletImage;
	private int score;
	private int highScore;
	private int bulletTimeout = 0;
	private int aliensAlive = NUMALIENS;
	private boolean gameOver = false;

	public InvadersApplication() {
		String workingDirectory = System.getProperty("user.dir");
		ImageIcon aliens = new ImageIcon(workingDirectory + "/alien_ship_1.png");
		ImageIcon aliens2 = new ImageIcon(workingDirectory + "/alien_ship_2.png");
		bulletImage = new ImageIcon(workingDirectory + "/bullet.png");
		ImageIcon player = new ImageIcon(workingDirectory+"/player_ship.png");
		/*50 width + 10 margin*/
		int x_offset = (WindowSize.width / 2) - 290/2;
		int y_offset = 25;
		int score = 0;
		int highScore = 0;
		Image[] alienImages = new Image[2];
		alienImages[0] = aliens.getImage();
		alienImages[1] = aliens2.getImage();
		for(int i = 0; i < NUMALIENS; i++) {
			AliensArray[i] = new Alien(alienImages,WindowSize.width);
			/*10 margin on x and y*/
			AliensArray[i].setPosition(((i%5)*(60))+x_offset,((i/5)*42)+y_offset);
		}
		this.bullets = new ArrayList<PlayerBullet>();
		this.PlayerShip = new Spaceship(player.getImage(),WindowSize.width);

		Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int x = screensize.width/2 - WindowSize.width/2;
		int y = screensize.height/2 - WindowSize.height/2;
		this.setTitle("Space Invaders");
		this.setLocation(x,y);
		this.setPreferredSize(WindowSize);
		this.pack();
		this.createBufferStrategy(2);
		strategy = getBufferStrategy();
		this.setVisible(true);
		this.addKeyListener(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		t = new Thread(this);
		t.start();
	}

	public void newGame() {
		int x_offset = (WindowSize.width / 2) - 290/2;
		int y_offset = 25;
		this.bullets.clear();
		this.bulletTimeout = 0;
		this.score = 0;
		this.PlayerShip.setPosition(WindowSize.width/2 - 27,552);
		for(int i=0;i<NUMALIENS;i++) {
			AliensArray[i].setPosition(((i%5)*(60))+x_offset,((i/5)*42)+y_offset);
			AliensArray[i].setXSpeed(4);
		}
		this.aliensAlive = NUMALIENS;
		this.gameOver = false;
	}

	private void newRound() {
		int x_offset = (WindowSize.width / 2) - 290/2;
		int y_offset = 25;
		bullets.clear();
		bulletTimeout = 1024;
		for(int i=0;i<NUMALIENS;i++) {
			AliensArray[i].setPosition(((i%5)*(60))+x_offset,((i/5)*42)+y_offset);
			AliensArray[i].increaseSpeed();
		}
		this.aliensAlive = NUMALIENS;
	}

	public void run() {
		boolean inbounds = true;
		while(true) {
				try {
					if (!gameOver) {
						inbounds = true;
						for (int i = 0; i< NUMALIENS; i++) {
							inbounds &= AliensArray[i].move();
						}
						if(!inbounds) {
							for (int i = 0; i < NUMALIENS; i++) {
								AliensArray[i].reverseDirection();
								if((AliensArray[i].getBottom() >= 552) && AliensArray[i].isAlive()) {
									this.gameOver = true;
									if(this.score > this.highScore) {
										this.highScore = this.score;
									}
								}
							}
						}
						Iterator iterator = bullets.iterator();
						while(iterator.hasNext()) {
							try {
								PlayerBullet b = (PlayerBullet) iterator.next();
								if(!b.move()) {
									iterator.remove();
									continue;
								}
								for(int i = NUMALIENS - 1; i >= 0; i--) {
									Dimension bdimensions = b.getDimensions();
									if(AliensArray[i].checkCollision(bdimensions.width,bdimensions.height,bulletImage.getImage().getWidth(null),bulletImage.getImage().getHeight(null))) {
										iterator.remove();
										score += 50;
										aliensAlive--;
										break;
									}
								}
							} catch(Exception e) {
								//on a rare occasion there's a concurrent modification exception passed
								//this ends the bullet refresh until the next loop
								this.newRound();
								System.out.println(e);
								break;
							}
						}
						bulletTimeout = bulletTimeout>>1;
						PlayerShip.move();
				}
				this.repaint();
				if (aliensAlive == 0) {
					this.newRound();
				}
				t.sleep(33);
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
	}

	public void shootBullet() {
		if (bulletTimeout == 0) {
			//Basically the timeout works by performing bitshifts until
			//bulletTimeout is 0
			bulletTimeout = 128;
			PlayerBullet bullet = new PlayerBullet(bulletImage.getImage(),WindowSize.width);
			bullet.setPosition(PlayerShip.getCenter() - 3, 552 - bulletImage.getImage().getHeight(null));
			bullets.add(bullet);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(!gameOver) {
			switch(e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
				case KeyEvent.VK_A:
					PlayerShip.setXSpeed(-5);
					break;
				case KeyEvent.VK_RIGHT:
				case KeyEvent.VK_D:
					PlayerShip.setXSpeed(5);
					break;
					case KeyEvent.VK_SPACE:
					this.shootBullet();
					break;
			}
		} else {
			this.newGame();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		PlayerShip.setXSpeed(0);
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}

	public void paint(Graphics g) {
		g = strategy.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,WindowSize.width,WindowSize.height);
		if(!gameOver) {
			for(int i = 0; i < NUMALIENS; i++) {
				AliensArray[i].paint(g);
			}
			Iterator iterator = bullets.iterator();
			while(iterator.hasNext()) {
				PlayerBullet b = (PlayerBullet) iterator.next();
				b.paint(g);
			}
			/*Print the score board*/
			g.setColor(Color.WHITE);
			String scoreBoard = "Score: " + this.score +" High Score: " + this.highScore;
			g.setFont(new Font("TimesRoman",Font.PLAIN,30));
			g.drawString(scoreBoard,(WindowSize.width/2 - 210),50);
			PlayerShip.paint(g);
		} else {
			g.setColor(Color.WHITE);
			g.setFont(new Font("TimesRoman",Font.PLAIN,30));
			String message = "Game Over!\nYour Score: "+score+"\nHigh Score: "+highScore+"\nPress Any Key \nto start a new game";
			this.drawString(g,message,WindowSize.width/2 - 210, 200);
		}
		strategy.show();
	}
	/*Graphics.drawString() deos not handle multi level strings!*/
	private void drawString(Graphics g, String text, int x, int y) {
		int lineHeight = g.getFontMetrics().getHeight();
		String[] lines = text.split("\n");
		for (int i = 0; i < lines.length; i++) {
			g.drawString(lines[i], x, y += lineHeight);
		}
	}

	public static void main(String args[]) {
		InvadersApplication game = new InvadersApplication();
	}
}
