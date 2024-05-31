package logic;

import java.awt.Graphics;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class pathfinding {
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
		board = initializeBoard();
		start = generateRandomStart();
		System.out.println("random start = " + start);
		startX = start.row;
		startY = start.col;
		board[startX][startY] = true;
	}

	public ArrayList<Position> findtotalPath(ArrayList<Position> maze) {
		ArrayList<Position> path = new ArrayList<Position>();
		path = findPath(maze, start, goal);
		// System.out.println(path);
		if (path == null || path.isEmpty()) {
			// even if one level is not solved, maze is not solved
			return path;
		}

		for (int i = 0; i < path.size(); i++) {
			System.out.println(path.get(i));
		}
		return path;
	}

	public ArrayList<Position> findPath(ArrayList<Position> maze, Position start, Position goal) {
		Stack<Position> stack = new Stack<Position>();
		stack.push(start);

		ArrayList<Position> traversedPositions = new ArrayList<Position>();

		if (neighborFound(start, stack, traversedPositions)) {
			return reconstructPath(stack, goal);
		}
		System.out.println("no path");
		return new ArrayList<Position>();
	}

	private boolean neighborFound(Position current, Stack<Position> stack, ArrayList<Position> traversedPositions) {
		// System.out.println("Position "+current);

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
					if (gettingCloserToGoal(current, neighbor)) {
						if (!traversedPositions.contains(neighbor)) {
							stack.push(neighbor);
							traversedPositions.add(neighbor);
							if (!neighborFound(neighbor, stack, traversedPositions)) {
								stack.pop();
								continue;
							} else {
								return true;
							}
						}
					} else {
						noncloseneigh.add(neighbor);
					}
				}
			}
		}

		for (int i = 0; i < noncloseneigh.size(); i++) {
			if (!traversedPositions.contains(noncloseneigh.get(i))) {
				stack.push(noncloseneigh.get(i));
				if (!neighborFound(noncloseneigh.get(i), stack, traversedPositions)) {
					stack.pop();
					continue;
				} else {
					return true;
				}
			}
		}
		return false;
	}

	private boolean[][] initializeBoard() {
		boolean[][] board = new boolean[27][27];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = false;
			}
		}
		return board;
	}

	private Position generateRandomStart() {
		int assign = (int) ((Math.random() * 4) + 1);
		Position randomStart = null;
		int x;
		int y;
		if (assign < 3) {
			if (assign < 2) {
				x = 1;
				y = (int) ((Math.random() * 27));
				randomStart = new Position(x, y, "4");
			} else {
				x = (int) ((Math.random() * 27));
				y = 1;
				randomStart = new Position(x, y, "4");
			}
		} else {
			if (assign > 3) {
				x = board.length - 1;
				y = (int) ((Math.random() * 27));
				board[startX][startY] = true;
				randomStart = new Position(x, y, "4");
			} else {
				x = (int) ((Math.random() * 27));
				y = board[0].length - 1;
				board[startX][startY] = true;
				randomStart = new Position(x, y, "4");
			}
		}
		return randomStart;
	}

	private boolean gettingCloserToGoal(Position current, Position next) {
		int nextX = Math.abs(next.row - goal.row);
		int currentX = Math.abs(current.row - goal.row);

		int nextY = Math.abs(next.col - goal.col);
		int currentY = Math.abs(current.col - goal.col);
		return (nextX < currentX && nextY <= currentY) || (nextX <= currentX && nextY < currentY);

	}

	private ArrayList<Position> reconstructPath(Stack<Position> path, Position goal) {
		while (!path.isEmpty()) {
			sol.add(0, path.pop());
		}
		sol.add(goal);

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

		Tile t = new Tile(sol.get(i).row, sol.get(i).col, 3);
		t.paint(g);

	}
}
