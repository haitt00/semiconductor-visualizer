package elements;

import javafx.scene.control.Button;
import elements.charge.Charge;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import settings.Settings;

public class MainTest extends Application{

	private Button cotinueButton = new Button("Continue?");
	int timestep = 0;
	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane root = new Pane();
		root.getChildren().add(cotinueButton);
		
//		//test type pure (all Si)
//		Crystal crystalPure = new Crystal();
//		crystalPure.initCrystal("SI");
//		crystalPure.displayCrystal();
//		crystalPure.displayHolePosition();
//		//test behavior 4
//		crystalPure.getAtomAt(2, 2).diffuse();
//		crystalPure.displayCrystal();
//		crystalPure.displayHolePosition();
//		//test behavior 5
//		crystalPure.getAtomAt(2, 2).recombination();
//		crystalPure.displayCrystal();
//		crystalPure.displayHolePosition();
		
		//test type P (1 Al, the rest Si) - behavior 2 & 3
		System.out.println();
		Crystal crystalAl = new Crystal();
		System.out.println("t = 0:");
		crystalAl.initCrystal("AL");
		crystalAl.displayCrystal();
		crystalAl.displayHolePosition();
		for (int x = 0; x < Settings.crystalWidth; x++) {
			for (int y = 0; y < Settings.crystalWidth; y++) {
				root.getChildren().add(crystalAl.getAtomAt(x, y).getView());
				root.getChildren().add(crystalAl.getAtomAt(x, y).getValenceCharge("up").getView());
				root.getChildren().add(crystalAl.getAtomAt(x, y).getValenceCharge("down").getView());
				root.getChildren().add(crystalAl.getAtomAt(x, y).getValenceCharge("right").getView());
				root.getChildren().add(crystalAl.getAtomAt(x, y).getValenceCharge("left").getView());
				if(crystalAl.getAtomAt(x, y).checkForConductingE()) {
					root.getChildren().add(crystalAl.getAtomAt(x, y).getConductingE().getView());
				}
			}
		}
		//for-loop approach
//		for (int i = 1; i <= 2; i++) {
//			System.out.println("t = "+i+":");
//			crystalAl.progress();
//			crystalAl.displayCrystal();
//			crystalAl.displayHolePosition();
//		}
		
		//continue button approach
		this.cotinueButton.setOnAction(evt->{
			timestep++;
			System.out.println("t = "+timestep);
			crystalAl.progress();
			crystalAl.displayCrystal();
			crystalAl.displayHolePosition();
		});
		System.out.println();
//		//test type N (1 P, the rest Si) - behavior 1
//		Crystal crystalP = new Crystal();
//		System.out.println("t = 0:");
//		crystalP.initCrystal("P");
//		crystalP.displayCrystal();
//		Charge e = crystalP.getAtomAt(3, 2).getConductingE();
//		System.out.println("1st coordinate: "+e.getView().getX()+", "+e.getView().getY());
//		for (int x = 0; x < Settings.crystalWidth; x++) {
//		for (int y = 0; y < Settings.crystalWidth; y++) {
//			root.getChildren().add(crystalP.getAtomAt(x, y).getView());
//			root.getChildren().add(crystalP.getAtomAt(x, y).getValenceCharge("up").getView());
//			root.getChildren().add(crystalP.getAtomAt(x, y).getValenceCharge("down").getView());
//			root.getChildren().add(crystalP.getAtomAt(x, y).getValenceCharge("right").getView());
//			root.getChildren().add(crystalP.getAtomAt(x, y).getValenceCharge("left").getView());
//			if(crystalP.getAtomAt(x, y).checkForConductingE()) {
//				root.getChildren().add(crystalP.getAtomAt(x, y).getConductingE().getView());
//			}
//		}
//	}
		
//		//for-loop approach (not sequential, new coordinate not updated in time)
//		for (int i = 1; i <= 2; i++) {
//			System.out.println("t = "+i+":");
//			System.out.println("before coordinate: "+e.getView().getX()+", "+e.getView().getY());
//			crystalP.progress();
//			System.out.println("after coordinate: "+e.getView().getX()+", "+e.getView().getY());
//			crystalP.displayCrystal();
//		}
//		//continue button approach
//		this.cotinueButton.setOnAction(evt->{
//			crystalP.progress();
//			timestep++;
//			System.out.println("t = "+timestep);
//			crystalP.displayCrystal();
//			crystalP.displayHolePosition();
//		});


		Scene scene = new Scene(root, 1000, 1000);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
