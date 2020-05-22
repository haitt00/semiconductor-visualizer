package animation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class ConductElectron extends Particle {
	
	public ConductElectron() {
		String strElectron = "./src/images/particle-conduct-e.png"; //need to find new images for conduct electron
		FileInputStream inputElectron = null;
		try {
			inputElectron = new FileInputStream(strElectron);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		
		Image imgElectron = new Image(inputElectron);
		ImageView imgViewElectron = new ImageView(imgElectron);
		this.particle = imgViewElectron;
	}
	
	public Transition spinAndResize() {
		RotateTransition rotate = new RotateTransition(Duration.millis(3000));
		rotate.setNode(this.getParticle());
		rotate.setAxis(Rotate.Z_AXIS);
		rotate.setByAngle(360);
		
		ScaleTransition scale = new ScaleTransition(Duration.millis(3000));
		scale.setNode(this.getParticle());
		scale.setByX(-0.2);
		scale.setByY(-0.2);
		
		ParallelTransition prlTrans = new ParallelTransition();
		prlTrans.getChildren().addAll(rotate, scale);
		
		return prlTrans;
	}
	
}
