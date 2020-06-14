package elements.charge;

import elements.atom.Atom;
import elements.view.ElementImage;

public class ValenceBandCharge extends Charge{

	public ValenceBandCharge(Atom atom) {
		super(atom);
	}

	protected void setXY(String position) {
		if(position.contentEquals("up")) {
			view.setX(this.getContainerAtom().getIndexX()*ElementImage.atomViewCell+ElementImage.atomViewCell/2+ElementImage.atomViewRadius);
			view.setY(this.getContainerAtom().getIndexY()*ElementImage.atomViewCell+ElementImage.atomViewCell/2-ElementImage.valenceViewPadding);
		}
		if(position.contentEquals("down")) {
			view.setX(this.getContainerAtom().getIndexX()*ElementImage.atomViewCell+ElementImage.atomViewCell/2+ElementImage.atomViewRadius);
			view.setY(this.getContainerAtom().getIndexY()*ElementImage.atomViewCell+ElementImage.atomViewCell/2+2*ElementImage.atomViewRadius+ElementImage.valenceViewPadding);
		}
		if(position.contentEquals("right")) {
			view.setX(this.getContainerAtom().getIndexX()*ElementImage.atomViewCell+ElementImage.atomViewCell/2+2*ElementImage.atomViewRadius+ElementImage.valenceViewPadding);
			view.setY(this.getContainerAtom().getIndexY()*ElementImage.atomViewCell+ElementImage.atomViewCell/2+ElementImage.atomViewRadius);
		}
		if(position.contentEquals("left")) {
			view.setX(this.getContainerAtom().getIndexX()*ElementImage.atomViewCell+ElementImage.atomViewCell/2-ElementImage.valenceViewPadding);
			view.setY(this.getContainerAtom().getIndexY()*ElementImage.atomViewCell+ElementImage.atomViewCell/2+ElementImage.atomViewRadius);
		}
	}
}
