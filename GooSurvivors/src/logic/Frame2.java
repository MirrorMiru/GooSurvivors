package logic;

import java.util.ArrayList;

public class Frame2 {
//this is simply a different class that should be implemented in frame later on
	
	ArrayList<Enemy> shootSkells = new ArrayList<Enemy>();
	ArrayList<ProjectileBullet> bullets = new ArrayList<ProjectileBullet>();
	
	private void shoot(ArrayList<Enemy> something, int dmg, int width, int height) {
		for( Enemy ss : something) {
			if( ss.getShootingTimer() == 180) {//need to add an if statement for each subclass shooting enemy or 
				//make your own shooting class for each subclass
				ProjectileBullet b = new ProjectileBullet( dmg, width, height, -globalX-380, -globalY + 250, 1);
				bullets.add( b );
				if(ss.getX() < -globalX+300) {
					b.setVx(b.getSpeed());
				}else {
					b.setVx(-1*b.getSpeed());
				}
				if(ss.getY() < -globalY+300) {
					b.setVy(b.getSpeed());
				}else {
					b.setVy(-1*b.getSpeed());
			}
			}
		}//this is to be put in frame
		//this means that every single enemy with projectiles needs a shootingTimer variable and getter
	}
}
