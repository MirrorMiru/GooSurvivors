package logic;

import java.awt.BasicStroke;
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/******************************************************************
 * Program: Corporate Mandated Holliday                           *
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

public class Frame extends JPanel implements ActionListener, KeyListener  {
	



	
	Player player = new Player();
   			
	//le title screen
	Sprite[] titleScreen = {new Sprite("/img/ts1.png",0,0,800,700), new Sprite("/img/ts2.png",0,0,800,700),new Sprite("/img/ts3.png",0,0,800,700),new Sprite("/img/ts4.png",0,0,800,700)};
	Sprite[] cursor = {new Sprite("/img/title1.png",0,0,800,700), new Sprite("/img/title2.png",0,0,800,700),new Sprite("/img/title3.png",0,0,800,700)};
	//le how to screen
	Sprite[] inst = {new Sprite("/img/inst1.png",0,0,800,700), new Sprite("/img/inst2.png",0,0,800,700),new Sprite("/img/inst3.png",0,0,800,700),new Sprite("/img/inst4.png",0,0,800,700)};
	
	Sprite[] GUI = {new Sprite("/img/gui1.png",0,0,800,700), new Sprite("/img/gui2.png",0,0,800,700),new Sprite("/img/gui3.png",0,0,800,700),new Sprite("/img/gui4.png",0,0,800,700),new Sprite("/img/gui5.png",0,0,800,700)};
	//le font
	Font myFont = new Font("Courier", Font.BOLD, 40);
	
	Sprite map = new Sprite("/img/map.png",-400,-350,2000,2000);
	
	Sprite bg = new Sprite("/img/permabg.png",0,0,800,700);
	
	//bgm, starts looping immediately and never stops
	//SimpleAudioPlayer backgroundMusic = new SimpleAudioPlayer("LabyrinthFight.wav", true);
	
	//frame width/height
	int width = 815;
	int height = 735;	
	
	int waveTimer = 0;
	int wave = 0;
	
	
	int score = 0;//game score, this should be self explanatory
	int lives = 3;//lives
	
	int gamestate = 0;//gamestate
	//0 = main menu
	//2 = how to play
	//1 = gameplay
	//4 = game over
	//5 = victory screen
	
	int titleIndex = 0; //array index used for title screen cursor
	int titleAnim = 0;
	int titleAnim2 = 0;
	
	int UIanim = 0;
	int UIanim2 = 0;

	
	int globalX = 300;
	int globalY = 200;
	
	//enemy arraylists
	ArrayList<Enemy> skells = new ArrayList<Enemy>();
	ArrayList<Enemy> bSlimes = new ArrayList<Enemy>();
	ArrayList<Enemy> sSlimes = new ArrayList<Enemy>();
	
	GameLoader load = new GameLoader();
	
	Tile[][] tiles = new Tile[27][27];
	
	

	boolean invunrebility = false; //player cannot die when true
	
	
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
			
		}else if(gamestate == 1) {
				
			bg.paint(g);
			
		   	g.translate(globalX, globalY);//translate all tiles
		   	
			
		   	map.paint(g);
		   	drawTiles(g);
		   	
			enemyLogic(skells, g);
			enemyLogic(bSlimes, g);
			enemyLogic(sSlimes, g);
			
			
			
			g.translate(-globalX, -globalY);//translate gui and player back to stay rooted
	
			player.paint(g);		
			
			globalX -=player.getVx();
			globalY -=player.getVy();
			g.translate(0, 100);
			UIanim2++;
			
			if(UIanim2 % 5 == 0) {
				UIanim++;
			}
			
			if(UIanim > 4) {
				UIanim2 = 0;
				UIanim = 0;
			}
			GUI[UIanim].paint(g);
	
			drawGui(g);//le gui
			
			startWaves();
			
				  
		}else if(gamestate == 4) {
			
		//	over.paint(g);//display game over image
		
		}else if(gamestate == 5) {
			
		//	win.paint(g);//win screen
			
			//display score at end of game
			g.setFont(myFont.deriveFont(40.0f));
			g.drawString(Integer.toString(score), 250, 140);
		}
	}

	/**
	* main function 
	* runs and updates Jframe
	*
	* @param String arg (i have no idea what this dose)
	*/
	public static void main(String[] arg) throws IOException {
		Frame f = new Frame();		
	}
	
	
	/**
	* frame function 
	* creates Jframe, plays bgm, initialized game map
	* starts a weird timer (no idea what that dose)
	*
	* @param none
	*/
	public Frame() {
		JFrame f = new JFrame("Goo Survivors");//create and name Jframe
		f.setSize(new Dimension(width, height));//set window size
		f.add(this);
		f.setResizable(false);
		f.addKeyListener(this);
		f.getContentPane().setBackground(Color.BLACK); 
	   // backgroundMusic.play();//play bgm
	    
		
		Timer t = new Timer(16, this);//decrease number to make loop faster
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
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
				enemies.add(new Skeleton());
			}else if(who.toLowerCase().equals("big slimes")) {
				enemies.add(new bigSlime());
			}else if(who.toLowerCase().equals("small slimes")) {
				enemies.add(new smallSlime());
			}
		}
	}
	
	private void enemyLogic( ArrayList<Enemy> enemies, Graphics g) {
		if(enemies.size() > 0) {
		for(Enemy s : enemies) {
			s.paint(g);
			if(s.getX() < -globalX+300) {
				s.setVx(s.getSpeed());
			}else {
				s.setVx(-1*s.getSpeed());
			}
			if(s.getY() < -globalY+300) {
				s.setVy(s.getSpeed());
			}else {
				s.setVy(-1*s.getSpeed());
			}
		}
		for(int i = 0; i< enemies.size(); i++) {
			//if(skels.get(i).getHp() <= 0) {
			///	skels.remove(i);
			/// player.addXp(10);
			//}
		}
		}
	}
	
	private void startWaves() {
		waveTimer++;
		
		if(waveTimer == 20) {
			spawnEnemies(5, sSlimes, "small slimes");
		}
		if(waveTimer == 220) {
			spawnEnemies(1, bSlimes, "big slimes");
		}
		if(waveTimer == 500) {
			spawnEnemies(1, skells, "skeletons");
		}
	}
	
	/**
	* initilaize 1t layer of tiles
	* traverses a array of strings, each letter is assigned a tile
	* the tile is added to arraylists that are processed in the drawGrid function
	*
	* @param none
	*/
	public void initTiles() {//initialize 1st layer of tiles
		  String[] lines = {//game map 27x27
				  "1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1",
				  "1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1",
				  "1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1",
				  "1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1",
				  "1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1",
				  "1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1",
				  "1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1",
				  "1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1",
				  "1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1",
				  "1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1",
				  "1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1",
				  "1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1",
				  "1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1",
				  "1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1",
				  "1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1",
				  "1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1",
				  "1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1",
				  "1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1",
				  "1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1",
				  "1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1",
				  "1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1",
				  "1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1",
				  "1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1",
				  "1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1",
				  "1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1",
				  "1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1",
				  "1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1"
		  };
		  
		  for(int row = lines.length -1; row >= 0; row--){//traverse rows of strings
		    String[] values = lines[row].split(",");//split each into individual letters
		    for(int col = 0; col < values.length ; col++){///traverse collumns of string
		    	
		    	
		    	//read string and create object in arraylist based on letter or number
		    	if(values[col].equals("1")){
		    		Tile s = new Tile(0,0,Integer.parseInt(values[col]));
		    		s.setX((row*75)-400);
		    		s.setY((col*75)-350);
		    		tiles[row][col] = s;
		    	}
		    }
		  }	
	}
	
	public void drawTiles(Graphics g) {
		g.setColor(Color.RED);
		g.drawRect(-globalX+380, -globalY+250, 50, 200);
		for(int r = 0; r < tiles.length; r++) {
			for(int c = 0; c<tiles[0].length; c++) {
				if(tiles[r][c] != null) {
					tiles[r][c].paint(g);
					if((tiles[r][c].collided(-globalX+380,-globalY+250,50,200))){
					
						player.setVx(0);
						player.setVy(0);
						if(globalX < 0) {
						globalX++;
						}else {
							globalX--;
						}
						if(globalY < 0) {
						globalY ++;
						}else {
							globalY--;
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
	public void getHurt() {
		if(lives > 1) {
			//playSfx("hit.wav");
			player.getHurt(1);
		}else {
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
		gamestate = 0;
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
		
		if(arg0.getKeyCode() == 112) {//right
			if(gamestate == 1) {
				ArrayList<Integer> TEMP = new ArrayList<Integer>();
				TEMP.add(1);
				TEMP.add(2);
				load.save(player.getHp(),wave,player.getXp(),TEMP);
				System.out.println("save");
			}
		}
	 if(arg0.getKeyCode() == 32) {//spacebar
		//select button
		 if(gamestate == 0) {
				if(titleIndex == 0) {
					gamestate = 1;
					titleAnim = 0;
					titleAnim2 = 0;
				}else if(titleIndex == 1) {
					load.load();
					player.setHp(load.getPlayerHp());
					player.setXp(load.getPlayerXp());
				}else if(titleIndex == 2) {
					gamestate = 2;
					titleAnim = 0;
					titleAnim2 = 0;
				}
				initTiles();
		 	}
	 	}
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();//this is important i think
	}
	

}
