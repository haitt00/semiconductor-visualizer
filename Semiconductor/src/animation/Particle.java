package animation;

import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.animation.Transition;
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
	
	public static void changeElectronAndHole(ValenceElectron electron, ValenceHole hole) {
		SequentialTransition sqTrans = new SequentialTransition();
		sqTrans.getChildren().addAll(electron.jumpToHole(hole), hole.jumpToElectron(electron));
		
		sqTrans.play();
	}
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
