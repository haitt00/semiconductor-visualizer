package elements.charge;
import animation.ElementImage;
import elements.atom.Atom;
public abstract class Charge {

	private Atom containerAtom;
	protected ElementImage view;
	
	public Charge(Atom atom) {
		this.containerAtom = atom;
	}

	public Atom getContainerAtom() {
		return containerAtom;
	}

	public ElementImage getView() {
		return view;
	}
	
}
