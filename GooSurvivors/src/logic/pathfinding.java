package logic;

import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.logging.Level;

import javax.swing.text.Position;

public class pathfinding {
	private ArrayList<Enemy> enemy;
	private int[][] board;
	
	public pathfinding(Player p) {
		// TODO Auto-generated constructor stub
		enemy = new ArrayList<Enemy>();
		board = new int[815][735];
	}
	public void addEnemy(Enemy e) {
		enemy.add(e);
	}
	public static List<Position> findPath(List<Level> levels, int rows, int columns) {
		List<Position> path = new ArrayList<>();
		boolean[][] visited = new boolean[rows][columns];

		// Start from the entrance position of the first level
		search(levels, 0, levels.get(0).intValue(), visited, path, rows, columns);
		return path;
	}

	private static boolean search(List<Level> levels, int levelIndex, Position pos,
			List<Position> path, int rows, int columns) {
		int row = 0;
		int col = 1;
		int level = levelIndex;
		int posIndex = row * columns + col;

		// Check if the position is valid and not visited
		if (row < 0 || col < 0 || level < 0 || row >= rows || col >= columns || visited[levelIndex][row][col]) {
			return false;
		}

		// Mark the position as visited

		// Explore adjacent positions
		int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // Up, down, left, right
		for (int[] dir : dirs) {
			int newRow = row + dir[0];
			int newCol = col + dir[1];
			
		}

		// If the position is a prize, keep exploring without changing level

		// If no path is found, backtrack
		path.remove(path.size() - 1);
		return false;
	}


}
