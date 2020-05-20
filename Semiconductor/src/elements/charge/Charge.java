package elements.charge;
import elements.atom.Atom;
import utilities.Vector;
public abstract class Charge {

	private Atom containerAtom;
	public Charge(Atom atom) {
		this.containerAtom = atom;
	}
	private Vector coordinate;
	protected Vector getCoordinate() {
		return coordinate;
	}
	
}
