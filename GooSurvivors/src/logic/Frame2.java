package logic;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import javax.swing.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
/******************************************************************
 * Program: GOO SURVIVORS                                         *
 ******************************************************************
 * Author:  Nikolas Pautov                                        *
 * Purpose: game innit                                            *
 *                                                                *
 ******************************************************************
 * Revision History:                                              *
 ******************************************************************
 *  Date  | Author |                                              *
 *--------|--------|----------------------------------------------*
 * 12/4/23| Nikol  | I don't know why I kept this part of the     * 
 *        |        | template. Just check the github repo.        *
 *        |        |                                              *
 ******************************************************************/
public class Frame2 extends JPanel implements ActionListener, KeyListener  {
	
	
	Player player = new Player();
   			
	//le title screen
	Sprite[] titleScreen = {new Sprite("/img/ts1.png",0,0,800,700), new Sprite("/img/ts2.png",0,0,800,700),new Sprite("/img/ts3.png",0,0,800,700),new Sprite("/img/ts4.png",0,0,800,700)};
	Sprite[] cursor = {new Sprite("/img/title1.png",0,0,800,700), new Sprite("/img/title2.png",0,0,800,700),new Sprite("/img/title3.png",0,0,800,700)};
	//le how to screen
	Sprite[] inst = {new Sprite("/img/inst1.png",0,0,800,700), new Sprite("/img/inst2.png",0,0,800,700),new Sprite("/img/inst3.png",0,0,800,700),new Sprite("/img/inst4.png",0,0,800,700)};
	
	Sprite[] GUI = {new Sprite("/img/gui1.png",0,0,800,700), new Sprite("/img/gui2.png",0,0,800,700),new Sprite("/img/gui3.png",0,0,800,700),new Sprite("/img/gui4.png",0,0,800,700),new Sprite("/img/gui5.png",0,0,800,700)};
	Sprite[] timeIcon = {new Sprite("/img/timer1.png",0,0,800,700), new Sprite("/img/timer2.png",0,0,800,700)};
	//le font
	Font myFont = new Font("Courier", Font.BOLD, 40);
	
	
	Sprite map = new Sprite("/img/map.png",-400,-350,2000,2000);
	
	Sprite bg = new Sprite("/img/permabg.png",0,0,800,700);
	
	Sprite[] over = {new Sprite("/img/go1.png",0,0,800,700), new Sprite("/img/go2.png",0,0,800,700),new Sprite("/img/go3.png",0,0,800,700),new Sprite("/img/go4.png",0,0,800,700)};
	
	Sprite[] loadS = {new Sprite("/img/loadS1.png",0,0,800,700),new Sprite("/img/loadS1.png",0,0,800,700),new Sprite("/img/loadS2.png",0,0,800,700),new Sprite("/img/loadS3.png",0,0,800,700)};
	//bgm, starts looping immediately and never stops
	//SimpleAudioPlayer backgroundMusic = new SimpleAudioPlayer("LabyrinthFight.wav", true);
	
	//frame width/height
	int width = 815;
	int height = 735;	
	
	int lives = 3;//lives
	
	int gamestate = 0;//gamestate
	
	int titleIndex = 0; //array index used for title screen cursor
	int titleAnim = 0;
	int titleAnim2 = 0;
	
	int UIanim = 0;
	int UIanim2 = 0;
	int UIanim3 = 0;
	int UIanim4 = 0;
	
	int globalX = 300;
	int globalY = 200;
	
	int healthVials = 0;
	int dmgVials = 0;
	int highScore = 0;
	
	CountdownTimer time = new CountdownTimer();
	
	//enemy arraylists
	ArrayList<Enemy> skells = new ArrayList<Enemy>();
	ArrayList<Enemy> bSlimes = new ArrayList<Enemy>();
	ArrayList<Enemy> sSlimes = new ArrayList<Enemy>();
	ArrayList<Enemy> hSlimes = new ArrayList<Enemy>();
	ArrayList<Enemy> shootSkells = new ArrayList<Enemy>();
	ArrayList<ProjectileBullet> bullets = new ArrayList<ProjectileBullet>();
	ArrayList<Item> items = new ArrayList<Item>();
	ArrayList<SurroundWeapon> surround = new ArrayList<SurroundWeapon>();
	
