package elements.charge;

import elements.atom.Atom;
import elements.view.ElementImage;

public class ConductionBandElectron extends Charge{

	public ConductionBandElectron(Atom atom) {
		super(atom);
		this.view = ElementImage.getConductingEImage();
		view.setX(atom.getView().getX()+2*Atom.atomViewRadius+Atom.valenceViewPadding/2);
		view.setY(atom.getView().getY()-Atom.valenceViewPadding/2);
		//upper right corner
	}
	
//	public String toString() {
//		return "e";
//	}

}
