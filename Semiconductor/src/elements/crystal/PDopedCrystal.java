package elements.crystal;

import elements.atom.AluminumAtom;
import elements.atom.Atom;
import elements.atom.PhosphorusAtom;

public class PDopedCrystal extends DopedCrystal{
	public PDopedCrystal(String dopedRatio) {
		super(dopedRatio);
	}

	protected Atom getDopedTypeAtom(int x, int y) {
		return new PhosphorusAtom(x, y, this);
	}
}
