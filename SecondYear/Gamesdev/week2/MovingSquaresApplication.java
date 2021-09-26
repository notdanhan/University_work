import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class MovingSquaresApplication extends JFrame implements Runnable {

	private static final Dimension WindowSize = new Dimension(600,600);
	private static final int NUMGAMEOBJECTS = 30;
	private GameObject[] GameObjectsArray = new GameObject[NUMGAMEOBJECTS];
	private Color white = new Color(255,255,255);
	public MovingSquaresApplication() {
		this.setTitle("Moving Squares innit");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Center the screen
		Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int x = screensize.width/2 - WindowSize.width/2;
		int y = screensize.height/2 - WindowSize.height/2;
		this.setLocation(x,y);
		//Make the Window the size defined in WindowSize
		this.setPreferredSize(WindowSize);
		this.pack();
		//Display everything
		this.setVisible(true);
		for(int i =0; i<NUMGAMEOBJECTS; i++) {
			GameObjectsArray[i] = new GameObject();
		}
		Thread t = new Thread(this);
		t.start();
	}

	public void run() {
		try {
			while(true) {
				Thread.sleep(20);
				for(int i = 0; i < NUMGAMEOBJECTS; i++) {
					GameObjectsArray[i].move();
				}
				this.removeAll();
				this.revalidate();
				this.repaint();
			}
		} catch(Exception e) {
			System.out.println("ERROR: " + e);
		}
	}

	@Override
	public void paint(Graphics g) {
		//Clear and redraw
		g.setColor(white);
		g.fillRect(0,0,600,600);
		for(int i = 0; i < NUMGAMEOBJECTS; i++) {
			GameObjectsArray[i].paint(g);
		}
	}

	public static void main(String[] args) {
		MovingSquaresApplication mover = new MovingSquaresApplication();
	}
}
