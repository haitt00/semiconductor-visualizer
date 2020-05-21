package animation;

import java.util.Random;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

@SuppressWarnings({"deprecation","unchecked", "rawtypes"})


public abstract class Particle extends Transition{
	
	// temporary name for doing transition, will rename after intergration
	// need advice
	
	ImageView particle;
	
	public ImageView getParticle() {
		return particle;
	}

	public void setParticle(ImageView particle) {
		this.particle = particle;
	}

	public Particle() {
//		this.particle = new ImageView(img);
	}
	
	// animation for a particle to appear in a specified position
	
	public Transition appear(Particle electron) {
		double posX = electron.getParticle().getX();
		double posY = electron.getParticle().getY();
		
		FadeTransition fadeIn = new FadeTransition(Duration.millis(1000));
		fadeIn.setNode(this.getParticle());
		fadeIn.setFromValue(0);
		fadeIn.setToValue(1);
		
		TranslateTransition move = new TranslateTransition(Duration.millis(1000));
		move.setNode(this.getParticle());
		move.setByX(posX-this.getParticle().getX());
		move.setByY(posY-this.getParticle().getY());
		
		ParallelTransition prlTrans = new ParallelTransition();
		prlTrans.getChildren().addAll(fadeIn, move);
		
		return prlTrans;
	}
	
	// animation for a particle to disappear
	
	public Transition disappear() {
		FadeTransition fadeOut = new FadeTransition(Duration.millis(1000));
		fadeOut.setNode(this.getParticle());
		fadeOut.setFromValue(1);
		fadeOut.setToValue(0);
		
		return fadeOut;
	}
	
	
	// animation for exchanging position of valence electron and valence hole
	// when a valence electron and a valence hole are near enough
	// invoke this function
	// the electron will "jump" to the hole's position
	// then the hole will disappear then re-appear in the electron's previous position
	// parameters: the valence electron and valence hole
	// construct these in main code so that this fucntion can do its work
	
	public static void changeElectronAndHole(ValenceElectron electron, ValenceHole hole) {
		SequentialTransition sqTrans = new SequentialTransition();
		sqTrans.getChildren().addAll(electron.jumpToHole(hole), hole.disappear(), hole.appear(electron));
		
		sqTrans.play();
	}
	
	// animation for creating valence band electron 
	// due to combination of conduct electron and valence hole
	// when a conduct electron meet a valence hole
	// invoke this function
	// the conduct electron will spin and become smaller before disappear
	// and the hole disappear also
	// then the valence electron will appear in the hole's position
	// parameters: valence hole, conduct electron and valence electron
	// construct these in main code so that this function can do its work
	
	public static void createValenceBandE(ValenceHole hole, ConductElectron conductE, ValenceElectron valenceE) {
		SequentialTransition sqTrans = new SequentialTransition();
		ParallelTransition prlTrans = new ParallelTransition();
		
		prlTrans.getChildren().addAll(conductE.spinAndResize(), conductE.disappear(), hole.disappear(), valenceE.appear(hole));
		
		prlTrans.play();
		
	}
	
	// animation for translational move of valence electron and conduct electron
	// still need revise
	
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
        

		KeyFrame kf = new KeyFrame(Duration.millis(20), onFinished);        
        
        TimelineBuilder.create().keyFrames(kf).autoReverse(true).cycleCount(Timeline.INDEFINITE).build().play();
	}

	@Override
	protected void interpolate(double frac) {
		// TODO Auto-generated method stub
		
	}

}
