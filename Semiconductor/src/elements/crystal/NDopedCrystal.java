package elements.crystal;

import elements.atom.Atom;
import elements.atom.PhosphorusAtom;

public class NDopedCrystal extends DopedCrystal{
	
	public NDopedCrystal(String dopedRatio) {
		super(dopedRatio);
	}

	protected Atom getDopedTypeAtom(int x, int y) {
		return new PhosphorusAtom(x, y, this);
	}
}
