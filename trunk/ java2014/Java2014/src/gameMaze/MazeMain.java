package gameMaze;

import game2048.Model2048;
import game2048.View2048;
import controller.Presenter;
import controller.Presenter;

public class MazeMain {

	public static void main(String[] args) {
		System.out.println("Start ! ");
		
		ModelMaze modelMaze = new ModelMaze(4);
		ViewMaze viewMaze = new ViewMaze(4);
		
		Presenter presenter=new Presenter(modelMaze, viewMaze);
		
		modelMaze.addObserver(presenter);
		viewMaze.addObserver(presenter);
		
		viewMaze.run();

	}

}
