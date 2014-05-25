package game2048;

import org.eclipse.swt.widgets.Shell;

import controller.Presenter;

public class Main2048 {

//	public static void main(String[] args) {
//		System.out.println("Start ! ");
//		
//		Model2048 model2048 = new Model2048(4);
//		View2048 view2048 = new View2048(4);
//		
//		Presenter presenter=new Presenter(model2048, view2048);
//		
//		model2048.addObserver(presenter);
//		view2048.addObserver(presenter);
//
//		view2048.run();
//		
//		
//		
//	}
	/**
	 * Start game2048
	 * @param perentShell 
	 */
	public static void startGame2048(Shell perentShell) {
		Model2048 model2048 = new Model2048(4);
		View2048 view2048 = new View2048(4,perentShell);
		
		Presenter presenter=new Presenter(model2048, view2048);
		
		model2048.addObserver(presenter);
		view2048.addObserver(presenter);

		view2048.run();
	}

}
