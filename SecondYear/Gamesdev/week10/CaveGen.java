import java.awt.*;
import javax.swing.*;
import java.util.Random;

@SuppressWarnings("serial")
public class CaveGen extends JFrame implements Runnable {

	//member data
	private static final Dimension WindowSize = new Dimension(800,800);
	private boolean running;
	private boolean isReady;
	private boolean[][][] gameState;
	private Thread t;

	public CaveGen() {
		this.isReady = false;
		gameState = new boolean[2][200][200];
		Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int x = screensize.width/2 - WindowSize.width/2;
		int y = screensize.height/2 - WindowSize.height/2;
		this.running = false;
		this.setTitle("Cave Generation");
		this.setLocation(x,y);
		this.setPreferredSize(WindowSize);
		this.pack();
		this.populate();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		t = new Thread(this);
		this.running = true;
		t.start();
	}

	public void run() {
		boolean[][] temp = new boolean[200][200];
		int iterations = 0;
		while(true) {
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) {
				System.out.println(e);
			}
			if(running) {
				if(iterations<4) {
					for(int x = 0; x < 200; x++) {
						for(int y = 0; y < 200; y++) {
							int neighbours = 0;
							neighbours += (gameState[0][(199 + x) % 200][y]) ? 1:0;
							neighbours += (gameState[0][(199 + x) % 200][(199 + y) % 200]) ? 1:0;
							neighbours += (gameState[0][(199 + x) % 200][(y+1)%200]) ? 1:0;
							neighbours += (gameState[0][x][(199 + y)%200]) ? 1:0;
							neighbours += (gameState[0][x][(y+1)%200]) ? 1:0;
							neighbours += (gameState[0][(x+1) % 200][y]) ? 1:0;
							neighbours += (gameState[0][(x+1) % 200][(199 + y) % 200]) ? 1:0;
							neighbours += (gameState[0][(x+1) % 200][(y+1)%200]) ? 1:0;
							if(neighbours >= 5) {
								gameState[1][x][y] = true;
							} else {
								gameState[1][x][y] = false;
							}
						}
					}
					temp = gameState[0];
					gameState[0] = gameState[1];
					gameState[1] =temp;
					iterations++;
				}
				this.repaint();
			}
		}
	}

	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0,0,800,800);
		g.setColor(Color.WHITE);
		if(this.isReady) {
			for(int i = 0; i < 200; i++) {
				for(int j = 0; j < 200; j++) {
					if(gameState[0][i][j]) {
						g.fillRect(i*4,j*4,4,4);
					}
				}
			}
		}
	}

	public void populate() {
		Random myNums = new Random();
		for(int i = 0; i < 200; i++) {
			for(int j = 0; j < 200; j++) {
				if(myNums.nextInt(10) > 4) {
					this.gameState[0][i][j] = true;
				}
			}
		}
		this.isReady = true;
		this.repaint();
	}

	public static void main(String[] args) {
		CaveGen myCave = new CaveGen();
	}
}
