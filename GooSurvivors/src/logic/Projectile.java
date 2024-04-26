package logic;

import java.awt.Image;

public class Projectile {
	private Image picture;
	private int x; private int y;
	private int vx; private int vy;
	
	public Projectile(Image p, int a, int b, int va, int vb) {
		// TODO Auto-generated constructor stub
		setPicture(p);
		x = a;
		y = b;
		vx = va;
		vy = vb;
	}
	
	public void setX(int a) {
		x = a;
	}
	public int getX() {
		return x;
	}
	
	public void setY(int b) {
		y = b;
	}
	public int getY() {
		return y;
	}
	
	public void setVx(int va) {
		vx = va;
	}
	public int getVx() {
		return vx;
	}
	
	public void setVy(int vb) {
		vy = vb;
	}
	public int getVy() {
		return vy;
	}

	public Image getPicture() {
		return picture;
	}

	public void setPicture(Image picture) {
		this.picture = picture;
	}
	

}
