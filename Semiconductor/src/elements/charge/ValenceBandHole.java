package elements.charge;

import elements.atom.Atom;
import elements.view.ElementImage;

public class ValenceBandHole extends ValenceBandCharge{

	
	public ValenceBandHole(Atom atom, String position) {
		super(atom);
		this.view = ElementImage.getValenceHoleImage();
		if(position.contentEquals("up")) {
			view.setX(atom.getView().getX()+Atom.atomViewRadius);
			view.setY(atom.getView().getY()-Atom.valenceViewPadding);
		}
		if(position.contentEquals("down")) {
			view.setX(atom.getView().getX()+Atom.atomViewRadius);
			view.setY(atom.getView().getY()+2*Atom.atomViewRadius+Atom.valenceViewPadding);
		}
		if(position.contentEquals("right")) {
			view.setX(atom.getView().getX()+2*Atom.atomViewRadius+Atom.valenceViewPadding);
			view.setY(atom.getView().getY()+Atom.atomViewRadius);
		}
		if(position.contentEquals("left")) {
			view.setX(atom.getView().getX()-Atom.valenceViewPadding);
			view.setY(atom.getView().getY()+Atom.atomViewRadius);
		}
	}
	
//	public String toString() {
//		return "o";
//	}
}
