package logic;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.logging.Level;

public class pathfinding {
	private ArrayList<Position> entry;
	public ArrayList<Position> maze;
	public ArrayList<Position> sol = new ArrayList<Position>();
	private boolean[][] board;
	private String[][] xBoard = new String[27][27];
	private int startX;
	private int startY;
	public int rows = 27;
	public int cols = 27;
	public String filename;
	public Position goal = new Position(14, 14, "4");
	public Position start;
	private int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public pathfinding() {
		// TODO Auto-generated constructor stub
		board = new boolean[27][27];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = false;
			}
		}
		int assign = (int) ((Math.random() * 4) + 1);
		if (assign < 3) {
			if (assign < 2) {
				startX = 1;
				startY = (int) ((Math.random() * 27));
				board[startX][startY] = true;
				start = new Position(startX, startY, "4");
			} else {
				startX = (int) ((Math.random() * 27));
				startY = 1;
				board[startX][startY] = true;
				start = new Position(startX, startY, "4");
			}
		} else {
			if (assign > 3) {
				startX = board.length - 1;
				startY = (int) ((Math.random() * 27));
				board[startX][startY] = true;
				start = new Position(startX, startY, "4");
			} else {
				startX = (int) ((Math.random() * 27));
				startY = board[0].length - 1;
				board[startX][startY] = true;
				start = new Position(startX, startY, "4");
			}
		}
	}

	public ArrayList<Position> findtotalPath(ArrayList<Position> maze) {
		ArrayList<Position> path = new ArrayList<Position>();
		path = findPath(maze, start, goal);
		//System.out.println(path);
		if (path == null || path.isEmpty()) {
			// even if one level is not solved, maze is not solved
			return null;
		}
		while (!path.isEmpty()) {
			Position q = path.remove(0);
			path.add(q);
		}
		return path;

	}

	public ArrayList<Position> findPath(ArrayList<Position> maze, Position start, Position goal) {
		Stack<Position> stack = new Stack<Position>();
		stack.push(start);

		Map<Position, Position> parentMap = new HashMap<>();
		parentMap.put(start, null);
		
			
		if(neighborFound(start, parentMap, stack)) {
			return reconstructPath(parentMap, goal);
		} 
		System.out.println("no work");
		return null;
	}
	
	private boolean neighborFound(Position current, Map<Position,Position> parentMap, Stack<Position> stack) {

		if (current.row == goal.row && current.col == goal.col) {
			// If the goal is reached, reconstruct and return the path
			System.out.println("it works");
			return true;
		}
		ArrayList<Position> noncloseneigh = new ArrayList<Position>();
		for (int[] dir : dirs) {
			int newX = current.row + dir[0];
			int newY = current.col + dir[1];

			if (isValid(newX, newY, 26, 26)) {
				Position neighbor = maze.get(newX * cols + newY);
				if (neighbor != null && neighbor.type.equals("0")) {
					if (Math.abs(neighbor.row - goal.row) < Math.abs(current.row - goal.row)
							|| Math.abs(neighbor.col - goal.col) < Math.abs(current.col - goal.col)) {
						System.out.println("current "+current);
						System.out.println("neighbor "+neighbor);
						if (!parentMap.containsKey(neighbor)) {
							stack.push(neighbor);
							parentMap.put(neighbor, current);
							if(!neighborFound(neighbor, parentMap, stack)){
								stack.pop();
								continue;
							}else {
								return true;
							}

						}
					}
					else {
						noncloseneigh.add(neighbor);
					}
				}
			}
		}
		for (int i = 0; i < noncloseneigh.size(); i++) {
			if (!parentMap.containsKey(noncloseneigh.get(i))) {
				stack.push(noncloseneigh.get(i));
				parentMap.put(noncloseneigh.get(i), current);
				if(!neighborFound(noncloseneigh.get(i), parentMap, stack)){
					stack.pop();
					continue;
				}else {
					return true;
				}

			}
		}
		return false;
	}

	private ArrayList<Position> reconstructPath(Map<Position, Position> parentMap, Position Position) {
		Position current = goal;
		while (current != null) {// add to the new queue what was in the list
			sol.add(0, current);
			current = parentMap.get(current);
		}
		return sol;
	}

	private boolean isValid(int row, int col, int numRows, int numCols) {
		return row >= 0 && row <= numRows && col >= 0 && col <= numCols;
	}

	public ArrayList<Position> loadMaze(String name, int rows, int cols) {
		maze = new ArrayList<Position>();
		int j = 0;
		try {
			File f = new File(name);
			Scanner s = new Scanner(f);
			while (s.hasNext()) {
				String[] row = s.nextLine().split(",");
				for (int i = 0; i < row.length; i++) {
					maze.add(new Position(j, i, row[i].substring(0, 1)));
					xBoard[j][i] = row[i].substring(0, 1);
				}
				j++;

			}

			s.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return maze;
	}

	public void paint(Graphics g, int i) {
		Tile t = new Tile(sol.get(i).row, sol.get(i).col, 4);
		t.paint(g);

	}

	public int[][] returnBoard() {
		int[][] board = new int[27][27];
		int s = sol.size();
		for (int i = 0; i < s; i++) {
			board[sol.get(i).row][sol.get(i).col] = 1;
		}
		return board;
	}
}
