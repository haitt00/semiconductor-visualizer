package animation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import settings.Settings;

public class ElementImage extends ImageView{
	//constructors
	public ElementImage(Image image) {
		super(image);
		
	}
	public ElementImage(String fileName, double scaleRatio) {
		String strName = "./src/images/"+fileName;
		FileInputStream inputFile = null;
		try {
			inputFile = new FileInputStream(strName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Image img = new Image(inputFile);
		this.setImage(img);
		this.setFitHeight(img.getHeight()*scaleRatio);
		this.setFitWidth(img.getWidth()*scaleRatio);
	}
	public static ElementImage getSilicImage() {
		return new ElementImage("atom-Silic.png", 0.25);
	}
	public static ElementImage getAluminumImage() {
		return new ElementImage("atom-Aluminum.png", 0.25);
	}
	public static ElementImage getPhosphorusImage() {
		return new ElementImage("atom-Phosphorus.png", 0.25);
	}
	public static ElementImage getValenceEImage() {
		return new ElementImage("particle-valence-e.png", 0.2);
	}
	public static ElementImage getValenceHoleImage() {
		return new ElementImage("particle-hole.png", 0.2);
	}
	public static ElementImage getConductingEImage() {
		return new ElementImage("particle-conduct-e.png", 0.2);
	}
	public static ElementImage getExplosionImage() {
		return new ElementImage("particle-explosion.png", 0.2);
	}
	
	//methods
	public Transition appear(double x, double y) {
		this.setX(x);
		this.setY(y);
		FadeTransition fadeIn = new FadeTransition(Duration.millis(Settings.transitionLength), this);
		fadeIn.setFromValue(0);
		fadeIn.setToValue(1);
		fadeIn.setOnFinished(evt->{
//			System.out.println("appear: "+fadeIn.getStatus());
			this.setTranslateX(0);
			this.setTranslateY(0);
//			System.out.println("get property:"+this.getX()+", "+this.getY());
//			System.out.println("get translate property:"+this.getTranslateX()+", "+this.getTranslateY());
//			System.out.println("get layout property:"+this.getLayoutX()+", "+this.getLayoutY());
		});
		return fadeIn;
	}
	public Transition disappear() {
		FadeTransition fadeOut = new FadeTransition(Duration.millis(Settings.transitionLength), this);
		fadeOut.setFromValue(1);
		fadeOut.setToValue(0);
		fadeOut.setOnFinished(evt->{
//			System.out.println("disappear: "+fadeOut.getStatus());
			this.setTranslateX(0);
			this.setTranslateY(0);
//			System.out.println("get property:"+this.getX()+", "+this.getY());
//			System.out.println("get translate property:"+this.getTranslateX()+", "+this.getTranslateY());
//			System.out.println("get layout property:"+this.getLayoutX()+", "+this.getLayoutY());
		});
		return fadeOut;
	}
	public Transition moveTranslate(double x, double y) {
		TranslateTransition move = new TranslateTransition(Duration.millis(Settings.transitionLength), this);
		System.out.println("transition length:"+Duration.millis(Settings.transitionLength));
		move.setByX(x);
		move.setByY(y);
		move.setInterpolator(Interpolator.LINEAR);
		move.setOnFinished(evt->{
//			System.out.println("straight: "+move.getStatus());
			this.setTranslateX(0);
			this.setTranslateY(0);
			this.setX(this.getX()+move.getByX());
			this.setY(this.getY()+move.getByY());
//			System.out.println("get property:"+this.getX()+", "+this.getY());
//			System.out.println("get translate property:"+this.getTranslateX()+", "+this.getTranslateY());
//			System.out.println("get layout property:"+this.getLayoutX()+", "+this.getLayoutY());
		});
		return move;
	} 
	public Transition moveChaotic() {
	//	ElementImage im = this;
		
		TranslateTransition move = new TranslateTransition();
		move.setByX(Math.random()*Settings.chaoticRate*0.3);
		move.setByY(Math.random()*Settings.chaoticRate*0.3);
		move.setDuration(Duration.millis(100));
		move.setCycleCount(10);
		move.setAutoReverse(true);
		move.setNode(this);
//		move.setOnFinished(e->{
//			this.setTranslateX(0);
//			this.setTranslateY(0);
//			this.setX(0);
//			this.setY(0);
//		});
		return move;
		
//		TranslateTransition front = new TranslateTransition();
//		TranslateTransition back = new TranslateTransition();
//		Random randf = new Random();
//		Random reverse = new Random();
//		
//		front.setNode(im);
//		back.setNode(im);
//		
//		front.setByX(randf.nextFloat()*5);
//		front.setByY(randf.nextFloat()*5);
//		front.setCycleCount((int)(25*10/Settings.chaoticRate));
//		
//		back.setByX(randf.nextFloat()*-5);
//		back.setByY(randf.nextFloat()*-5);
//		back.setCycleCount((int)(25*10/Settings.chaoticRate));
//		
//		if(reverse.nextInt(1) == 0)
//			return front;
//		else
//			return back;
		
//		EventHandler onFinished = new EventHandler<ActionEvent>() {
//    		boolean reverse = true;
//            public void handle(ActionEvent t) {
//            	Random randf = new Random();
//                	if(reverse) {	
//                        im.setX(im.getX()+randf.nextFloat()*5);
//                        im.setY(im.getY()+randf.nextFloat()*5);
//                	} else {
//                        im.setX(im.getX()-randf.nextFloat()*5);
//                        im.setY(im.getY()-randf.nextFloat()*5);
//                	}
//                	reverse = !reverse;
//            }
//        };
//		KeyFrame kf = new KeyFrame(Duration.millis(25*10/Settings.chaoticRate), onFinished);        
//        
//		Timeline timeline = new Timeline();
//		timeline.getKeyFrames().add(kf);
//		timeline.setAutoReverse(true);
//		timeline.setCycleCount(Timeline.INDEFINITE);
//		
//		return timeline;
		
   //     TimelineBuilder.create().keyFrames(kf).autoReverse(true).cycleCount(Timeline.INDEFINITE).build().play();
	}
	public Transition moveArc(double x, double y, boolean sweepFlag) {
		Path path = new Path();
		MoveTo mt = new MoveTo(this.getX(), this.getY());
		ArcTo at = new ArcTo(90, 90, 0, x, y, false, sweepFlag);
//		System.out.println("from "+this.getX()+", "+this.getY()+" to "+x+", "+y);
		path.getElements().add(mt);
		path.getElements().add(at);
		PathTransition move = new PathTransition(Duration.millis(Settings.transitionLength), path, this);
		move.setOnFinished(evt->{
//			System.out.println("arc: "+move.getStatus());
			this.setTranslateX(0);
			this.setTranslateY(0);
			this.setX(x);
			this.setY(y);
//			System.out.println("get property:"+this.getX()+", "+this.getY());
//			System.out.println("get translate property:"+this.getTranslateX()+", "+this.getTranslateY());
//			System.out.println("get layout property:"+this.getLayoutX()+", "+this.getLayoutY());
		});
		return move;
	}
	public Transition moveOutFrameAndBack(double x, double y) {
		Path path = new Path();
		MoveTo mt = new MoveTo(this.getX(), this.getY());
		ArcTo at = new ArcTo(300, 300, 0, x, y, true, true);
		path.getElements().add(mt);
		path.getElements().add(at);
		PathTransition move = new PathTransition(Duration.millis(Settings.transitionLength), path, this);
		move.setOnFinished(evt->{
//			System.out.println("out&back: "+move.getStatus());
			this.setTranslateX(0);
			this.setTranslateY(0);
			this.setX(x);
			this.setY(y);
//			System.out.println("get property:"+this.getX()+", "+this.getY());
//			System.out.println("get translate property:"+this.getTranslateX()+", "+this.getTranslateY());
//			System.out.println("get layout property:"+this.getLayoutX()+", "+this.getLayoutY());
		});
		return move;
	}
	public Transition spin() {
		RotateTransition rotate = new RotateTransition(Duration.millis(Settings.transitionLength));
		rotate.setNode(this);
		rotate.setAxis(Rotate.Z_AXIS);
		rotate.setByAngle(360);
		
		ScaleTransition scale = new ScaleTransition(Duration.millis(Settings.transitionLength));
		scale.setNode(this);
		scale.setByX(0.2);
		scale.setByY(0.2);
		
		ParallelTransition prlTrans = new ParallelTransition();
		prlTrans.getChildren().addAll(rotate, scale);
		
		return prlTrans;
	}
	
//	public Timeline shake() {
//		Timeline shakeTimeline = new Timeline(new KeyFrame(Duration.millis(0), new KeyValue(this.translateXProperty(), 0)),
//                        new KeyFrame(Duration.millis(100), new KeyValue(this.translateXProperty(), -10)),
//                        new KeyFrame(Duration.millis(100), new KeyValue(this.translateYProperty(), -10)),
//                        new KeyFrame(Duration.millis(200), new KeyValue(this.translateXProperty(), 10)),
//                        
//                        new KeyFrame(Duration.millis(300), new KeyValue(this.translateXProperty(), -10)),
//                        new KeyFrame(Duration.millis(300), new KeyValue(this.translateYProperty(), -10)),
//                        new KeyFrame(Duration.millis(400), new KeyValue(this.translateXProperty(), 10)),
//                        
//                        new KeyFrame(Duration.millis(500), new KeyValue(this.translateXProperty(), -10)),
//                        new KeyFrame(Duration.millis(500), new KeyValue(this.translateYProperty(), -10)),
//                        new KeyFrame(Duration.millis(600), new KeyValue(this.translateXProperty(), 10)),
//                        
//                        new KeyFrame(Duration.millis(700), new KeyValue(this.translateXProperty(), -10)),
//                        new KeyFrame(Duration.millis(700), new KeyValue(this.translateYProperty(), -10)),
//                        new KeyFrame(Duration.millis(800), new KeyValue(this.translateXProperty(), 10)),
//                        
//                        new KeyFrame(Duration.millis(900), new KeyValue(this.translateXProperty(), -10)),
//                        new KeyFrame(Duration.millis(900), new KeyValue(this.translateYProperty(), -10)),
//                        new KeyFrame(Duration.millis(1000), new KeyValue(this.translateXProperty(), 0))
//                        
//                );
//        shakeTimeline.setCycleCount(Timeline.INDEFINITE);
//        shakeTimeline.setDelay(Duration.seconds(0.2));
//        return shakeTimeline;
//	}
}
