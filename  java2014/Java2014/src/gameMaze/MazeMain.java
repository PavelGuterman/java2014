package gameMaze;

import controller.Presenter;

public class MazeMain {

	public static void main(String[] args) {
		System.out.println("Start ! ");
		
		ModelMaze modelMaze = new ModelMaze();
		ViewMaze viewMaze = new ViewMaze(4);
		
		Presenter presenter=new Presenter(modelMaze, viewMaze);
		
		modelMaze.addObserver(presenter);
		viewMaze.addObserver(presenter);
		
		viewMaze.run();

	}

}
