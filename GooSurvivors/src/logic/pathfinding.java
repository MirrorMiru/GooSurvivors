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
	private ArrayList<Tile> enemy;
	private boolean[][] board;
	private int startX;
	private int startY;
	private char[] types = {0, 1, 2, 3};
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
	public void findPath(Tile start, Player p) {
		Queue<Tile> queue = new LinkedList<>();
		queue.offer(start);

        Map<Tile, Tile> parentMap = new HashMap<>();
        parentMap.put(start, null);
        
        int goalX = 13;
        int goalY = 13;
        
        while (!queue.isEmpty()) {
            Tile current = queue.poll(); // Dequeue a cell
            
            for(int[] dir : dirs) {
            	int newX = current.x + dir[0];
            	int newY = current.y + dir[1];
            	
            	/*if (isValid(newX, newY, rows, cols)) {
                    Position neighbor = maze.get(newRow * cols + newCol);
                    if (neighbor != null && neighbor.row == newRow && neighbor.col == newCol && neighbor.type != WALL) {
                        if (!parentMap.containsKey(neighbor)) {
                            queue.offer(neighbor);
                            parentMap.put(neighbor, current);
                        }
                    }
                }*/
            }
		
        }
	}
	private static Queue<Position> reconstructPath(Map<Position, Position> parentMap, Position goal) {
        Queue<Position> path = new LinkedList<>();//create a new queue to return the information
        Position current = goal;
        while (current != null) {//add to the new queue what was in the list
            path.offer(current);
            current = parentMap.get(current);
        }
        return path;
    }
    private static boolean isValid(int row, int col, int numRows, int numCols) {
        return row >= 0 && row < numRows && col >= 0 && col < numCols;
    }
}
