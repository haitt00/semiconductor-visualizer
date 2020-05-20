package animation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.animation.PathTransition;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class ValenceElectron extends Particle {

	public ValenceElectron() {
		String strElectron = "./src/images/particle-valence-e.png";
		FileInputStream inputElectron = null;
		try {
			inputElectron = new FileInputStream(strElectron);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		Image imgElectron = new Image(inputElectron);
		ImageView imgViewElectron = new ImageView(imgElectron);
		this.particle = imgViewElectron;
	}
	
	public Transition jumpToHole(ValenceHole hole) {
		Path movePath = new Path();
		MoveTo destination = new MoveTo(this.getParticle().getX(), this.getParticle().getY());
		CubicCurveTo path = new CubicCurveTo(this.getParticle().getX()+36, this.getParticle().getY()+36, (this.getParticle().getX()+hole.getParticle().getY())/2, 85, hole.getParticle().getX()+36, hole.getParticle().getY()+36);
		movePath.getElements().add(destination);
		movePath.getElements().add(path);
		
		PathTransition pathTransition = new PathTransition();
		pathTransition.setDuration(Duration.millis(1500));
		pathTransition.setNode(this.getParticle());
		pathTransition.setPath(movePath);
		pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		pathTransition.setAutoReverse(false);
//		pathTransition.play();
		
		return pathTransition;
		
		// disappear, move to another transition
		
/*		FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000));
		fadeTransition.setNode(hole);
		fadeTransition.setFromValue(1.0);
		fadeTransition.setToValue(0);
		fadeTransition.setAutoReverse(false);
		
		SequentialTransition sqTransition = new SequentialTransition();
		sqTransition.getChildren().addAll(pathTransition, fadeTransition);
		
		sqTransition.play();
		*/
	}

}