	deathWall wall = new deathWall();

	GameLoader load = new GameLoader();

	Tile[][] tiles = new Tile[27][27];
	
	String name = "map"+ (int)((Math.random() * 3) + 1) +".txt";
	
	File tilemap = new File(name);
	
	 //only using this because i have to
	static Queue<String> instructions1 = new LinkedList<>();
	static Queue<String> instructions2 = new LinkedList<>();
	static Queue<String> instructions3 = new LinkedList<>();
	static Queue<String> instructions4 = new LinkedList<>();
	
	static HashMap<String, int[]> waves = new HashMap<String, int[]>();
	
	
	Whip starter = new Whip(10,1);
	
	
	 
	/**
	* main function 
	* runs and updates Jframe
	*
	* @param String arg (i have no idea what this dose)
	*/
	public static void main(String[] arg) throws IOException {
		
			initWaves();
		
			instructions1.add("WELCOME TO GOO SURVIVORS!");
			instructions2.add("use ARROW KEYS to move");
			instructions3.add("your WEAPON fires automatically!");
			instructions4.add("it's that simple!");
			
			instructions1.add("starting weapon = FIRE WHIP");
			instructions2.add("it deals 1 damage");
			instructions3.add("Level up to unlock BALLS");
			instructions4.add("more XP = MORE BALLS");
			
			instructions1.add("break BROWN BOXES with your WEAPON");
			instructions2.add("GREEN VIAL = DAMAGE UP");
			instructions3.add("RED VIAL = HEALTH UP");
			instructions4.add("sometimes boxes give EXPEREINCE");
			

			instructions1.add("the vials you collect SAVE");
			instructions2.add("biuld them up over multiple rounds");
			instructions3.add("dont forget to LOAD to resume");
			instructions4.add("from where you left off");
			
			instructions1.add("each round lasts 6 MINUTES");
			instructions2.add("the first 30 seconds is a grace period");
			instructions3.add("survive as long as you can");
			instructions4.add("good luck soldier!");
		
	
		
		Frame2 f = new Frame2();

	

		
		
	}
	
