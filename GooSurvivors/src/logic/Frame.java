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

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener  {
	



	
	Player player = new Player();
   			
	//le font
	Font myFont = new Font("Courier", Font.BOLD, 40);
	
	//bgm, starts looping immediately and never stops
	//SimpleAudioPlayer backgroundMusic = new SimpleAudioPlayer("LabyrinthFight.wav", true);
	
	//frame width/height
	int width = 780;
	int height = 800;	
	
	int score = 0;//game score, this should be self explanatory
	int lives = 3;//lives
	
	int gamestate = 1;//gamestate
	//0 = main menu
	//2 = story cutscene
	//1 = gameplay
	//4 = game over
	//5 = victory screen
	
	int titleIndex = 0; //array index used for title screen cursor
	

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
		//title screen logic
		
		}else if(gamestate == 2) {
			
		//optional story images or game controlls
			
		}else if(gamestate == 1) {
			
			//background.paint(g);//display background image
			//under everything
			//never translates
			
		  // 	g.translate(globalX, globalY);//translate all tiles
		   	
			//grid draw here
			  
		//	g.translate(-globalX, -globalY);//translate gui and player back to stay rooted

			player.paint(g);
			//drawGui(g);//le gui
				  
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
		f.setBackground(Color.white);
		f.add(this);
		f.setResizable(false);
 		f.addMouseListener(this);
		f.addKeyListener(this);
	
	   // backgroundMusic.play();//play bgm
	    
		
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
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

		//how much everything gets moved by
		int movebyX = 128/2;
		int movebyY = 64/4;

		if(arg0.getKeyCode() == 38) {//up
			
			
			if(gamestate == 1 && player.getVy() == 0) {//player movement
				player.jump(0);
			}
			
		}else if(arg0.getKeyCode() == 40) {//down
			
			if(gamestate == 1 && player.getVy() == 0) {
				player.jump(1);
	
			}
			
		}else if(arg0.getKeyCode() == 37) {//left
			if(gamestate == 1 && player.getVy() == 0) {//player movement 
				player.jump(2);

			}
			
		}else if(arg0.getKeyCode() == 39) {//right
			if(gamestate == 1 &&  player.getVy() == 0) {//player movement
				player.jump(3);

			}
			
		}else if(arg0.getKeyCode() == 32) {//spacebar
		//select button
		}
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
	    g.setColor(Color.WHITE);
	    g.fillRect(0,0,200,50);
	    g.setColor(Color.BLACK);
	    g.drawRect(0,0,200,50);
	    g.drawString("SCORE: "+score, 20,30);
	    
	    //same thing but with lives
	    g.setColor(Color.WHITE);
	    g.fillRect(600,0,200,50);
	    g.setColor(Color.BLACK);
	    g.drawRect(600,0,200,50);
	    g.drawString("LIVES: "+lives, 620,30);
	}
	
	/**
	* draw grid
	* paints: enemies, tiles, moving platforms, socks, colliders
	* runs all collsion code
	* runs all object logic code
	* pretty much contains major game logic
	*
	* @param Graphics g - for object paint functions
	*/
	private void drawGrid(Graphics g) {
		 
		for(Tile t: tiles) {//game tiles
			t.paint(g);
		}
		
		for(Platform p: moving) {//moving platform
			
			p.paint(g);
			
			for(Border b: bouncersL) {//colliders that reverse mp velocity
				if(p.collided(b.getHitbox())) {
					p.setVx(128/2);
					p.setVy(64/4);
				}
			}
			
			for(Border b: bouncersR) {//colliders that reverse mp velocity
				if(p.collided(b.getHitbox())) {
					p.setVx(-128/2);
					p.setVy(-64/4);
				}
			}
			
			boolean moved = p.getImpulse();//checks of moving platform moved or is stationary
		
			//only moves when platform moves
			if(moved && invunrebility) {//move world with the same velocity as the platform when the player is sanding on it
				if(p.getVx() > 0){
					globalX -= 64;
					globalY -= 16;
				}else {
					globalX += 64;
					globalY += 16;
				}
				
			}
			
			//player cannot die if touching platform
			if(p.collided(player.getX()-globalX, player.getY()+130-globalY,70,10) && !player.getInAir()) {
				invunrebility = true;
				delay = 5;
			}else {
				if(delay <= 0) {
					invunrebility = false;
					delay = 0;
				}else {
					delay--;
				}
				
			}
		}
		
		//display and player collsion for enemies
		for(int i = 0; i< enemies.length; i++) {//1st row of enemies
			enemies[i].paint(g);//should be self explanatory
				
			if((enemies[i].collided((player.getX()-globalX), player.getY()+130-globalY,70,10))&&player.getVy()==0){
				getHurt();//should also be self explanatory by now
			}
		}
		
		   for(int i = 0; i< enemies2.length; i++) {//2nd row of enemies
				enemies2[i].paint(g);
				
				if((enemies2[i].collided((player.getX()-globalX), player.getY()+130-globalY,70,10))&&player.getVy()==0){
					getHurt();
			
				}
			}
		   for(int i = 0; i< enemies3.length; i++) {//3rd row of enemies
				enemies3[i].paint(g);
				
				if((enemies3[i].collided((player.getX()-globalX), player.getY()+130-globalY,70,10))&&player.getVy()==0){
					getHurt();
				}
			}
	
		   //loop enemy back to the begining of row using a loop
			for(EnemyOne enemy : enemies) {//1st row
					for(Border b : breakers) {
						if(b.getModifier() == 1) {
							if(enemy.collided(b.getHitbox())) {
								enemy.setX(enemy.getOriginX());//reset back to spawn position
								enemy.setY(enemy.getOriginY());			
							}
						}
				}  
			}
			
			for(EnemyOne enemy : enemies2) {//2nd row
				for(Border b : breakers) {
					if(b.getModifier() == 2) {
						if(enemy.collided(b.getHitbox())) {
							enemy.setX(enemy.getOriginX());
							enemy.setY(enemy.getOriginY());	
						}
					}
			}
		}
			
			for(EnemyOne enemy : enemies3) {//3rd row
				for(Border b : breakers) {
					if(b.getModifier() == 3) {
						if(enemy.collided(b.getHitbox())) {
							enemy.setX(enemy.getOriginX());
							enemy.setY(enemy.getOriginY());	
						}
					}
			}
		}
			
		//sock collision and logic
		for(int i=socks.size()-1; i>-1; i--) {//for some reason it errors with a for each loop
			socks.get(i).paint(g);//display them
			if(socks.get(i).collided(player.getX()-globalX, player.getY()+130-globalY,70,10) && player.getVy() == 0){
				score+=50;//increase score when collected
				playSfx("sock.wav");//beep boop
				socks.remove(i);//sock is kil. no.
			}
		}
		
		//there's only 1 present so no need for a loop
		pres.paint(g);
		if(pres.collided(player.getX()-globalX, player.getY()+130-globalY,70,10) && player.getVy() == 0){
			playSfx("end.wav");//boop beep
			score+=100;
			gamestate = 5;//end the game when present is collected
		}
		
		for(Border x: borders) {//invisible borders that kil player
		
			if(x.collided(player.getX()-globalX, player.getY()+130-globalY,70,10) && player.getVy() == 0){
				if(!invunrebility) {
				getHurt();//die.
				}
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
	public void initTiles() {//initialize 1st layer of tiles
		  String[] lines = {//game map
				  "X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X"//X = border
			  	, "X,X,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,X,X"//2 = red tile
			    , "X,X,2,1,1,1,1,1,1,1,1,1,1,1,1,1,2,X,X"//1 = grey tile
		  		, "X,X,2,1,1,1,1,1,1,1,1,1,1,1,1,1,2,X,X"//3 = black tile
				, "X,X,2,1,1,1,1,1,1,1,1,1,1,1,1,1,2,X,X"//4 = yellow tile
		  		, "X,X,2,1,1,1,1,1,1,1,1,1,1,1,1,1,2,X,X"//5 = red slab
				, "X,X,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,X,X"//anything else = white space
		  		, "X,X,3,3,4,4,3,3,4,4,3,3,4,4,3,3,4,X,X"
				, "X,X,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,X,X"
		  		, "X,X,2,1,1,1,1,1,1,1,1,1,1,1,1,1,2,X,X"		
		  		, "0,X,X,2,1,1,1,1,1,1,1,1,1,1,1,2,X,X,0"
		  		, "0,X,X,2,1,1,1,1,1,1,1,1,1,1,1,2,X,X,0"
		  		, "0,X,X,2,1,1,1,1,1,1,1,1,1,1,1,2,X,X,0"
		  		, "X,X,2,1,1,1,1,1,1,1,1,1,1,1,1,1,2,X,X"
				, "X,X,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,X,X"
		  		, "X,X,3,3,4,4,3,3,4,4,3,3,4,4,3,3,4,X,X"
				, "X,X,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,X,X"
				, "X,X,2,1,1,1,1,1,1,1,1,1,1,1,1,1,2,X,X"
		  		, "X,X,2,1,1,1,1,1,1,1,1,1,1,1,1,1,2,X,X"
		  		, "X,X,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,X,X"
		  		, "X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X"
		  		, "X,X,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,X,X"	
		  		, "X,X,2,1,1,1,1,1,1,1,1,1,1,1,1,1,2,X,X"		
		  		, "X,X,2,1,1,1,1,1,1,1,1,1,1,1,1,1,2,X,X"		
		  		, "0,X,X,2,1,1,1,1,1,1,1,1,1,1,1,2,X,X,0"
		  		, "0,X,X,2,1,1,1,1,1,1,1,1,1,1,1,2,X,X,0"
		  		, "0,X,X,2,1,1,1,1,1,1,1,1,1,1,1,2,X,X,0"
		  		, "X,X,2,1,1,1,1,1,1,1,1,1,1,1,1,1,2,X,X"
				, "X,X,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,X,X"
		  		, "X,X,3,3,4,4,3,3,4,4,3,3,4,4,3,3,4,X,X"
				, "X,X,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,X,X"
				, "X,X,2,1,1,1,1,1,1,1,1,1,1,1,1,1,2,X,X"
		  		, "X,X,X,2,1,1,1,1,1,1,1,1,1,1,1,2,X,X,X"
		  		, "X,X,X,X,2,1,1,1,1,1,1,1,1,1,2,X,X,X,X"
		  		, "X,X,X,X,X,2,1,1,1,1,1,1,1,2,X,X,X,X,X"
		  		, "X,X,X,X,X,X,2,1,1,1,1,1,2,X,X,X,X,X,X"
		  		, "X,X,X,X,X,X,X,2,1,1,1,2,X,X,X,X,X,X,X"
		  		, "X,X,X,X,X,X,X,X,2,2,2,X,X,X,X,X,X,X,X"
		  };
		  
		  for(int row = lines.length -1; row >= 0; row--){//traverse rows of strings
		    String[] values = lines[row].split(",");//split each into individual letters
		    for(int col = 0; col < values.length ; col++){///traverse collumns of string
		    	
		    	//convert x and y coords to isometric coords
		    	int isoX = (row+col) * (128/2);
		    	int isoY = (col-row) * (64/4);
		    	
		    	//read string and create object in arraylist based on letter or number
		    	if(values[col].equals("1")){
		    		Tile s = new Tile(0,0,Integer.parseInt(values[col]));
		    		s.setX(isoX);
		    		s.setY(isoY);
		    		tiles.add(s);
		    	}else if(values[col].equals("2")){
		    		Tile s = new Tile(0,0,Integer.parseInt(values[col]));
		    		s.setX(isoX);
		    		s.setY(isoY);
		    		tiles.add(s);
		    	}else if(values[col].equals("3")){
				   Tile s = new Tile(0,0,Integer.parseInt(values[col]));
				   s.setX(isoX);
				   s.setY(isoY);
				   tiles.add(s);
				} else if(values[col].equals("4")){
				   Tile s = new Tile(0,0,Integer.parseInt(values[col]));
				   s.setX(isoX);
				   s.setY(isoY);
				   tiles.add(s);
				}else if(values[col].equals("5")){
				   Tile s = new Tile(0,0,Integer.parseInt(values[col]));
				   s.setX(isoX);
				   s.setY(isoY);
				   tiles.add(s);
				}else if(values[col].equals("X")){
				   Border s = new Border(0,0);
				   s.setX(isoX);
				   s.setY(isoY);
				   borders.add(s);
				 }
		    }
		  }	
	}
	
	/**
	* initilaize 2nd layer of tiles
	* traverses a array of strings, each letter is assigned a tile
	* the tile is added to arraylists that are processed in the drawGrid function
	* im not commenting this one, its the same as the one above
	*
	* @param none
	*/
	public void initTilesL2() {
		  String[] lines = {	
				  	  "X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X"
				  	, "X,X,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,X,X"
				    , "X,X,2,1,1,1,1,1,1,1,1,1,1,1,1,1,2,X,X"
			  		, "X,X,2,1,1,1,1,1,1,1,1,1,1,1,1,1,2,X,X"
					, "X,X,2,1,1,1,1,1,1,1,1,1,1,1,1,1,2,X,X"
			  		, "X,X,2,1,1,1,1,1,1,1,1,1,1,1,1,1,2,X,X"
					, "X,B,1,3,3,3,3,3,3,3,3,3,3,3,3,3,3,B,1"
			  		, "X,B,1,0,0,4,3,0,2,4,3,0,1,4,0,0,0,B,1"
					, "X,O,1,E1,0,3,3,E1,1,3,3,E1,2,3,3,1,0,B,1"
			  		, "X,X,2,1,1,S,1,1,1,S,1,1,1,S,1,1,2,X,X"		
			  		, "0,X,X,2,1,1,1,1,1,1,1,1,1,1,1,2,X,X,0"
			  		, "0,X,X,2,1,1,1,1,1,1,1,1,1,1,1,2,X,X,0"
			  		, "0,X,X,2,1,1,1,1,1,1,1,1,1,1,1,2,X,X,0"
			  		, "X,X,2,1,1,1,1,1,1,1,1,1,1,1,1,1,2,X,X"
					, "X,X,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,B,2"
			  		, "X,X,3,3,4,4,3,3,4,4,3,3,4,4,3,3,4,B,2"
					, "X,O,2,E2,0,3,E2,1,0,3,E2,2,2,3,E2,3,3,B,2"
					, "X,X,2,1,S,1,1,1,S,1,1,1,S,1,1,1,2,X,X"
			  		, "X,X,2,1,1,1,1,1,1,1,1,1,1,1,1,1,2,X,X"
			  		, "X,X,L,2,2,2,2,2,2,2,2,2,2,2,2,2,R,X,X"
			  		, "X,X,L,X,X,X,X,X,X,P,40,X,X,X,X,X,R,X,X"
			  		, "X,X,L,1,1,1,1,1,1,1,1,1,1,1,1,1,R,X,X"	
			  		, "X,X,2,1,1,1,1,1,1,1,1,1,1,1,1,1,2,X,X"		
			  		, "X,X,2,1,1,1,1,1,1,1,1,1,1,1,1,1,2,X,X"		
			  		, "0,X,X,2,1,1,S,1,1,S,1,1,S,1,1,2,X,X,0"
			  		, "0,X,X,2,1,1,1,1,1,1,1,1,1,1,1,2,X,X,0"
			  		, "0,X,X,2,1,1,1,1,1,1,1,1,1,1,1,2,X,X,0"
			  		, "X,X,2,1,1,1,1,1,1,1,1,1,1,1,1,1,2,X,X"
					, "X,X,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,B,3"
			  		, "X,X,3,3,4,4,3,3,4,4,3,3,4,4,3,3,4,B,3"
					, "X,O,3,E3,0,3,E3,1,3,E3,2,3,3,3,3,3,3,B,3"
					, "X,X,2,1,S,S,1,1,S,S,1,1,S,S,1,1,2,X,X"
			  		, "X,X,X,2,1,1,1,1,1,1,1,1,1,1,1,2,X,X,X"
			  		, "X,X,X,X,2,1,1,1,1,1,1,1,1,1,2,X,X,X,X"
			  		, "X,X,X,X,X,2,1,1,1,FIN,1,1,1,2,X,X,X,X,X"
			  		, "X,X,X,X,X,X,2,1,1,1,1,1,2,X,X,X,X,X,X"
			  		, "X,X,X,X,X,X,X,2,1,1,1,2,X,X,X,X,X,X,X"
			  		, "X,X,X,X,X,X,X,X,2,2,2,X,X,X,X,X,X,X,X"	
		  };
		  for(int row = lines.length -1; row >= 0; row--){
		    String[] values = lines[row].split(",");
		    for(int col = 0; col < values.length ; col++){
		    	int isoX = (row+col) * (128/2);
		    	int isoY = (col-row) * (64/4);
		    	
		      if(values[col].equals("P")){
		        Platform p = new Platform(Integer.parseInt(values[col+1]));
		        p.setX(isoX);
		        p.setY(isoY);
		        moving.add(p);
		      }else if(values[col].equals("B")){
			    Border b = new Border(0,0);
			    b.setX(isoX);
			    b.setY(isoY);
			    b.setModifier(Integer.parseInt(values[col+1]));
			    breakers.add(b);
			  }else if(values[col].equals("O")){
				Border b = new Border(0,0);
				b.setX(isoX);
				b.setY(isoY);
				b.setModifier(Integer.parseInt(values[col+1]));
				makers.add(b);
			  }else if(values[col].equals("L")){
				Border s = new Border(0,0);
				s.setX(isoX);
				s.setY(isoY);
				bouncersL.add(s); 
			  }else if(values[col].equals("R")){
				Border s = new Border(0,0);
				s.setX(isoX);
				s.setY(isoY);
				bouncersR.add(s); 
		      }else if(values[col].equals("S")){
				Sock s = new Sock(0,0);
				s.setX(isoX);
				s.setY(isoY);
				socks.add(s);
		      }else if(values[col].equals("FIN")){
		    	pres.setX(isoX);
		    	pres.setY(isoY);
		      }else if(values[col].equals("E1")){
				enemies[Integer.parseInt(values[col+1])] = new EnemyOne();
				enemies[Integer.parseInt(values[col+1])].setX(isoX);
				enemies[Integer.parseInt(values[col+1])].setY(isoY);
				for(Border b : makers) {
					if(b.getModifier() == 1) {
						enemies[Integer.parseInt(values[col+1])].setOriginX( b.getX() );
						enemies[Integer.parseInt(values[col+1])].setOriginY( b.getY() );
					}
				}
		      }else if(values[col].equals("E2")){
		    	  enemies2[Integer.parseInt(values[col+1])] = new EnemyOne();
				  enemies2[Integer.parseInt(values[col+1])].setX(isoX);
				  enemies2[Integer.parseInt(values[col+1])].setY(isoY);
				  for(Border b : makers) {
					  if(b.getModifier() == 2) {
						  enemies2[Integer.parseInt(values[col+1])].setOriginX( b.getX() );
						  enemies2[Integer.parseInt(values[col+1])].setOriginY( b.getY() );
					  }
				}
		      }else if(values[col].equals("E3")){
		    	  enemies3[Integer.parseInt(values[col+1])] = new EnemyOne();
				  enemies3[Integer.parseInt(values[col+1])].setX(isoX);
				  enemies3[Integer.parseInt(values[col+1])].setY(isoY);
				  for(Border b : makers) {
					  if(b.getModifier() == 3) {
						  enemies3[Integer.parseInt(values[col+1])].setOriginX( b.getX() );
						  enemies3[Integer.parseInt(values[col+1])].setOriginY( b.getY() );
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
			playSfx("hit.wav");
		globalX = -320;
		globalY = 400;
		lives--;
		score -= 10;
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
		score = 0;
		lives = 3;
		globalX = -320;
		globalY = 400;
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

	//ignore all code beyond this
	//must be present for frame to compile
	
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();//this is important i think
	}
	

}
