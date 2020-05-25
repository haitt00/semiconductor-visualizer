package animation.test;

import animation.ConductElectronImage;
import animation.ValenceElectronImage;
import animation.ValenceHoleImage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class ElectronAnimationTest extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		
		ValenceElectronImage valenceElectronImage = new ValenceElectronImage();
		ValenceHoleImage hole = new ValenceHoleImage();
		ConductElectronImage conductElectronImage = new ConductElectronImage();
		
		// set initial position for electron and hole
		
/*		valenceElectron.getParticle().setX(0);
		valenceElectron.getParticle().setY(0);
		hole.getParticle().setX(300);
		hole.getParticle().setY(600);
		conductElectron.getParticle().setX(300);
		conductElectron.getParticle().setY(0);
		*/
		
		// test animation 2 + 3 - done
		
//		ParticleImage.changeElectronAndHole(valenceElectron, hole);
		
		// test animation 1 - done
		
//		conductElectron.translationalMove();
		
//		// test animation 4
//		// cannot display the explosion image because it didn't added to the pane "root" below
//		// although the transition is still played
//		// how to solve this?
//		
//		valenceElectronImage.explode(hole, conductElectronImage);
		
		// test animation 5 - done
		
		valenceElectronImage.getParticle().setX(0);
		valenceElectronImage.getParticle().setY(0);
		hole.getParticle().setX(0);
		hole.getParticle().setY(0);
		valenceElectronImage.getParticle().setX(0);
		valenceElectronImage.getParticle().setY(0);
		valenceElectronImage.createValenceBandE(hole, conductElectronImage, valenceElectronImage);
		
		
		Pane root = new Pane();
		root.getChildren().addAll(valenceElectronImage.getParticle(), hole.getParticle(), conductElectronImage.getParticle());
//		root.getChildren().addAll(valenceElectron.getParticle(), imgExplosion, hole.getParticle(), conductElectron.getParticle());
		Scene scene = new Scene(root, 1000, 1000);
		
		stage.setScene(scene);
		stage.show();		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
