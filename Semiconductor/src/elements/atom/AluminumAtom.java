package elements.atom;

import elements.charge.ValenceBandElectron;
import elements.charge.ValenceBandHole;
import elements.crystal.Crystal;
import elements.view.ElementImage;

public class AluminumAtom extends Atom{

//	public AluminumAtom(){
//		// TODO Auto-generated constructor stub
//	}
	
	public AluminumAtom(int indexX, int indexY, Crystal container) {
		super(indexX, indexY, container);
		this.view = ElementImage.getAluminumImage();
		this.view.setX(this.getIndexX()*Atom.atomViewCell+Atom.atomViewCell/2);
		this.view.setY(this.getIndexY()*Atom.atomViewCell+Atom.atomViewCell/2);
		this.valenceCharges.put("up", new ValenceBandHole(this, "up")); // maybe initilze using indexX & Y of atom here
		this.valenceCharges.put("down", new ValenceBandElectron(this, "down"));
		this.valenceCharges.put("right", new ValenceBandElectron(this, "right"));
		this.valenceCharges.put("left", new ValenceBandElectron(this, "left"));
	}

//	public String toString() {
//		return "AL:"+super.toString();
//	}

}
