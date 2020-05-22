package elements.charge;
import elements.atom.Atom;
public abstract class Charge {

	private Atom containerAtom;
	
	public Charge(Atom atom) {
		this.containerAtom = atom;
	}
	
}
