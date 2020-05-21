package animation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
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
	
	// valence electron "explodes" into valence hole and conduct electron
	// in main code: construct hole and conduct electron
	// so that this function can do its job
	
	public void explode(ValenceHole hole, ConductElectron conductE) {
	//	String strHole = "./src/images/particle-hole.png";
	//	String strConductE = "./src/images/particle-conduct-e.png";
		String strExplosion  = "./src/images/atom-recombination.png";
		
	/*	ImageView imgHole = new ImageView(new Image(strHole));
		ImageView imgConductE = new ImageView(new Image(strConductE));
		
		imgHole.setX(this.getParticle().getX());
		imgHole.setY(this.getParticle().getY());
		
		imgConductE.setX(this.getParticle().getX());
		imgConductE.setY(this.getParticle().getY());
		
		FadeTransition holeAppear = new FadeTransition(Duration.millis(500));
		holeAppear.setNode(imgHole);
		holeAppear.setFromValue(0);
		holeAppear.setToValue(1);
		
		FadeTransition conductEAppear = new FadeTransition(Duration.millis(500));
		conductEAppear.setNode(imgConductE);
		conductEAppear.setFromValue(0);
		conductEAppear.setToValue(1);
		*/
		ImageView imgExplosion = new ImageView(new Image(strExplosion));
		imgExplosion.setX(this.getParticle().getX());
		imgExplosion.setY(this.getParticle().getY());
		
		FadeTransition explosionIn = new FadeTransition(Duration.millis(500));
		explosionIn.setNode(imgExplosion);
		explosionIn.setFromValue(0);
		explosionIn.setToValue(1);
		
		FadeTransition explosionOut = new FadeTransition(Duration.millis(1000));
		explosionOut.setNode(imgExplosion);
		explosionOut.setFromValue(1);
		explosionOut.setToValue(0);
		
		FadeTransition valEdisappear = new FadeTransition(Duration.millis(200));
		valEdisappear.setNode(this.getParticle());
		valEdisappear.setFromValue(1);
		valEdisappear.setToValue(0);		
		
		ParallelTransition prlTrans1 = new ParallelTransition();
		prlTrans1.getChildren().addAll(valEdisappear, explosionIn);
		
		ParallelTransition prlTrans2 = new ParallelTransition();
		prlTrans2.getChildren().addAll(explosionOut, hole.appear(this), conductE.appear(this));
		
		SequentialTransition sqTrans = new SequentialTransition();
		sqTrans.getChildren().addAll(prlTrans1, prlTrans2);
		
		sqTrans.play();
		
	}

}
