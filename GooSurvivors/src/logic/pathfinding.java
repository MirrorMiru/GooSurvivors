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
import java.util.Scanner;
import java.util.logging.Level;

public class pathfinding {
	private ArrayList<Tile> enemy;
	private ArrayList<Position> map;
	private Queue<Position> sol;
	private boolean[][] board;
	private String[][] xBoard;
	private int startX;
	private int startY;
	private char[] types = {0, 1, 2, 3};
	public int rows = 27;
	public int cols = 27;
	Position goal = new Position(13,13, "4");
	private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	
	public pathfinding(String s) throws Exception {
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
		String filename = s;
    	ClassLoader classLoader = Frame.class.getClassLoader();
		InputStream inputStream = ClassLoader.getSystemResourceAsStream(filename);
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		System.out.println(inputStream != null);
		String[] dimensions = reader.readLine().split(" ");
		map = loadMaze(reader,rows,cols);
		System.out.print(map.size());
	}
	public void addTile(Tile e) {
		enemy.add(e);
	}
	public void finder() throws Exception {
		Scanner userInput = new Scanner(System.in);
		String c = userInput.nextLine().toLowerCase();
		pathfinding p = new pathfinding(c);
		sol = p.findPath(goal, null);
		
		
	}
	public Queue<Position> findPath(Position start, Player p) {
		Queue<Position> queue = new LinkedList<>();
		queue.offer(start);

        Map<Position,Position> parentMap = new HashMap<>();
        parentMap.put(start, null);
        
        
        while (!queue.isEmpty()) {
            Position current = queue.poll(); // Dequeue a cell
            
            if (current.row == goal.row && current.col == goal.col) {
                // If the goal is reached, reconstruct and return the path
                return reconstructPath(parentMap, goal);
            }
            
            for(int[] dir : dirs) {
            	int newX = current.row + dir[0];
            	int newY = current.col + dir[1];
            	
            	if (isValid(newX, newY, goal.row, goal.col)) {
                    Position n = map.get(newX * cols + newY);
                    Position neighbor = new Position(n.row, n.col, n.type);
                    if (neighbor != null && neighbor.row == goal.row && neighbor.col == goal.col && current.type.equals("1") && current.type.equals("3") && current.type.equals("2")) {
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
	private Queue<Position> reconstructPath(Map<Position, Position> parentMap, Position Position) {
        Queue<Position> path = new LinkedList<>();//create a new queue to return the information
        Position current = goal;
        while (current != null) {//add to the new queue what was in the list
            path.offer(current);
            current = parentMap.get(current);
        }
        return path;
    }
    private boolean isValid(int row, int col, int numRows, int numCols) {
        return row >= 0 && row <= numRows && col >= 0 && col <= numCols;
    }

    public ArrayList<Position> loadMaze(BufferedReader reader, int rows, int cols) throws Exception {
    	map = new ArrayList<Position>();
    	for (int i = 0; i < rows; i++) {
			String line = reader.readLine();
			for (int j = 0; j < cols; j++) {
				String c = line.substring(i,i+1);
				if (!c.equals(",")) {
					Position p = new Position(i,j,c);
					map.add(p);
					xBoard[i][j] = c;
				}
			}
		}
		System.out.println("all positions loaded");
		return map;
	}
    
    public void paint(Graphics g) {
    	for (int i = 0;  i < map.size(); i++) {
    		Tile t = new Tile(map.get(i).row, map.get(i).col, Integer.parseInt(map.get(i).type));
    		t.paint(g);
    	}
    }  
    public int[][] returnBoard() {
    	int[][] board = new int[27][27];
    	int s = sol.size();
    	for (int i = 0; i < s; i++) {
    		board[sol.peek().row][sol.peek().col] = 1;
    		sol.remove();
    	}
    	return board;
    }
}
