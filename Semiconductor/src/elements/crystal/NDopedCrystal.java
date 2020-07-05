package elements.crystal;

import elements.atom.AluminumAtom;
import elements.atom.Atom;

public class NDopedCrystal extends DopedCrystal{
	
	public NDopedCrystal(String dopedRatio) {
		super(dopedRatio);
	}

	protected Atom getDopedTypeAtom(int x, int y) {
		return new AluminumAtom(x, y, this);
	}
}
