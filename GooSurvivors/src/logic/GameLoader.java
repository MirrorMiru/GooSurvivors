package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class GameLoader {

    // Attributes for player health, weapon damage, and high score
    private int playerHp;
    private int weaponDamage;
    private int highScore = 0;

    // Constructor initializes the GameLoader and ensures the save file exists
    public GameLoader() {
        make();
    }

    // Static method to create the save file if it does not already exist
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

    // Method to save the current game state to the save file
    public void save(int health, int damage, int highscore) {
        // Update high score if the new score is higher
        if (highscore > this.highScore) {
            this.highScore = highscore;
        }

        try {
            // Clear the existing content of the save file
            this.clear();
            // Write the new game state to the save file
            FileWriter myWriter = new FileWriter("save.txt");
            myWriter.write("" + health + "," + damage + "," + highScore);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // Method to load the game state from the save file
    public void load() {
        File file = new File("save.txt");

        try {
            System.out.println("loaded");
            Scanner sc = new Scanner(file);

            // Read each line of the save file
            while (sc.hasNextLine()) {
                String i = sc.nextLine();
                System.out.println(i);
                // Split the line into values and assign them to attributes
                String[] vals = i.split(",");
                playerHp = Integer.parseInt(vals[0]);
                weaponDamage = Integer.parseInt(vals[1]);
                highScore = Integer.parseInt(vals[2]);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Getter method for player health
    public int getPlayerHp() {
        return playerHp;
    }

    // Getter method for weapon damage
    public int getDamage() {
        return weaponDamage;
    }

    // Getter method for high score
    public int getHighScore() {
        return highScore;
    }

    // Method to clear the save file content
    public void clear() {
        File file = new File("save.txt");

        try {
            PrintWriter writer = new PrintWriter(file);
            writer.print(""); // Clear the content
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
