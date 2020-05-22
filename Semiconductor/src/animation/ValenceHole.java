package animation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ValenceHole extends Particle {

	public ValenceHole() {
		String strHole = "./src/images/particle-hole.png";
		FileInputStream inputHole = null;
		try {
			inputHole = new FileInputStream(strHole);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Image imgHole = new Image(inputHole);
		ImageView imgViewHole = new ImageView(imgHole);
		this.particle = imgViewHole;
	}
	

	/* old code 
	public Transition jumpToElectron(ValenceElectron electron) {
		
		double iniPositionX = electron.getParticle().getX();
		double iniPositionY = electron.getParticle().getY();
		
		FadeTransition fadeoutTransition = new FadeTransition(Duration.millis(1000));
		fadeoutTransition.setNode(this.getParticle());
		fadeoutTransition.setFromValue(1.0);
		fadeoutTransition.setToValue(0);
		
		TranslateTransition translateTransition = new TranslateTransition(Duration.millis(100));
		translateTransition.setNode(this.getParticle());
		translateTransition.setByX(iniPositionX-this.getParticle().getX());
		translateTransition.setByY(iniPositionY-this.getParticle().getY());
		
		FadeTransition fadeinTransition = new FadeTransition(Duration.millis(1000));
		fadeinTransition.setNode(this.getParticle());
		fadeinTransition.setFromValue(0);
		fadeinTransition.setToValue(1.0);
		
		SequentialTransition sqTransition = new SequentialTransition();
		sqTransition.getChildren().addAll(fadeoutTransition, translateTransition, fadeinTransition);
		
//		sqTransition.play();
		
		return sqTransition;
		
	} */

}
