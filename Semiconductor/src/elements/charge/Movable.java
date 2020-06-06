package elements.charge;

import javafx.animation.Transition;

public interface Movable {
	public Transition appear(double x, double y);
	public Transition moveTranslate(double x, double y);
	public Transition moveChaotic();
	public Transition moveArc(double x, double y, boolean sweepFlag);
	public Transition moveOutFrameAndBack(double x, double y);
	public Transition spin();
}
