import java.awt.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;

@SuppressWarnings("serial")
public class MyApplication extends JFrame {

	private static final Dimension WindowSize = new Dimension(620,600);

	public MyApplication() {
		this.setTitle("Pacman or something");
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
	}
	/*Paint seems to be invoked every time the window is resized so I kind of left it*/
	@Override
	public void paint(Graphics g) {
		int margin = 10;
		int width = 50;
		int height = 50;
		int y_padding = 40;
		Rectangle r = this.getBounds();
		int h = (int)(r.getHeight() - y_padding) / (height + margin);
		int w = (int)r.getWidth()/(width+margin);
		Graphics2D g2 = (Graphics2D) g;
		for(int x = 0; x < w; x++ ){
			for(int y = 0; y < h; y++) {
				int red = (int)(Math.random() * 255);
				int green = (int)(Math.random() * 255);
				int blue = (int)(Math.random() * 255);
				Rectangle2D myrect = new Rectangle2D.Double(margin + (x * (width + margin)),(y_padding + (y * (margin + height))),width,height);
				Color mycolor  = new Color(red,green,blue);
				g2.setColor(mycolor);
				g2.fill(myrect);
				g2.draw(myrect);
			}
		}
	}

	public static void main(String args[]) {
		MyApplication w = new MyApplication();
	}
}
