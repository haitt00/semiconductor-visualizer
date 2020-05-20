package animation.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import animation.ConductElectron;
import animation.Particle;
import animation.ValenceElectron;
import animation.ValenceHole;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ElectronAnimationTest extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		
		ValenceElectron valenceElectron = new ValenceElectron();
		ValenceHole hole = new ValenceHole();
		ConductElectron conductElectron = new ConductElectron();
		
		// set initial position for electron and hole
		
		valenceElectron.getParticle().setX(0);
		valenceElectron.getParticle().setY(0);
		hole.getParticle().setX(300);
		hole.getParticle().setY(600);
		conductElectron.getParticle().setX(0);
		conductElectron.getParticle().setY(0);
		
		// test each animation
		
	//	valenceElectron.jumpToHole(hole);
//		hole.jumpToElectron(electron);
		
		// test combination of 2 animation: jumpToHole and jumpToElectron
		
		Particle.changeElectronAndHole(valenceElectron, hole);
		
		
		// test translational moving with vibration
		
		conductElectron.translationalMove();
		
		Pane root = new Pane();
		root.getChildren().addAll(valenceElectron.getParticle(), hole.getParticle(), conductElectron.getParticle());
		
		Scene scene = new Scene(root, 1000, 1000);
		
		stage.setScene(scene);
		stage.show();		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
