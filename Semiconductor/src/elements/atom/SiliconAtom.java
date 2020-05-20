package elements.atom;

import elements.Crystal;
import elements.charge.ValenceBandElectron;

public class SiliconAtom extends Atom{

	public SiliconAtom(int indexX, int indexY, Crystal container) {
		super(indexX, indexY, container);
		this.valenceCharges.put("up", new ValenceBandElectron(this)); // maybe initilze using indexX & Y of atom here
		this.valenceCharges.put("down", new ValenceBandElectron(this));
		this.valenceCharges.put("right", new ValenceBandElectron(this));
		this.valenceCharges.put("left", new ValenceBandElectron(this));
	}
	public String toString() {
		return "SI:"+super.toString();
	}
}
