
package logic;

import java.awt.Color;
import java.awt.Rectangle;

public class WallofGoo {
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
	}
	
	//increases the boundaries of the goo every second as a part of the graphics
	public void update() {
		r1.setRect(r1.x, r1.y, r1.width, r1.height+20);
		r2.setRect(r2.x, r2.y, r2.width+20, r2.height);
		r3.setRect(r3.x-20, r3.y, r3.width, r3.height);
		r4.setRect(r4.x, r4.y-20, r4.width, r4.height);
	}

}
