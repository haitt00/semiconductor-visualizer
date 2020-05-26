package animation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class ElementImage extends ImageView{
	private final Duration length = Duration.millis(1000);
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
		FadeTransition fadeIn = new FadeTransition(this.length, this);
		fadeIn.setFromValue(0);
		fadeIn.setToValue(1);
		fadeIn.setOnFinished(evt->{
			System.out.println("appear: "+fadeIn.getStatus());
			this.setTranslateX(0);
			this.setTranslateY(0);
			System.out.println("get property:"+this.getX()+", "+this.getY());
			System.out.println("get translate property:"+this.getTranslateX()+", "+this.getTranslateY());
			System.out.println("get layout property:"+this.getLayoutX()+", "+this.getLayoutY());
		});
		return fadeIn;
	}
	public Transition disappear() {
		FadeTransition fadeOut = new FadeTransition(this.length, this);
		fadeOut.setFromValue(1);
		fadeOut.setToValue(0);
		fadeOut.setOnFinished(evt->{
			System.out.println("disappear: "+fadeOut.getStatus());
			this.setTranslateX(0);
			this.setTranslateY(0);
			System.out.println("get property:"+this.getX()+", "+this.getY());
			System.out.println("get translate property:"+this.getTranslateX()+", "+this.getTranslateY());
			System.out.println("get layout property:"+this.getLayoutX()+", "+this.getLayoutY());
		});
		return fadeOut;
	}
	public Transition moveTranslate(double x, double y) {
		TranslateTransition move = new TranslateTransition(this.length, this);
		move.setByX(x);
		move.setByY(y);
		move.setInterpolator(Interpolator.LINEAR);
		move.setOnFinished(evt->{
			System.out.println("straight: "+move.getStatus());
			this.setTranslateX(0);
			this.setTranslateY(0);
			this.setX(this.getX()+move.getByX());
			this.setY(this.getY()+move.getByY());
			System.out.println("get property:"+this.getX()+", "+this.getY());
			System.out.println("get translate property:"+this.getTranslateX()+", "+this.getTranslateY());
			System.out.println("get layout property:"+this.getLayoutX()+", "+this.getLayoutY());
		});
		return move;
	} 
//	public Transition moveChaotic(int x, int y) {
//		ElementImage im = this;
//		EventHandler onFinished = new EventHandler<ActionEvent>() {
//    		boolean reverse = true;
//            public void handle(ActionEvent t) {
//            	Random randf = new Random();
//                	if(reverse) {	
//                        im.setX(im.getX()+randf.nextFloat()*20);
//                        im.setY(im.getY()+randf.nextFloat()*10);
//                	} else {
//                        im.setX(im.getX()-randf.nextFloat()*10);
//                        im.setY(im.getY()-randf.nextFloat()*10);
//                	}
//                	reverse = !reverse;
//            }
//        };
//		KeyFrame kf = new KeyFrame(Duration.millis(20), onFinished);        
//        
//        TimelineBuilder.create().keyFrames(kf).autoReverse(true).cycleCount(Timeline.INDEFINITE).build().play();
//	}
	public Transition moveArc(double x, double y, boolean sweepFlag) {
		Path path = new Path();
		MoveTo mt = new MoveTo(this.getX(), this.getY());
		ArcTo at = new ArcTo(90, 90, 0, x, y, false, sweepFlag);
		System.out.println("from "+this.getX()+", "+this.getY()+" to "+x+", "+y);
		path.getElements().add(mt);
		path.getElements().add(at);
		PathTransition move = new PathTransition(this.length, path, this);
		move.setOnFinished(evt->{
			System.out.println("arc: "+move.getStatus());
			this.setTranslateX(0);
			this.setTranslateY(0);
			this.setX(x);
			this.setY(y);
			System.out.println("get property:"+this.getX()+", "+this.getY());
			System.out.println("get translate property:"+this.getTranslateX()+", "+this.getTranslateY());
			System.out.println("get layout property:"+this.getLayoutX()+", "+this.getLayoutY());
		});
		return move;
	}
	public Transition moveOutFrameAndBack(double x, double y) {
		Path path = new Path();
		MoveTo mt = new MoveTo(this.getX(), this.getY());
		ArcTo at = new ArcTo(300, 300, 0, x, y, true, true);
		path.getElements().add(mt);
		path.getElements().add(at);
		PathTransition move = new PathTransition(this.length, path, this);
		move.setOnFinished(evt->{
			System.out.println("out&back: "+move.getStatus());
			this.setTranslateX(0);
			this.setTranslateY(0);
			this.setX(x);
			this.setY(y);
			System.out.println("get property:"+this.getX()+", "+this.getY());
			System.out.println("get translate property:"+this.getTranslateX()+", "+this.getTranslateY());
			System.out.println("get layout property:"+this.getLayoutX()+", "+this.getLayoutY());
		});
		return move;
	}
	public Transition spin() {
		RotateTransition rotate = new RotateTransition(this.length);
		rotate.setNode(this);
		rotate.setAxis(Rotate.Z_AXIS);
		rotate.setByAngle(360);
		
		ScaleTransition scale = new ScaleTransition(this.length);
		scale.setNode(this);
		scale.setByX(0.2);
		scale.setByY(0.2);
		
		ParallelTransition prlTrans = new ParallelTransition();
		prlTrans.getChildren().addAll(rotate, scale);
		
		return prlTrans;
	}
}
