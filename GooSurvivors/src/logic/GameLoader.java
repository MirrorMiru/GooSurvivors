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
	int wave;
	int playerXp;
	ArrayList<Integer> items = new ArrayList<Integer>();
	
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
	
	public void save(int hp, int w, int xp, ArrayList<Integer> items) {
		 
		    try {
		    	this.clear();
		      FileWriter myWriter = new FileWriter("save.txt");
		      myWriter.write(""+hp+","+w+","+xp+",");
		      for(int i: items) {
		      myWriter.write(i+",");
		      }
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
		            wave = Integer.parseInt(vals[1]);
		            playerXp = Integer.parseInt(vals[2]);
		            for(int n = 3; n < vals.length; n++) {
		            	items.add(Integer.parseInt(vals[n]));
		            }
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


	public int getWave() {
		return wave;
	}


	public int getPlayerXp() {
		return playerXp;
	}


	public ArrayList<Integer> getItems() {
		return items;
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
	



