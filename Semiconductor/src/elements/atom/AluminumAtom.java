package elements.atom;

import elements.Crystal;
import elements.charge.ValenceBandElectron;
import elements.charge.ValenceBandHole;

public class AluminumAtom extends Atom{

	public AluminumAtom(){
		// TODO Auto-generated constructor stub
	}
	
	public AluminumAtom(int indexX, int indexY, Crystal container) {
		super(indexX, indexY, container);
		this.valenceCharges.put("up", new ValenceBandHole(this)); // maybe initilze using indexX & Y of atom here
		this.valenceCharges.put("down", new ValenceBandElectron(this));
		this.valenceCharges.put("right", new ValenceBandElectron(this));
		this.valenceCharges.put("left", new ValenceBandElectron(this));
	}

	public String toString() {
		return "AL:"+super.toString();
	}

}
