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
   			
	//le title screen
	Sprite[] titleScreen = {new Sprite("/img/title1.png",0,0,800,700), new Sprite("/img/title2.png",0,0,800,700),  new Sprite("/img/title3.png",0,0,800,700)};
	
	
	//le font
	Font myFont = new Font("Courier", Font.BOLD, 40);
	
	//bgm, starts looping immediately and never stops
	//SimpleAudioPlayer backgroundMusic = new SimpleAudioPlayer("LabyrinthFight.wav", true);
	
	//frame width/height
	int width = 800;
	int height = 700;	
	
	int score = 0;//game score, this should be self explanatory
	int lives = 3;//lives
	
	int gamestate = 0;//gamestate
	//0 = main menu
	//2 = story cutscene
	//1 = gameplay
	//4 = game over
	//5 = victory screen
	
	int titleIndex = 0; //array index used for title screen cursor
	
	int globalX = 300;
	int globalY = 200;
	

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
			
			titleScreen[titleIndex].paint(g);
		
		}else if(gamestate == 2) {
			
		//optional story images or game controlls
			
		}else if(gamestate == 1) {
			
			//background.paint(g);//display background image
			//under everything
			//never translates
			for (int i = 0; i <= 100; i++) {
			    int y = i * 50; // Adjust cellHeight as needed
			    g.drawLine(0, y, 100 * 50, y); // Assuming cellWidth is the width of each cell
			}

			// Drawing vertical lines
			for (int i = 0; i <= 100; i++) {
			    int x = i * 50; // Adjust cellWidth as needed
			    g.drawLine(x, 0, x, 100 * 50); // Assuming cellHeight is the height of each cell
			}
			
		  // 	g.translate(globalX, globalY);//translate all tiles
		   	
			//grid draw here
			  
			g.translate(globalX, globalY);//translate gui and player back to stay rooted

			
			player.paint(g);
			globalX +=player.getVx();
			globalY +=player.getVy();
			
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

	}
	
	/**
	* initilaize 1t layer of tiles
	* traverses a array of strings, each letter is assigned a tile
	* the tile is added to arraylists that are processed in the drawGrid function
	*
	* @param none
	*/
	
	
	
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
				player.setVy(-2);
			}
		}  if(arg0.getKeyCode() == 40) {//down
			if(gamestate == 0) {
				if(titleIndex < 2) {
					titleIndex++;
				}
	
			}else	if(gamestate == 1) {
				player.setVy(2);
			}
		}  if(arg0.getKeyCode() == 37) {//left
			if(gamestate == 1) {
				player.setVx(-2);
			}
		}  if(arg0.getKeyCode() == 39) {//right
			if(gamestate == 1) {
				player.setVx(2);
			}
		}
	 if(arg0.getKeyCode() == 32) {//spacebar
		//select button
		 if(gamestate == 0) {
				if(titleIndex == 0) {
					gamestate = 1;
				}
		 	}
	 	}
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
