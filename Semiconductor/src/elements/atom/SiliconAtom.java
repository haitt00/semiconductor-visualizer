package elements.atom;

import elements.charge.ValenceBandElectron;
import elements.crystal.Crystal;
import elements.view.ElementImage;

public class SiliconAtom extends Atom{

	public SiliconAtom(int indexX, int indexY, Crystal container) {
		super(indexX, indexY, container);
		this.view = ElementImage.getSilicImage();
		this.view.setX(this.getIndexX()*ElementImage.atomViewCell+ElementImage.atomViewCell/2);
		this.view.setY(this.getIndexY()*ElementImage.atomViewCell+ElementImage.atomViewCell/2);
	}
	protected void addCharges() {
		super.addCharges();
		this.valenceCharges.put("up", new ValenceBandElectron(this, "up")); // maybe initilze using indexX & Y of atom here
		this.valenceCharges.put("down", new ValenceBandElectron(this, "down"));
		this.valenceCharges.put("right", new ValenceBandElectron(this, "right"));
		this.valenceCharges.put("left", new ValenceBandElectron(this, "left"));
	}
//	public String toString() {
//		return "SI:"+super.toString();
//	}
}
