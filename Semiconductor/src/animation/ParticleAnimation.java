package animation;

import java.util.Random;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class ParticleAnimation extends Transition{
	
	// temporary name for doing transition, will rename after intergration
	// need advice
	
	ImageView particle;
	
	public ImageView getParticle() {
		return particle;
	}

	public void setParticle(ImageView particle) {
		this.particle = particle;
	}

	public ParticleAnimation(Image img) {
		this.particle = new ImageView(img);
	}
	
	public Transition jumpToHole(ParticleAnimation hole) {
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
	
	public Transition jumpToElectron(ParticleAnimation electron) {
		
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
		
	}
	
	public static void changeElectronAndHole(ParticleAnimation electron, ParticleAnimation hole) {
		SequentialTransition sqTrans = new SequentialTransition();
		sqTrans.getChildren().addAll(electron.jumpToHole(hole), hole.jumpToElectron(electron));
		
		sqTrans.play();
	}
	
	@SuppressWarnings("deprecation")
	public void translationalMove() {

		ImageView imgParticle = this.getParticle();

        EventHandler onFinished = new EventHandler<ActionEvent>() {
    		boolean reverse = true;
            public void handle(ActionEvent t) {
            	Random randf = new Random();
                	if(reverse) {
                        imgParticle.setX(imgParticle.getX()+randf.nextFloat()*20);
                        imgParticle.setY(imgParticle.getY()+randf.nextFloat()*10);
                	} else {
                        imgParticle.setX(imgParticle.getX()-randf.nextFloat()*10);
                        imgParticle.setY(imgParticle.getY()-randf.nextFloat()*10);
                	}
                	reverse = !reverse;
            }
        };
        
        KeyFrame kf = new KeyFrame(Duration.millis(50), onFinished);        
        
        TimelineBuilder.create().keyFrames(kf).autoReverse(true).cycleCount(Timeline.INDEFINITE).build().play();
	}

	@Override
	protected void interpolate(double frac) {
		// TODO Auto-generated method stub
		
	}

}
