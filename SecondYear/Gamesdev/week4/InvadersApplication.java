import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class InvadersApplication extends JFrame implements Runnable, KeyListener {

	//member data
	private static final Dimension WindowSize = new Dimension(600,600);
	private BufferStrategy strategy;
	private static final int NUMALIENS = 30;
	private Alien[] AliensArray = new Alien[NUMALIENS];
	private Spaceship PlayerShip;
	private Thread t;


	public InvadersApplication() {
		String workingDirectory = System.getProperty("user.dir");
		ImageIcon aliens = new ImageIcon(workingDirectory + "/alien_ship_1.png");
		ImageIcon player = new ImageIcon(workingDirectory+"/player_ship.png");
		/*50 width + 10 margin*/
		int x_offset = (WindowSize.width / 2) - 290/2;
		int y_offset = 25;

		for(int i = 0; i < NUMALIENS; i++) {
			AliensArray[i] = new Alien(aliens.getImage(),WindowSize.width);
			/*10 margin on x and y*/
			AliensArray[i].setPosition(((i%5)*(60))+x_offset,((i/5)*42)+y_offset);
		}

		this.PlayerShip = new Spaceship(player.getImage(),WindowSize.width);

		Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int x = screensize.width/2 - WindowSize.width/2;
		int y = screensize.height/2 - WindowSize.height/2;
		this.setTitle("Space Invaders");
		this.setLocation(x,y);
		this.setPreferredSize(WindowSize);
		this.pack();
		this.setVisible(true);
		this.addKeyListener(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.createBufferStrategy(2);
		strategy = getBufferStrategy();
		t = new Thread(this);
		t.start();
	}

	public void run() {
		boolean inbounds = true;
		while(true) {
			try {
				inbounds = true;
				for (int i = 0; i< NUMALIENS; i++) {
					inbounds &= AliensArray[i].move();
				}
				if(!inbounds) {
					for (int i = 0; i < NUMALIENS; i++) {
						AliensArray[i].reverseDirection();
					}
				}
				PlayerShip.move();
				this.repaint();
				t.sleep(33);
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_A:
				PlayerShip.setXSpeed(-5);
				break;
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D:
				PlayerShip.setXSpeed(5);
				break;
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
		for(int i = 0; i < NUMALIENS; i++) {
			AliensArray[i].paint(g);
		}
		PlayerShip.paint(g);
		strategy.show();
	}

	public static void main(String args[]) {
		InvadersApplication game = new InvadersApplication();
	}
}
