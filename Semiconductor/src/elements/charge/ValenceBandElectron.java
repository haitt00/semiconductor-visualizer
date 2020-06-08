package elements.charge;

import elements.atom.Atom;
import elements.crystal.Crystal;
import elements.view.ElementImage;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class ValenceBandElectron extends ValenceBandCharge implements Movable{

	public ValenceBandElectron(Atom atom, String position) {
		super(atom);
		this.view = ElementImage.getValenceEImage();
		if(position.contentEquals("up")) {
			view.setX(atom.getView().getX()+ElementImage.atomViewRadius);
			view.setY(atom.getView().getY()-ElementImage.valenceViewPadding);
		}
		if(position.contentEquals("down")) {
			view.setX(atom.getView().getX()+ElementImage.atomViewRadius);
			view.setY(atom.getView().getY()+2*ElementImage.atomViewRadius+ElementImage.valenceViewPadding);
		}
		if(position.contentEquals("right")) {
			view.setX(atom.getView().getX()+2*ElementImage.atomViewRadius+ElementImage.valenceViewPadding);
			view.setY(atom.getView().getY()+ElementImage.atomViewRadius);
		}
		if(position.contentEquals("left")) {
			view.setX(atom.getView().getX()-ElementImage.valenceViewPadding);
			view.setY(atom.getView().getY()+ElementImage.atomViewRadius);
		}
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transition moveArc(double x, double y, boolean sweepFlag) {
		Path path = new Path();
		MoveTo mt = new MoveTo(this.getView().getX(), this.getView().getY());
		ArcTo at = new ArcTo(90, 90, 0, x, y, false, sweepFlag);
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
		// TODO Auto-generated method stub
		return null;
	}
	
//	public String toString() {
//		return "e";
//	}
}
