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

public class ConductElectronImage extends ParticleImage {
	
	public ConductElectronImage() {
		String strElectron = "./src/images/particle-conduct-e.png";
		FileInputStream inputElectron = null;
		try {
			inputElectron = new FileInputStream(strElectron);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		
		Image imgElectron = new Image(inputElectron);
		ImageView imgViewElectron = new ImageView(imgElectron);
		
		imgViewElectron.setFitHeight(imgViewElectron.getImage().getHeight()*0.2);
		imgViewElectron.setFitWidth(imgViewElectron.getImage().getWidth()*0.2);

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
