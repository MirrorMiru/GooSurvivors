package logic;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.logging.Level;

public class pathfinding {
	private ArrayList<Tile> enemy;
	private ArrayList<Position> map;
	private boolean[][] board;
	private String[][] xBoard;
	private int startX;
	private int startY;
	private char[] types = {0, 1, 2, 3};
	private int rows = 27;
	private int cols = 27;
	Tile goal = new Tile(13,13, 4);
	private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	
	public pathfinding() {
		// TODO Auto-generated constructor stub
		enemy = new ArrayList<Tile>();
		board = new boolean[27][27];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0;  j < board[0].length; j++) {
				board[i][j] = false;
			}
		}
		int assign = (int)((Math.random()*4)+1);
		if(assign < 3) {
			if(assign < 2) {
				startX = 0;
				startY = (int)((Math.random()*27));
				board[startX][startY] = true;
			}
			else {
				startX = (int)((Math.random()*27));
				startY = 0;
				board[startX][startY] = true;
			}
		}
		else {
			if(assign > 3) {
				startX = board.length-1;
				startY = (int)((Math.random()*27));
				board[startX][startY] = true;
			}
			else {
				startX = (int)((Math.random()*27));
				startY = board[0].length-1;
				board[startX][startY] = true;
			}
		}
	}
	public void addTile(Tile e) {
		enemy.add(e);
	}
	public void finder() throws Exception {
		String filename = "map1.txt";
    	ClassLoader classLoader = Frame.class.getClassLoader();
		InputStream inputStream = ClassLoader.getSystemResourceAsStream(filename);
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		System.out.println(inputStream != null);
		String[] dimensions = reader.readLine().split(" ");
		map = loadMaze(reader,rows,cols);
	}
	public Queue<Tile> findPath(Tile start, Player p) {
		Queue<Tile> queue = new LinkedList<>();
		queue.offer(start);

        Map<Tile, Tile> parentMap = new HashMap<>();
        parentMap.put(start, null);
        
        
        while (!queue.isEmpty()) {
            Tile current = queue.poll(); // Dequeue a cell
            
            if (current.x == goal.x && current.y == goal.y) {
                // If the goal is reached, reconstruct and return the path
                return reconstructPath(parentMap, goal);
            }
            
            for(int[] dir : dirs) {
            	int newX = current.x + dir[0];
            	int newY = current.y + dir[1];
            	
            	if (isValid(newX, newY, goal.x, goal.y)) {
                    Position n = map.get(newX * cols + newY);
                    Tile neighbor = new Tile(n.row, n.col, Integer.parseInt(n.type));
                    if (neighbor != null && neighbor.x == goal.x && neighbor.y == goal.y && current.type != 1 && current.type != 3 && current.type != 2) {
                        if (!parentMap.containsKey(neighbor)) {
                        	queue.offer(neighbor);
                            parentMap.put(neighbor, current);
                        }
                    }
                }
            }
        }
		return queue;
	}
	private Queue<Tile> reconstructPath(Map<Tile,Tile> parentMap, Tile goal) {
        Queue<Tile> path = new LinkedList<>();//create a new queue to return the information
        Tile current = goal;
        while (current != null) {//add to the new queue what was in the list
            path.offer(current);
            current = parentMap.get(current);
        }
        return path;
    }
    private boolean isValid(int row, int col, int numRows, int numCols) {
        return row >= 0 && row < numRows && col >= 0 && col < numCols;
    }

    private ArrayList<Position> loadMaze(BufferedReader reader, int rows, int cols) throws Exception {
    	map = new ArrayList<Position>();
    	for (int i = 0; i < rows; i++) {
			String line = reader.readLine();
			for (int j = 0; j < cols; j++) {
				String c = line.substring(i,i+1);
				if (c.equals(",")) {
					Position p = new Position(i,j,c);
					map.add(p);
					xBoard[i][j] = c;
				}
			}
		}
		System.out.println("all positions loaded");
		return map;
	}
}
