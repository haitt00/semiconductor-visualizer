package elements.charge;

import elements.atom.Atom;
import elements.crystal.Crystal;
import elements.view.ElementImage;
import javafx.animation.FadeTransition;
import javafx.animation.Transition;
import javafx.util.Duration;

public class ValenceBandHole extends ValenceBandCharge implements Movable{

	
	public ValenceBandHole(Atom atom, String position) {
		super(atom);
		this.view = ElementImage.getValenceHoleImage();
		super.setXY(position);
	}

	@Override
	public Transition appear(double x, double y) {
		this.getView().setX(x);
		this.getView().setY(y);
		FadeTransition fadeIn = new FadeTransition(Duration.millis(Crystal.getElectronCycle().get()), this.getView());
		fadeIn.setFromValue(0);
		fadeIn.setToValue(1);
		fadeIn.setOnFinished(evt->{
			this.getView().setTranslateX(0);
			this.getView().setTranslateY(0);
		});
		return fadeIn;
	}

	@Override
	public Transition moveTranslate(double x, double y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transition moveChaotic() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transition moveArc(double x, double y, boolean sweepFlag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transition moveOutFrameAndBack(double x, double y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transition spin() {
		// TODO Auto-generated method stub
		return null;
	}
	
//	public String toString() {
//		return "o";
//	}
}
