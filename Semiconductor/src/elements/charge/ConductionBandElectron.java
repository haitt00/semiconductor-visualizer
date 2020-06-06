package elements.charge;

import elements.atom.Atom;
import elements.view.ElementImage;
import settings.Settings;

public class ConductionBandElectron extends Charge{

	public ConductionBandElectron(Atom atom) {
		super(atom);
		this.view = ElementImage.getConductingEImage();
		view.setX(atom.getView().getX()+2*Settings.frameBias+Settings.padding/2);
		view.setY(atom.getView().getY()-Settings.padding/2);
		//upper right corner
	}
	
//	public String toString() {
//		return "e";
//	}

}
