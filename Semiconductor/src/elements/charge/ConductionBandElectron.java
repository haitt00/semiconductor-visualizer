package elements.charge;

import elements.atom.Atom;
import elements.crystal.Crystal;
import elements.view.ElementImage;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class ConductionBandElectron extends Charge implements  Movable{

	public ConductionBandElectron(Atom atom) {
		super(atom);
		this.view = ElementImage.getConductingEImage();
		view.setX(atom.getView().getX()+2*ElementImage.atomViewRadius+ElementImage.valenceViewPadding/2);
		view.setY(atom.getView().getY()-ElementImage.valenceViewPadding/2);
		//upper right corner
	}

	@Override
	public Transition appear(double x, double y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transition moveTranslate(double x, double y) {
		TranslateTransition move = new TranslateTransition(Duration.millis(Crystal.getElectronCycle().get()), this.getView());
		move.setByX(x);
		move.setByY(y);
		move.setInterpolator(Interpolator.LINEAR);
		move.setOnFinished(evt->{
			this.getView().setTranslateX(0);
			this.getView().setTranslateY(0);
			this.getView().setX(this.getView().getX()+move.getByX());
			this.getView().setY(this.getView().getY()+move.getByY());
		});
		return move;
	}

	@Override
	public Transition moveChaotic() {
		TranslateTransition move = new TranslateTransition();
		move.setByX(Math.random()*Crystal.getVibrationRange().get());
		move.setByY(Math.random()*Crystal.getVibrationRange().get());
		move.setDuration(Duration.millis(100));
		move.setCycleCount(12);
		move.setAutoReverse(true);
		move.setNode(this.getView());
		return move;
	}

	@Override
	public Transition moveArc(double x, double y, boolean sweepFlag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transition moveOutFrameAndBack(double x, double y) {
		Path path = new Path();
		MoveTo mt = new MoveTo(this.getView().getX(), this.getView().getY());
		ArcTo at = new ArcTo(1000, 1000, 0, x, y, true, true);
		path.getElements().add(mt);
		path.getElements().add(at);
		PathTransition move = new PathTransition(Duration.millis(Crystal.getElectronCycle().get()), path, this.getView());
		move.setOnFinished(evt->{
			this.getView().setTranslateX(0);
			this.getView().setTranslateY(0);
			this.getView().setX(x);
			this.getView().setY(y);
		});
		return move;
	}

	@Override
	public Transition spin() {
		RotateTransition rotate = new RotateTransition(Duration.millis(Crystal.getElectronCycle().get()));
		rotate.setNode(this.getView());
		rotate.setAxis(Rotate.Z_AXIS);
		rotate.setByAngle(360);
		
		ScaleTransition scale = new ScaleTransition(Duration.millis(Crystal.getElectronCycle().get()));
		scale.setNode(this.getView());
		scale.setByX(0.2);
		scale.setByY(0.2);
		
		ParallelTransition prlTrans = new ParallelTransition();
		prlTrans.getChildren().addAll(rotate, scale);
		
		return prlTrans;
	}
	
//	public String toString() {
//		return "e";
//	}

}
