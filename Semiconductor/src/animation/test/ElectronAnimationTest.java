package animation.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import animation.ParticleAnimation;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ElectronAnimationTest extends Application{

	@Override
	public void start(Stage stage) throws Exception {

		// initialize electron and hole for testing
		
		String strElectron = "C:\\Users\\Minh Chau\\OneDrive\\Documents\\Git\\OOLT.ICT.20192.20184238.NguyenThiMinhChau\\oolt.ict.20192-group13\\Semiconductor\\src\\images\\particle-electron.png";
		String strHole = "C:\\Users\\Minh Chau\\OneDrive\\Documents\\Git\\OOLT.ICT.20192.20184238.NguyenThiMinhChau\\oolt.ict.20192-group13\\Semiconductor\\src\\images\\particle-hole.png";
		
		FileInputStream inputElectron = null;
		FileInputStream inputHole = null;
		
		try {
			inputElectron = new FileInputStream(strElectron);
			inputHole = new FileInputStream(strHole);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Image imgElectron = new Image(inputElectron);
		Image imgHole = new Image(inputHole);
		
		ParticleAnimation electron = new ParticleAnimation(imgElectron);
		ParticleAnimation hole = new ParticleAnimation(imgHole);
		
		// set initial position for electron and hole
		
		electron.getParticle().setX(0);
		electron.getParticle().setY(0);
		hole.getParticle().setX(300);
		hole.getParticle().setY(600);
		
		// test each animation
		
//		electron.jumpToHole(hole);
//		hole.jumpToElectron(electron);
		
		// test combination of 2 animation: jumpToHole and jumpToElectron
		
//		ParticleAnimation.changeElectronAndHole(electron, hole);
		
		
		// test translational moving with vibration
		
		electron.translationalMove();
		
		Pane root = new Pane();
		root.getChildren().addAll(electron.getParticle(), hole.getParticle());
		
		Scene scene = new Scene(root, 1000, 1000);
		
		stage.setScene(scene);
		stage.show();		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
