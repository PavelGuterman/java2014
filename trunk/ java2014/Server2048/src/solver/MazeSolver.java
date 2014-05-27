package solver;

import java.util.ArrayList;

import maze.Maze;
import maze.MazeDistance;
import maze.MazeDomain;
import model.algorithms.Action;
import model.algorithms.a_star.Astar;

public class MazeSolver {
	
	private int[][] data;
	
	public MazeSolver(int[][] data){
		this.data = data;
	}
	
	public ArrayList<Action> getTheCheese(){
		Maze maze  = new Maze(data);
		Astar as = new Astar(new MazeDomain(maze), new MazeDistance(), new MazeDistance());
		ArrayList<Action> actions = as.search(maze.getStartState() , maze.getGoalState());
		return actions;
		
	}
}