	private static void initWaves() {
		waves.clear();
		int[][] initwaves = {{1,1,1,1,1,1}, {1,1,1,1,1,2,2,2}, {2,2,2,2,1}, {2,2,2,2,2,1,1,3},  {3,3,3,2,2,2}, {3,3,3,4,3,3,3}, {2,2,2,3,3,3,4,4,4},{1,1,1,1,1,1,1,1,1,1,2,2,2,5},{2,2,2,3,3,3,3,3,5,5,5},{5,5,5,5,2,2,2,4}};

		for(int i = 1; i<=10; i++){
			String waveName = "wave"+i;
			waves.put(waveName,initwaves[i-1]);
		}
	}
	
	
	/**
	* Runs once per frame, executes all of the game logic. 
	* The main function of the entire game, runs all physics and logic
	*
	* @param graphics g
	*/
	public void paint(Graphics g) {
		 
		super.paintComponent(g);//no clue what this dose
		if(gamestate == 0) {
			titleAnim++;
			
			if(titleAnim % 8 == 0) {
				titleAnim2++;
			}
			if(titleAnim2 > 3) {
				titleAnim = 0;
				titleAnim2 = 0;
			}
			titleScreen[titleAnim2].paint(g);
			cursor[titleIndex].paint(g);
		
		
		}else if(gamestate == 2) {
			

		//optional story images or game controlls
			titleAnim++;
			
			if(titleAnim % 8 == 0) {
				titleAnim2++;
			}
			if(titleAnim2 > 3) {
				titleAnim = 0;
				titleAnim2 = 0;
			}
			
			inst[titleAnim2].paint(g);
			
			g.setColor(Color.green);
			g.setFont(myFont.deriveFont(30.0f));
			g.drawString(instructions1.element(), 90, 230);
			g.drawString(instructions2.element(), 90, 280);
			g.drawString(instructions3.element(), 90, 330);
			g.drawString(instructions4.element(), 90, 380);
			g.drawString("press SPACE to continue", 90, 480);
			g.drawString("press ENTER to exit", 90, 520);
			
			
		}else if(gamestate == 1) {
				
			bg.paint(g);
			
		   	g.translate(globalX, globalY);//translate all tiles
		   	
			
		   	map.paint(g);
		   	drawTiles(g);
		   	
			enemyLogic(skells, g);
			enemyLogic(bSlimes, g);
			enemyLogic(sSlimes, g);
			enemyLogic(hSlimes, g);
			enemyLogic(shootSkells, g);
			shoot(shootSkells);
			
			
			for(ProjectileBullet bull : bullets) {
				bull.paint(g);
				if(bull.getTimer() == 120) {
					bullets.remove( bullets.indexOf(bull) );
				}
			}
			
			itemLogic(g);
			
			wall.paint(g);
			for( SurroundWeapon ball : surround) {
				ball.paint(g);
			}

			g.translate(-globalX, -globalY);//translate gui and player back to stay rooted
	
			player.paint(g);
			
			starter.paint(g);
			
			globalX -=player.getVx();
			globalY -=player.getVy();
			g.translate(0, 100);
			UIanim2++;
			UIanim3++;
			
			if(UIanim2 % 5 == 0) {
				UIanim++;
			}
			if(UIanim3 % 10 == 0) {
				UIanim4++;
			}
			if(UIanim4 > 1) {
				UIanim3 = 0;
				UIanim4 = 0;
			}
			
			if(UIanim > 4) {
				UIanim2 = 0;
				UIanim = 0;
			}
			
			
			timeIcon[UIanim4].paint(g);
			GUI[UIanim].paint(g);
	
			drawGui(g);//le gui
			
			startWaves();
			
				  
		}else if(gamestate == 4) {
			
			//game over
			titleAnim++;
			
			if(titleAnim % 8 == 0) {
				titleAnim2++;
			}
			if(titleAnim2 > 3) {
				titleAnim = 0;
				titleAnim2 = 0;
			}
			
			over[titleAnim2].paint(g);
			
			g.setColor(Color.green);
			g.setFont(myFont.deriveFont(40.0f));
			g.drawString(Integer.toString(player.getXp()), 370, 240);
		
		}else if(gamestate == 5) {
			//game over
			titleAnim++;
			
			if(titleAnim % 10 == 0) {
				titleAnim2++;
			}
			if(titleAnim2 > 3) {
				titleAnim = 0;
				titleAnim2 = 0;
			}
			
			loadS[titleAnim2].paint(g);
			
			g.setColor(Color.green);
			g.setFont(myFont.deriveFont(40.0f));
			g.drawString(Integer.toString(highScore), 360, 210);
			
		}
	}

	
	
	/**
	* frame function 
	* creates Jframe, plays bgm, initialized game map
	* starts a weird timer (no idea what that dose)
	*
	* @param none
	*/
	public Frame2() {
		JFrame f = new JFrame("Goo Survivors");//create and name Jframe
		f.setSize(new Dimension(width, height));//set window size
		f.add(this);
		f.setResizable(false);
		f.addKeyListener(this);
		f.getContentPane().setBackground(Color.BLACK); 
	   // backgroundMusic.play();//play bgm
	    
		
		Timer t = new Timer(30, this);//decrease number to make loop faster
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		pathfinding p = new pathfinding();
		snakeLogic(p);
	}
	
	/**
	* draw the gui on screen
	* display lives and score
	*
	* @param Graphics g - for text and squares
	*/
	private void drawGui(Graphics g) {
		
		//square and border for score text
	    g.setFont(myFont.deriveFont(25.0f));
	    g.setColor(Color.GREEN);
	    g.drawString(Integer.toString(player.getHp()), 35,50);
	    g.drawString(Integer.toString(player.getXp()), 740,50);
	    
	    g.drawString(Integer.toString(time.getMins())+" : "+Integer.toString(time.getSecs()), 380,85);
	}
	
