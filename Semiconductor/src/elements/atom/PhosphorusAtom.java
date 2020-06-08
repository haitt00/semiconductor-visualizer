package elements.atom;

import elements.charge.ConductionBandElectron;
import elements.charge.ValenceBandElectron;
import elements.crystal.Crystal;
import elements.view.ElementImage;

public class PhosphorusAtom extends Atom{

//	public PhosphorusAtom() {
//		// TODO Auto-generated constructor stub
//	}
	
	public PhosphorusAtom(int indexX, int indexY, Crystal container) {
		super(indexX, indexY, container);
		this.view = ElementImage.getPhosphorusImage();
		this.view.setX(this.getIndexX()*ElementImage.atomViewCell+ElementImage.atomViewCell/2);
		this.view.setY(this.getIndexY()*ElementImage.atomViewCell+ElementImage.atomViewCell/2);
		this.valenceCharges.put("up", new ValenceBandElectron(this, "up")); // maybe initilze using indexX & Y of atom here
		this.valenceCharges.put("down", new ValenceBandElectron(this, "down"));
		this.valenceCharges.put("right", new ValenceBandElectron(this, "right"));
		this.valenceCharges.put("left", new ValenceBandElectron(this, "left"));
		this.conductingE = new ConductionBandElectron(this);
		
	}

//	public String toString() {
//		return "P:"+super.toString();
//	}
}
