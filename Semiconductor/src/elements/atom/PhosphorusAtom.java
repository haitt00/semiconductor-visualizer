package elements.atom;

import elements.Crystal;
import elements.charge.ConductionBandElectron;
import elements.charge.ValenceBandElectron;

public class PhosphorusAtom extends Atom{

	public PhosphorusAtom() {
		// TODO Auto-generated constructor stub
	}
	
	public PhosphorusAtom(int indexX, int indexY, Crystal container) {
		super(indexX, indexY, container);
		this.valenceCharges.put("up", new ValenceBandElectron(this)); // maybe initilze using indexX & Y of atom here
		this.valenceCharges.put("down", new ValenceBandElectron(this));
		this.valenceCharges.put("right", new ValenceBandElectron(this));
		this.valenceCharges.put("left", new ValenceBandElectron(this));
		this.conductingE = new ConductionBandElectron(this);
	}

	public String toString() {
		return "P:"+super.toString();
	}
}