	/**
	* initilaize 1t layer of tiles
	* traverses a array of strings, each letter is assigned a tile
	* the tile is added to arraylists that are processed in the drawGrid function
	*
	* @param none
	*/
	private void spawnEnemies(int amnt, ArrayList<Enemy> enemies, String who) {
		for(int i = 0; i< amnt; i++) {
			if(who.toLowerCase().equals("skeletons")) {
				Skeleton s = new Skeleton();
				int randomX = -600 + (int) (Math.random() * ((600 - (-600)) + 1));
				int randomY = -500 + (int) (Math.random() * ((500 - (-500)) + 1));
				s.setX(-globalX+380 + randomX);
				s.setY( -globalY+300 + randomY);
				enemies.add(s);
			}else if(who.toLowerCase().equals("big slimes")) {
				bigSlime s = new bigSlime();
				int randomX = -600 + (int) (Math.random() * ((600 - (-600)) + 1));
				int randomY = -500 + (int) (Math.random() * ((500 - (-500)) + 1));
				s.setX(-globalX+380 + randomX);
				s.setY( -globalY+300 + randomY);
				enemies.add(s);
			}else if(who.toLowerCase().equals("small slimes")) {
				smallSlime s = new smallSlime();
				int randomX = -600 + (int) (Math.random() * ((600 - (-600)) + 1));
				int randomY = -500 + (int) (Math.random() * ((500 - (-500)) + 1));
				s.setX(-globalX+380 + randomX);
				s.setY( -globalY+300 + randomY);
				enemies.add(s);
			}else if(who.toLowerCase().equals("shooting skeletons")) {
				ShootingSkeleton s = new ShootingSkeleton();
				int randomX = -600 + (int) (Math.random() * ((600 - (-600)) + 1));
				int randomY = -500 + (int) (Math.random() * ((500 - (-500)) + 1));
				s.setX(-globalX+380 + randomX);
				s.setY( -globalY+300 + randomY);
				enemies.add(s);
			}else if(who.toLowerCase().equals("huge slimes")) {
				megaSlime s = new megaSlime();
				int randomX = -600 + (int) (Math.random() * ((600 - (-600)) + 1));
				int randomY = -500 + (int) (Math.random() * ((500 - (-500)) + 1));
				s.setX(-globalX+380 + randomX);
				s.setY( -globalY+300 + randomY);
				enemies.add(s);
			}
		}
	}
	
	private void enemyLogic( ArrayList<Enemy> enemies, Graphics g) {
		
		if(starter.getDir()) {
		g.drawRect(-globalX+230, -globalY+300, starter.getWidth(), starter.getHeight());
		starter.setX(-globalX+230);
		starter.setY(-globalY+300);
		}else {
		g.drawRect(-globalX+430, -globalY+300, starter.getWidth(), starter.getHeight());
		starter.setX(-globalX+430);
		}
		
		for( SurroundWeapon ball : surround) {
			ball.setX( (int) (Math.cos( ball.getAngle() / (surround.indexOf(ball) + 1) ) * -(globalX + 380) ) );
			ball.setY( (int) (Math.sin( ball.getAngle() / (surround.indexOf(ball) + 1) ) * (globalY + 250) ) );
			//unfinished spawnlocation
			
		}
		
		if(enemies.size() > 0) {
		for(Enemy s : enemies) {
			s.paint(g);
			if(s.getX() < -globalX+380) {
				s.setVx(s.getSpeed());
			}else {
				s.setVx(-1*s.getSpeed());
			}
			if(s.getY() < -globalY+300) {
				s.setVy(s.getSpeed());
			}else {
				s.setVy(-1*s.getSpeed());
			}
			
			if (starter.collidedWithEnemy(s)) {
	                s.takeDamage(starter.getDmg(), g);
	               
	         }
			
			for( SurroundWeapon balls : surround) {
				if( balls.collidedWithEnemy(s) ) {
					s.takeDamage(balls.getDmg(), g);
				}
			}
			
			for( ProjectileBullet bull : bullets) {
				if( player.collided( bull.getX(), bull.getY(), bull.getWidth(), bull.getHeight() ) ) {
					player.getHurt( bull.getDamage() );
				}
			}
			
			if(s.collided(-globalX+380, -globalY+250, 50, 200)) {

				getHurt(s.getDamage());
				
				
			}
			
			g.drawRect(s.getX(), s.getY(), s.getWidth(), s.getHeight());
			
		}
		for(int i = 0; i< enemies.size(); i++) {
			if(enemies.get(i).getHp() <= 0) {
				enemies.remove(i);
			 player.addXp(10);
			}
			
		}
		}
	}
	
	private void wallLogic() {
		
		wall.setVy(2);
		if(wall.getY() > 1000) {
			wall.setY(-400);
		}
		
	}
	
	
	
