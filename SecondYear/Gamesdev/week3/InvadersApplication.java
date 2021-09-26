import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class InvadersApplication extends JFrame implements Runnable, KeyListener {

	//member data
	private static final Dimension WindowSize = new Dimension(600,600);
	private static final int NUMALIENS = 30;
	private Sprite2D[] AliensArray = new Sprite2D[NUMALIENS];
	private Sprite2D PlayerShip;
	private Thread t;

	//Constructor
	public InvadersApplication() {
		String workingDirectory = System.getProperty("user.dir");
		//Create sprites
		ImageIcon aliens = new ImageIcon(workingDirectory + "/alien_ship_1.png");
		ImageIcon player = new ImageIcon(workingDirectory+"/player.png");
		for(int i = 0; i < NUMALIENS; i++) {
			AliensArray[i] = new Sprite2D(aliens.getImage());
		}
		PlayerShip = new Sprite2D(player.getImage());
		PlayerShip.setPosition(600/2 - 75/2, 544);


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
		t = new Thread(this);
		t.start();
	}
	public void handleKey(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_W:
				PlayerShip.movePlayer(-1);
				break;
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D:
				PlayerShip.movePlayer(1);
				break;
			default:
				break;
		}
	}
	//thread entry point
	public void run() {
		try {
			while(true) {
				for(int i = 0; i < NUMALIENS; i++) {
					AliensArray[i].moveEnemy();
				}
				this.removeAll();
				this.revalidate();
				this.repaint();
				Thread.sleep(33);
			}
		} catch(InterruptedException e) {
			System.out.println(e);
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		this.handleKey(e);
	}
	@Override
	public void keyReleased(KeyEvent e) {
		this.handleKey(e);
	}
	@Override
	public void keyTyped(KeyEvent e) {
		this.handleKey(e);
	}

	@Override
	public void paint(Graphics g) {
		//Clear and redraw
		g.setColor(Color.WHITE);
		g.fillRect(0,0,600,600);
		for (int i = 0; i < NUMALIENS; i++) {
			AliensArray[i].paint(g);
		}
		PlayerShip.paint(g);
	}

	public static void main(String[] args) {
		InvadersApplication game = new InvadersApplication();
	}
}
