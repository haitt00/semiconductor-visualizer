package elements.atom;

import elements.charge.ValenceBandElectron;

public class SiliconAtom extends Atom{

	public SiliconAtom(int indexX, int indexY) {
		super(indexX, indexY);
		this.valenceCharges.put("up", new ValenceBandElectron()); // maybe initilze using indexX & Y of atom here
		this.valenceCharges.put("down", new ValenceBandElectron());
		this.valenceCharges.put("right", new ValenceBandElectron());
		this.valenceCharges.put("left", new ValenceBandElectron());
	}
	public String toString() {
		return "SI:"+super.toString();
	}
}
