import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.util.*;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class FireworksStart extends JFrame implements Runnable, MouseListener {

	private LinkedList<Particle> particlesList = new LinkedList<Particle>();
	private BufferStrategy strategy;
	private boolean initialised = false;

	// embedded class for handling individual particles
	public class Particle {
		private int x, y; // position
		private int lifetime = 200; // how many frames are left before destruction
		private int red,green,blue;
		private double vx, vy;


		// constructor
		public Particle(int x, int y) {
			this.x = x;
			this.y = y;

			red = (int)Math.floor(128+Math.random()*128);
			green = (int)Math.floor(128+Math.random()*128);
			blue = (int)Math.floor(128+Math.random()*128);
			vx = 10 * (Math.random()-0.5);
			vy = -(Math.random()*8);
		}

		// updates the particle for 1 frame of animation
		// returns true if the particle should be destroyed
		public boolean update() {
			lifetime--;
			if (red>0)
				red--;
			if (green>0)
				green--;
			if (blue>0)
				blue--;

			x += vx;
			y += vy;
			vy += 0.1;

			return (lifetime<=0);
		}

		public void paint(Graphics g) {
			g.setColor(new Color(red,green,blue));
			g.fillRect(x, y, 10, 10);
		}
	}

	// constructor
	public FireworksStart() {
		this.setBounds(10, 10, 600, 600);
		this.setVisible(true);
		addMouseListener(this);

		createBufferStrategy(2);
		strategy = getBufferStrategy();

		Thread t = new Thread(this);
		t.start();

		initialised = true;
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		Particle p = new Particle(x,y);
		particlesList.add(p);
	}

	public void run() {
		while(1==1) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

			Iterator i = particlesList.iterator();
			while (i.hasNext()) {
				Particle p = (Particle)i.next();

				if (p.update())
					i.remove();

			}

			this.repaint();
		}
	}

	public void paint(Graphics g) {
		if (initialised == true){
			g = strategy.getDrawGraphics();
			g.setColor(Color.black);
			g.fillRect(0,0,600,600);

			ListIterator i = particlesList.listIterator();
			while (i.hasNext()) {
				Particle p = (Particle)i.next();
				p.paint(g);
			}

			g.dispose();
			strategy.show();
		}

	}


	public static void main(String[] args) {
		FireworksStart f = new FireworksStart();
	}

}