	private void shoot(ArrayList<Enemy> something) { //really want to take out the 
		for( Enemy ss : something) {
			if( ss instanceof ShootingSkeleton ) {
				if( ((ShootingSkeleton) ss).getShootingTimer() == 40) {
					ProjectileBullet b = new ProjectileBullet( 10, 20, 20, ((ShootingSkeleton) ss).getX() + 90, ((ShootingSkeleton) ss).getY() + 100, 15);
					bullets.add( b );
					double xDirection = -((globalX-380) - ((ShootingSkeleton) ss).getX() + 90);
					
					double yDirection = ((globalY-200) - ((ShootingSkeleton) ss).getY() + 50);
					
					int length = (int) Math.sqrt(yDirection*yDirection + xDirection*xDirection );
					
				b.setVx((int) (b.getSpeed() * (double)(xDirection / length)) );
				b.setVy((int) (b.getSpeed() * (double)(yDirection / length)) );
			
			}
		}//this is to be put in frame
		
		}
	}

	private void itemLogic(Graphics g) {
		if(items.size() >0) {
			for(int i = 0; i < items.size(); i++) {
				items.get(i).paint(g);
				if(items.get(i).collided(-globalX+380, -globalY+250, 50, 200)) {
					if(items.get(i).getType() == 0) {
						starter.setDmg(starter.getDmg() + 5);
						surround.add(new SurroundWeapon(10));
					}else {
						player.getHurt(-10);
					
					}
					items.remove(i);
				}
			}
		}
	}




	private void startWaves() {
		if(time.getMins() == 5 && time.getSecs() == 30) {
			if(waves.get("wave1") != null) {
				spawnWave(waves.remove("wave1"));//wave 1
			}
		}else if(time.getMins() == 5 && time.getSecs() == 0) {
			if(waves.get("wave2") != null) {
			spawnWave(waves.remove("wave2"));//wave 2
			}
		}else if(time.getMins() == 4 && time.getSecs() == 30) {
			if(waves.get("wave3") != null) {
			spawnWave(waves.remove("wave3"));//wave 3
			}
		}else if(time.getMins() == 4 && time.getSecs() == 0) {
			if(waves.get("wave4") != null) {
			spawnWave(waves.remove("wave4"));//wave 4
			}
		}else if(time.getMins() == 3 && time.getSecs() == 30) {
			if(waves.get("wave5") != null) {
			spawnWave(waves.remove("wave5")); //wave 5
			}
		}else if(time.getMins() == 3 && time.getSecs() == 00) {
			if(waves.get("wave6") != null) {
			spawnWave(waves.remove("wave6")); //wave 5
			}
		}else if(time.getMins() == 2 && time.getSecs() == 30) {
			if(waves.get("wave7") != null) {
			spawnWave(waves.remove("wave7")); //wave 5
			}
		}else if(time.getMins() == 2 && time.getSecs() == 00) {
				if(waves.get("wave8") != null) {
				spawnWave(waves.remove("wave8")); //wave 5
				}
		}else if(time.getMins() == 1 && time.getSecs() == 30) {
			if(waves.get("wave9") != null) {
			spawnWave(waves.remove("wave9")); //wave 5
			}
		}else if(time.getMins() == 1 && time.getSecs() == 0) {
			if(waves.get("wave10") != null) {
			spawnWave(waves.remove("wave10")); //wave 5
			}
		}
		else if(time.getMins() <= 0 && time.getSecs() <= 0) {
			wallLogic();
		}
		
	
	}
	
	private void spawnWave(int[] wave) {
		for(int i = 0; i< wave.length; i++) {
			if(wave[i] == 1) {
				spawnEnemies(1, sSlimes, "small slimes");
			}
			else if(wave[i] == 2) {
				spawnEnemies(1, bSlimes, "big slimes");
			}
			else if(wave[i] == 3) {
				spawnEnemies(1, skells, "skeletons");
			}
			else if(wave[i] == 4) {
				spawnEnemies(1, shootSkells, "shooting skeletons");
			}
			else if(wave[i] == 5) {
				spawnEnemies(1, hSlimes, "huge slimes");
			}
		}
		
	}
	
