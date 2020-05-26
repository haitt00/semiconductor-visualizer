package elements.charge;

import animation.ElementImage;
import animation.ParticleImage;
import animation.ValenceElectronImage;
import elements.atom.Atom;
import settings.Settings;

public class ValenceBandElectron extends ValenceBandCharge{

	public ValenceBandElectron(Atom atom, String position) {
		super(atom);
		this.view = ElementImage.getValenceEImage();
		if(position.contentEquals("up")) {
			view.setX(atom.getView().getX()+Settings.frameBias);
			view.setY(atom.getView().getY()-Settings.padding);
		}
		if(position.contentEquals("down")) {
			view.setX(atom.getView().getX()+Settings.frameBias);
			view.setY(atom.getView().getY()+2*Settings.frameBias+Settings.padding);
		}
		if(position.contentEquals("right")) {
			view.setX(atom.getView().getX()+2*Settings.frameBias+Settings.padding);
			view.setY(atom.getView().getY()+Settings.frameBias);
		}
		if(position.contentEquals("left")) {
			view.setX(atom.getView().getX()-Settings.padding);
			view.setY(atom.getView().getY()+Settings.frameBias);
		}
	}
	
	public String toString() {
		return "e";
	}
}
