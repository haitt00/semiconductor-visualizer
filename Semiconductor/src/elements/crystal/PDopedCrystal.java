package elements.crystal;

import elements.atom.AluminumAtom;
import elements.atom.Atom;

public class PDopedCrystal extends DopedCrystal{
	public PDopedCrystal(String dopedRatio) {
		super(dopedRatio);
	}

	protected Atom getDopedTypeAtom(int x, int y) {
		return new AluminumAtom(x, y, this);
	}
}
