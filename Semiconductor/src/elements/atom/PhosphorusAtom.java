package elements.atom;

import elements.charge.ConductionBandElectron;
import elements.charge.ValenceBandElectron;

public class PhosphorusAtom extends Atom{

	public PhosphorusAtom() {
		// TODO Auto-generated constructor stub
	}
	
	public PhosphorusAtom(int indexX, int indexY) {
		super(indexX, indexY);
		this.valenceCharges.put("up", new ValenceBandElectron()); // maybe initilze using indexX & Y of atom here
		this.valenceCharges.put("down", new ValenceBandElectron());
		this.valenceCharges.put("right", new ValenceBandElectron());
		this.valenceCharges.put("left", new ValenceBandElectron());
		this.conductingE = new ConductionBandElectron();
	}

	public String toString() {
		return "P:"+super.toString();
	}
}
