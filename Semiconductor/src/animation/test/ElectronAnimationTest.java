package animation.test;

import animation.ConductElectron;
import animation.ValenceElectron;
import animation.ValenceHole;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class ElectronAnimationTest extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		
		ValenceElectron valenceElectron = new ValenceElectron();
		ValenceHole hole = new ValenceHole();
		ConductElectron conductElectron = new ConductElectron();
		
		// set initial position for electron and hole
		
/*		valenceElectron.getParticle().setX(0);
		valenceElectron.getParticle().setY(0);
		hole.getParticle().setX(300);
		hole.getParticle().setY(600);
		conductElectron.getParticle().setX(300);
		conductElectron.getParticle().setY(0);
		*/
		
		// test animation 2 + 3 - done
		
//		Particle.changeElectronAndHole(valenceElectron, hole);
		
		// test animation 1 - done
		
//		conductElectron.translationalMove();
		
		// test animation 4
		// cannot display the explosion image because it didn't added to the pane "root" below
		// although the transition is still played
		// how to solve this?
		
		valenceElectron.explode(hole, conductElectron);
		
		// test animation 5 - done
		
/*		valenceElectron.getParticle().setX(0);
		valenceElectron.getParticle().setY(0);
		hole.getParticle().setX(0);
		hole.getParticle().setY(0);
		conductElectron.getParticle().setX(0);
		conductElectron.getParticle().setY(0);
		Particle.createValenceBandE(hole, conductElectron, valenceElectron);
		*/
		
		Pane root = new Pane();
		root.getChildren().addAll(valenceElectron.getParticle(), hole.getParticle(), conductElectron.getParticle());
//		root.getChildren().addAll(valenceElectron.getParticle(), imgExplosion, hole.getParticle(), conductElectron.getParticle());
		Scene scene = new Scene(root, 1000, 1000);
		
		stage.setScene(scene);
		stage.show();		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
