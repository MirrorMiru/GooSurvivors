package logic;

import java.awt.Graphics;
import java.util.ArrayList;

public class pathfinding {
	private ArrayList<Enemy> enemies;
	private Player player;
	
	public pathfinding(Player p) {
		// TODO Auto-generated constructor stub
		enemies = new ArrayList<Enemy>();
		player = p;
	}
	public void pathfinder() {
		for (int i = 0;  i < enemies.size();i++) {
			enemies.get(i);
		}
	}
	
	public void paint(Graphics g) {
		
	}

}
