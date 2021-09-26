import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.lang.Math;

@SuppressWarnings("serial")
public class GameOfLife extends JFrame implements Runnable, MouseListener {

	//member data
	private static final Dimension WindowSize = new Dimension(800,800);
	private BufferStrategy strategy;
	private boolean running;
	private boolean[][][] gameState;
	private Thread t;

	public GameOfLife() {
		//Boolean arrays are initalized to false anyway so nothing special needs to be done
		gameState = new boolean[2][40][40];
		Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int x = screensize.width/2 - WindowSize.width/2;
		int y = screensize.height/2 - WindowSize.height/2;
		this.running = false;
		this.setTitle("Conways Game of Life");
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
		boolean[][] temp = new boolean[40][40];
		while(true) {
			try {
				Thread.sleep(200);
			} catch(InterruptedException e) {
				System.out.println(e);
			}
			if(running) {
				for(int x = 0; x < 40; x++) {
					for(int y = 0; y < 40; y++) {
						int neighbours = 0;
						neighbours += (gameState[0][(39 + x) % 40][y]) ? 1:0;
						neighbours += (gameState[0][(39 + x) % 40][(39 + y) % 40]) ? 1:0;
						neighbours += (gameState[0][(39 + x) % 40][(y+1)%40]) ? 1:0;
						neighbours += (gameState[0][x][(39 + y)%40]) ? 1:0;
						neighbours += (gameState[0][x][(y+1)%40]) ? 1:0;
						neighbours += (gameState[0][(x+1) % 40][y]) ? 1:0;
						neighbours += (gameState[0][(x+1) % 40][(39 + y) % 40]) ? 1:0;
						neighbours += (gameState[0][(x+1) % 40][(y+1)%40]) ? 1:0;
						if((neighbours == 2 && gameState[0][x][y]) || neighbours == 3) {
							gameState[1][x][y] = true;
						} else {
							gameState[1][x][y] = false;
						}
					}
				}
				temp = gameState[0];
				gameState[0] = gameState[1];
				gameState[1] = temp;
				this.repaint();
			}
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		Point temp = e.getPoint();
		//Performs an XOR on the position clicked to reduce logic :)
		if(temp.getY() >= 40 && temp.getY() <= 65) {
			if(temp.getX() >= 30 && temp.getX() <= 80) {
				running ^= true;
			} else if(temp.getX()>=90 && temp.getX()<=170) {
				if(running) {
					running = false;
				}
				gameState[0] = new boolean[40][40];
				int tempLimit = (int) (Math.random() * 1600);
				for(int i = 0; i < tempLimit; i++) {
					gameState[0][(int)(Math.random() * 40)][(int)(Math.random() * 40)] ^= true;
				}
			}
		}
		gameState[0][(int)temp.getX()/20][(int)temp.getY()/20]^=true;
		this.repaint();
	}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	public void paint(Graphics g) {
		g = strategy.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,800,800);
		g.setColor(Color.WHITE);
		for(int i = 0; i < 40; i++) {
			for(int j = 0; j < 40; j++) {
				if(gameState[0][i][j]) {
					g.fillRect(i*20,j*20,20,20);
				}
			}
		}
		g.setColor(Color.GREEN);
		g.fillRect(30,40,50,25);
		g.fillRect(90,40,80,25);
		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman",Font.PLAIN,15));
		g.drawString("Start",35,60);
		g.drawString("Random",95,60);
		strategy.show();
	}

	public static void main(String[] args) {
		GameOfLife application = new GameOfLife();
	}
}
