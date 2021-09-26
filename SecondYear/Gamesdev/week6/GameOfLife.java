import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class GameOfLife extends JFrame implements Runnable, MouseListener {

	//member data
	private static final Dimension WindowSize = new Dimension(800,800);
	private BufferStrategy strategy;
	private boolean[][] squares;
	private Thread t;

	public GameOfLife() {
		//Boolean arrays are initalized to false anyway so nothing special needs to be done
		squares = new boolean[40][40];
		Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int x = screensize.width/2 - WindowSize.width/2;
		int y = screensize.height/2 - WindowSize.height/2;
		this.setTitle("Space Invaders");
		this.setLocation(x,y);
		this.setPreferredSize(WindowSize);
		this.pack();
		this.createBufferStrategy(2);
		strategy = getBufferStrategy();
		this.addMouseListener(this);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		t = new Thread(this);
		t.start();
	}

	public void run() {
		while(true) {
			try {
				repaint();
				t.sleep(33);
			} catch(InterruptedException e) {
				System.out.println(e);
			}
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mouseClicked(MouseEvent e) {
		Point temp = e.getPoint();
		//Performs an XOR on the position clicked to reduce logic :)
		squares[(int)temp.getX()/20][(int)temp.getY()/20]^=true;
	}

	public void paint(Graphics g) {
		g = strategy.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,800,800);
		g.setColor(Color.WHITE);
		for(int i = 0; i < 40; i++) {
			for(int j = 0; j < 40; j++) {
				if(squares[i][j]) {
					g.fillRect(i*20,j*20,20,20);
				}
			}
		}
		strategy.show();
	}

	public static void main(String[] args) {
		GameOfLife application = new GameOfLife();
	}
}
