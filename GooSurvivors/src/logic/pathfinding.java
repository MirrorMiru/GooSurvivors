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
	
	public pathfinding(Player p) {
		// TODO Auto-generated constructor stub
		enemy = new ArrayList<Enemy>();
	}
    public static Queue<Position> findPath(List<Level> maze, int rows, int cols) throws IOException {
        Position start = null, goal = null;
        Queue<Position> path = new LinkedList<Position>(); 
        for (Level level : maze) {
        	for (Position position: level.positions) {
        		if (position.type == CHARACTER) {
        			start = position;
        		} else if (position.type == GOAL) {
        			goal = position;
        		}
        		else if (position.type == DOOR) {
        			goal = position;
        		}	
        	}
        	Queue<Position> p = findPathInLevel(level.positions, start, goal, rows, cols);
        	if (p.isEmpty()) {
        		// even if one level is not solved, maze is not solved
        		return null;
        	}
        	while (!p.isEmpty()) {
                Position q = p.poll();
                path.offer(q);
            }
        }
        return path;
	
}
public static Queue<Position> findPathInLevel(List<Position> maze, Position start, Position goal, int rows, int cols) {
	

    Queue<Position> queue = new LinkedList<>();
    queue.offer(start); // Enqueue the starting cell

    Map<Position, Position> parentMap = new HashMap<>();
    parentMap.put(start, null); // Mark the starting cell's parent as null

    while (!queue.isEmpty()) {
        Position current = queue.poll(); // Dequeue a cell

        if (current.row == goal.row && current.col == goal.col) {
            // If the goal is reached, reconstruct and return the path
            return reconstructPath(parentMap, goal);
        }

        // Explore neighbors
        for (int[] dir : DIRECTIONS) {
            int newRow = current.row + dir[0];
            int newCol = current.col + dir[1];

            if (isValid(newRow, newCol, rows, cols)) {
                Position neighbor = maze.get(newRow * cols + newCol);
                if (neighbor != null && neighbor.row == newRow && neighbor.col == newCol && neighbor.type != WALL) {
                    if (!parentMap.containsKey(neighbor)) {
                        queue.offer(neighbor);
                        parentMap.put(neighbor, current);
                    }
                }
            }
        }
    }

    return new LinkedList<>(); // No path found
}
//reconstruct the path that was determined by the method
private static Queue<Position> reconstructPath(Map<Position, Position> parentMap, Position goal) {
    Queue<Position> path = new LinkedList<>();//create a new queue to return the information
    Position current = goal;
    while (current != null) {//add to the new queue what was in the list
        path.offer(current);
        current = parentMap.get(current);
    }
    return path;
}
//Used to check if the boundaries of the place that the maze is going through is valid
private static boolean isValid(int row, int col, int numRows, int numCols) {
    return row >= 0 && row < numRows && col >= 0 && col < numCols;
}

}
