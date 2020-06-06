package elements.charge;
import elements.atom.Atom;
import elements.view.ElementImage;
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
