package logic;

import java.awt.Image;

public class Enemydrops {
	private int power;
	private Image picture;
	private int x; private int y;
	public Enemydrops(int p, Image pi, int a, int b) {
		// TODO Auto-generated constructor stub
		setPower(p);
		setPicture(pi);
		setX(a);
		setY(b);
	}
	
	//getters and setters for variables
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	
	public Image getPicture() {
		return picture;
	}
	public void setPicture(Image picture) {
		this.picture = picture;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

}
