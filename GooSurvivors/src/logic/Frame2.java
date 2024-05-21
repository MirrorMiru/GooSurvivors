package logic;

import java.util.ArrayList;

public class Frame2 {
//this is simply a different class that should be implemented in frame later on
	
	ArrayList<Enemy> shootSkells = new ArrayList<Enemy>();
	ArrayList<Enemy> spraySkells = new ArrayList<Enemy>();
	ArrayList<ProjectileBullet> bullets = new ArrayList<ProjectileBullet>();
	
	private void shoot(ArrayList<Enemy> something) { //really want to take out the 
		for( Enemy ss : something) {
			if( ss instanceof ShootingSkeleton ) {
				if( ((ShootingSkeleton) ss).getShootingTimer() == 40) {
				ProjectileBullet b = new ProjectileBullet( 10, 20, 20, ((ShootingSkeleton) ss).getX(), ((ShootingSkeleton) ss).getY(), 50);
				bullets.add( b );
				if( ((ShootingSkeleton) ss).getX() < -globalX+300) {
					b.setVx(b.getSpeed());
					System.out.print("SEX IT WORKS");
				}else {
					b.setVx(-1*b.getSpeed());
					System.out.print("SEX IT WORKS");
				}
				if(((ShootingSkeleton) ss).getY() < -globalY+300) {
					b.setVy(b.getSpeed());
					System.out.print("SEX IT WORKS");
				}else {
					b.setVy(-1*b.getSpeed());
					System.out.print("SEX IT WORKS");
					}
				}
			}
			if( ss instanceof SpraySkeleton ) {
				if( ((SpraySkeleton) ss).getShootingTimer() == 40) {
					for( int i = 0; i < 5; i++) {
					ProjectileBullet s = new ProjectileBullet( 10, 20, 20, ((SpraySkeleton) ss).getX(), ((SpraySkeleton) ss).getY(), 50);
					bullets.add( s );
					}
					if( ((SpraySkeleton) ss).getX() < -globalX+300) {
						s.setVx(s.getSpeed());
						System.out.print("SEX IT WORKS");
					}else {
						s.setVx(-1*s.getSpeed());
						System.out.print("SEX IT WORKS");
					}
					if(((SpraySkeleton) ss).getY() < -globalY+300) {
						s.setVy(s.getSpeed());
						System.out.print("SEX IT WORKS");
					}else {
						s.setVy(-1*s.getSpeed());
						System.out.print("SEX IT WORKS");
					}
				}
			}		
		}//this is to be put in frame
		
	}

//in paint method
	enemyLogic(shootSkells, g);
	enemyLogic(spraySkells, g);
	shoot(shootSkells);
	shoot(spraySkells);
	for( ProjectileBullet bull: bullets) {
		bull.paint(g);
	}
// in spawn enemies method
	else if(who.toLowerCase().equals("shooting skeletons")) {
		enemies.add(new ShootingSkeleton());
	}else if(who.toLowerCase().equals("spray skeletons")) {
		enemies.add(new SpraySkeleton());
	}
	
//in start waves method
	if(waveTimer == 30) {
		spawnEnemies( 2, shootSkells, "shooting skeletons");
	}
	if(waveTimer == 100) {
		spawnEnemies( 2, spraySkells, "spray skeletons");
	}
} 