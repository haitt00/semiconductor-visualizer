package elements.atom;

import elements.charge.ValenceBandElectron;
import elements.charge.ValenceBandHole;

public class AluminumAtom extends Atom{

	public AluminumAtom(){
		// TODO Auto-generated constructor stub
	}
	
	public AluminumAtom(int indexX, int indexY) {
		super(indexX, indexY);
		this.valenceCharges.put("up", new ValenceBandHole()); // maybe initilze using indexX & Y of atom here
		this.valenceCharges.put("down", new ValenceBandElectron());
		this.valenceCharges.put("right", new ValenceBandElectron());
		this.valenceCharges.put("left", new ValenceBandElectron());
	}

	public String toString() {
		return "AL:"+super.toString();
	}

}