	/**
	* initilaize 1t layer of tiles
	* traverses a array of strings, each letter is assigned a tile
	* the tile is added to arraylists that are processed in the drawGrid function
	*
	* @param none
	*/
	public void initTiles(File map) {//initialize 1st layer of tiles
		String[] lines = new String[27];
		String tot = "";
		 
		 try {
			Scanner sc = new Scanner(map);
			
			 while (sc.hasNextLine()) {
		            String l = sc.nextLine();
		            tot+= l;
		           
			 }
			 lines = tot.split("/n");
			 
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
		  
		  for(int row = lines.length -1; row >= 0; row--){//traverse rows of strings
		    String[] values = lines[row].split(",");//split each into individual letters
		    for(int col = 0; col < values.length ; col++){///traverse collumns of string
		    	
		    	
		    	//read string and create object in arraylist based on letter or number
		    	if(values[col].equals("1")){
		    		Tile s = new Tile(0,0,Integer.parseInt(values[col]));
		    		s.setX((row*75)-400);
		    		s.setY((col*75)-350);
		    		tiles[row][col] = s;
		    	}else if(values[col].equals("2")){
		    		Tile s = new BreakableTile(0,0,Integer.parseInt(values[col]));
		    		s.setX((row*75)-400);
		    		s.setY((col*75)-350);
		    		tiles[row][col] = s;
		    	}
		    }
		  }	
	}
	
	public void snakeLogic(pathfinding p) {
			p.maze = p.loadMaze(name, p.rows, p.cols);
			p.sol = p.findtotalPath(p.maze);
		
	}
	public void drawTiles(Graphics g) {
		g.setColor(Color.RED);
		g.drawRect(-globalX+380, -globalY+250, 50, 200);
		for(int r = 0; r < tiles.length; r++) {
			for(int c = 0; c<tiles[0].length; c++) {
				if(tiles[r][c] != null) {
					tiles[r][c].paint(g);
					if((tiles[r][c].collided(-globalX+380,-globalY+250,50,200))){
						
						if(tiles[r][c].getX() + (75/2) > -globalX+380) {
							globalX++;
						}else {
							globalX--;
						}
						
						if(tiles[r][c].getY() + (75/2) >= -globalY+250) {
							globalY++;
						}else {
							globalY--;
						}
						
						if(tiles[r][c].getType() == 2) {
							((BreakableTile) tiles[r][c]).takeDamage(starter.getDmg(), g);
						}
						
						
						
						player.setVx(0);
						player.setVy(0);
					}
					
					if(tiles[r][c].getType() == 2) {
					
						if (starter.collidedGeneral((r*75)-400,(c*75)-350,75,75)) {
							((BreakableTile) tiles[r][c]).takeDamage(starter.getDmg(), g);
			                
			         }
						if(((BreakableTile) tiles[r][c]).getHp()<=0) {
							int rand = (int)(Math.random() * 5) + 1;
							if(rand == 1) {
								player.addXp(5);
							}else if(rand == 2) {
								Item i = new Item( tiles[r][c].getX(),  tiles[r][c].getY(), 1);
								i.setX((r*75)-400);
					    		i.setY((c*75)-350);
					    		healthVials++;
								items.add(i);
							}else if(rand == 3) {
							Item i = new Item( tiles[r][c].getX(),  tiles[r][c].getY(), 1);
							i.setX((r*75)-400);
				    		i.setY((c*75)-350);
				    		healthVials++;
							items.add(i);
							}else if(rand == 4) {
								player.addXp(20);
							}else if(rand == 5) {
								Item i = new Item( tiles[r][c].getX(),  tiles[r][c].getY(), 0);
								i.setX((r*75)-400);
					    		i.setY((c*75)-350);
					    		dmgVials++;
								items.add(i);
							}
							tiles[r][c] = null;

						}
					}

				
				
				}
			}
		}
	}
	
	/**
	* get hurt
	* play hurt sfx and reset position
	* decrease lives and score
	* if lives <0, trigger game over
	*
	* @param none
	*/
	public void getHurt(int dmg) {
		if(player.getHp() > 1) {
			playSfx("hit.wav");
			player.getHurt(dmg);
		}else {
			load.save(healthVials, dmgVials, player.getXp());
			gamestate = 4;
		}
	}
	
	/**
	* reset
	* reset game to intial state
	*
	* @param none
	*/
	public void reset(){
		
		
		
		skells.clear();
		bSlimes.clear();
		sSlimes.clear();
		hSlimes.clear();
		shootSkells.clear();
		bullets.clear();
		items.clear();
		surround.clear();
	
		globalX = 300;
		globalY = 200;
		
		healthVials = load.getPlayerHp();
		dmgVials = load.getDamage();
		
		gamestate = 0;
		
		time = new CountdownTimer();
		
		for(int r = 0; r < tiles.length; r++) {
			for(int c = 0; c < tiles[0].length; c++) {
				tiles[r][c] = null;
			}
		}
		name = "map"+ (int)((Math.random() * 3) + 1) +".txt";
		tilemap = new File(name);
		
		initTiles(tilemap);
		
		player.setXp(0);
		initWaves();
		load.load();
		player.setHp(100+load.getPlayerHp()*10);
		starter.setDmg(1+load.getDamage()*10);
		highScore = load.getHighScore();
		
	}
	
	/**
	* play sound effect once
	*
	* @param String filename - filename of sound to be played
	*/
	public void playSfx(String filename) {
		SimpleAudioPlayer sfx = new SimpleAudioPlayer(filename, false);//make new obj
		sfx.play();//play it
	}
	
	@Override
	public void keyTyped(KeyEvent arg0) {
	
	
	
	}
	
	/**
	* key released
	* controlls everything in the game
	* key released instead of key pressed so you can't keep key pushed
	*
	* @param KeyEvent arg0 - key currently pushed
	*/
	@Override
	public void keyReleased(KeyEvent arg0) {
		if(arg0.getKeyCode() == 38) {//up
			
				if(gamestate == 1) {
				player.setVy(0);
			}
		}
		 if(arg0.getKeyCode() == 40) {//down
			if(gamestate == 1) {
				player.setVy(0);
			}
		 }
		  if(arg0.getKeyCode() == 37) {//left
			if(gamestate == 1) {
				player.setVx(0);
			}
		}
		  if(arg0.getKeyCode() == 39) {//right
			if(gamestate == 1) {
				player.setVx(0);
			}
		}
	 if(arg0.getKeyCode() == 32) {//spacebar
		
	 	}
		
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		if(arg0.getKeyCode() == 38) {//up
			if(gamestate == 0) {
				if(titleIndex > 0) {
					titleIndex--;
				}
			}else if(gamestate == 1) {
				player.setVy(-4);
			}
		}  if(arg0.getKeyCode() == 40) {//down
			if(gamestate == 0) {
				if(titleIndex < 2) {
					titleIndex++;
				}
	
			}else	if(gamestate == 1) {
				player.setVy(4);
			}
		}  if(arg0.getKeyCode() == 37) {//left
			if(gamestate == 1) {
				player.setVx(-4);
			}
		}  if(arg0.getKeyCode() == 39) {//right
			if(gamestate == 1) {
				player.setVx(4);
			}
		}
		
	 if(arg0.getKeyCode() == 32) {//spacebar
		//select button
		 if(gamestate == 0) {
				if(titleIndex == 0) {
					gamestate = 1;
					titleAnim = 0;
					titleAnim2 = 0;
					time.start();
				}else if(titleIndex == 1) {
					gamestate = 5;
				}else if(titleIndex == 2) {
					gamestate = 2;
					titleAnim = 0;
					titleAnim2 = 0;
				}
				initTiles(tilemap);
		 	}else if(gamestate == 2) {
		 		instructions1.add(instructions1.poll());
		 		instructions2.add(instructions2.poll());
		 		instructions3.add(instructions3.poll());
		 		instructions4.add(instructions4.poll());
		 	}else if(gamestate == 5) {
				 load.clear();
				 gamestate = 0;
			 }else if(gamestate == 4) {
				 reset();
			 }
	 	}
	 
	 if(arg0.getKeyCode() == 10) {
		 if(gamestate ==2) {
		 gamestate = 0;
		 }else if(gamestate ==5) {
			 load.load();
			 player.setHp(100+load.getPlayerHp()*10);
			 starter.setDmg(1+load.getDamage()*10);	
			healthVials = load.getPlayerHp();
			dmgVials = load.getDamage();
			 highScore = load.getHighScore();
			 gamestate = 0;
		 }
	 }
	 //System.out.println(arg0.getKeyCode());
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();//this is important i think
	}
	
}