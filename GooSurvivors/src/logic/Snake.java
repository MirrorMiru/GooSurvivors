package logic;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ArrayList;

public class Snake {
	ArrayList<Image> snake;
	public Snake() {
		// TODO Auto-generated constructor stub
		snake = new ArrayList<Image>();
		int r = (int)((Math.random()*25)+20);
		Image i = getImage("/img/tile9.png");
		for (int j = 0; j < r; j++) {
			snake.add(i);
		}
	}
	
	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Tile.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
	

}
