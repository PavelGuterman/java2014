package gameMaze;

import org.eclipse.swt.widgets.Shell;

import controller.Presenter;

public class MazeMain {

	
	public static void startGameMaze(Shell shell) {
		ModelMaze modelMaze = new ModelMaze();
		ViewMaze viewMaze = new ViewMaze(4,shell);
		
		Presenter presenter=new Presenter(modelMaze, viewMaze);
		
		modelMaze.addObserver(presenter);
		viewMaze.addObserver(presenter);
		
		viewMaze.run();
	}

}
