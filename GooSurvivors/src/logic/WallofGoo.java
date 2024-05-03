
package logic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class WallofGoo {
	private Graphics g;
	private Rectangle r1;
	private Rectangle r2;
	private Rectangle r3;
	private Rectangle r4;
	private Color colour;
	public WallofGoo() {
		// TODO Auto-generated constructor stub
		r1 = new Rectangle(0, 0, 1000, 20);
		r2 = new Rectangle(0, 0, 20, 1000);
		r3 = new Rectangle(980, 0, 600, 1000);
		r4 = new Rectangle(0, 980, 1000, 600);
		colour = new Color(55,57,58);
		g.setColor(colour);
		g.drawRect(0, 0, 1000, 20);
		g.drawRect(0, 0, 20, 1000);
		g.drawRect(980, 0, 600, 1000);
		g.drawRect(0, 980, 1000, 600);
		g.fillRect(0, 0, 1000, 20);
		g.fillRect(0, 0, 20, 1000);
		g.fillRect(980, 0, 600, 1000);
		g.fillRect(0, 980, 1000, 600);
	}
	
	//increases the boundaries of the goo every second as a part of the graphics
	public void update() {
		g.drawRect(r1.x, r1.y, r1.width, r1.height+2);
		g.drawRect(r2.x, r2.y, r2.width+2, r2.height);
		g.drawRect(r3.x-2, r3.y, r3.width, r3.height);
		g.drawRect(r4.x, r4.y-2, r4.width, r4.height);
		
		g.fillRect(r1.x, r1.y, r1.width, r1.height+2);
		g.fillRect(r2.x, r2.y, r2.width+2, r2.height);
		g.fillRect(r3.x-2, r3.y, r3.width, r3.height);
		g.fillRect(r4.x, r4.y-2, r4.width, r4.height);
		
		r1.setRect(r1.x, r1.y, r1.width, r1.height+2);
		r2.setRect(r2.x, r2.y, r2.width+2, r2.height);
		r3.setRect(r3.x-2, r3.y, r3.width, r3.height);
		r4.setRect(r4.x, r4.y-2, r4.width, r4.height);
	}
	
	//decreases the boundaries after a player gets a drop that repels the wall
	public void repel() {
		g.drawRect(r1.x, r1.y, r1.width, r1.height-2);
		g.drawRect(r2.x, r2.y, r2.width-2, r2.height);
		g.drawRect(r3.x+2, r3.y, r3.width, r3.height);
		g.drawRect(r4.x, r4.y+2, r4.width, r4.height);
		
		g.fillRect(r1.x, r1.y, r1.width, r1.height-2);
		g.fillRect(r2.x, r2.y, r2.width-2, r2.height);
		g.fillRect(r3.x+2, r3.y, r3.width, r3.height);
		g.fillRect(r4.x, r4.y-2, r4.width, r4.height);
		
		r1.setRect(r1.x, r1.y, r1.width, r1.height-2);
		r2.setRect(r2.x, r2.y, r2.width-2, r2.height);
		r3.setRect(r3.x+2, r3.y, r3.width, r3.height);
		r4.setRect(r4.x, r4.y+2, r4.width, r4.height);

	}
}
