package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class GameLoader {

	int playerHp;
	int weaponDamage;
	int highScore = 0;
	
	public GameLoader() {
		 
		make();
	}
	
	
	public static void make() {
		    try {
		      File myObj = new File("save.txt");
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      } else {
		        System.out.println("File already exists.");
		       
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    
		    }
		  
		  }
	
	public void save(int health, int damage, int highscore) {
		 	if(highscore > this.highScore) {
		 		this.highScore = highScore;
		 	}
		
		    try {
		    	this.clear();
		      FileWriter myWriter = new FileWriter("save.txt");
		      myWriter.write(""+health+","+damage+","+highScore);
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		  }
	
	public void load() {
		 File file = new File("save.txt");

		    try {
		    	System.out.println("loaded");
		        Scanner sc = new Scanner(file);

		        while (sc.hasNextLine()) {
		            String i = sc.nextLine();
		            System.out.println(i);
		            String[] vals = i.split(",");
		            playerHp = Integer.parseInt(vals[0]);
		            weaponDamage = Integer.parseInt(vals[1]);
		            highScore = Integer.parseInt(vals[2]);
		        }
		        sc.close();
		    } 
		    catch (FileNotFoundException e) {
		        e.printStackTrace();
		    }
	}
	
	public int getPlayerHp() {
		return playerHp;
	}


	public int getDamage() {
		return weaponDamage;
	}


	public int getHighScore() {
		return highScore;
	}


	public void clear() {
		File file = new File("save.txt");
		
		PrintWriter writer;
		try {
			writer = new PrintWriter(file);
			writer.print("");
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	
}
	



