package elements.crystal;

import java.util.ArrayList;
import java.util.Random;

import elements.atom.Atom;
import elements.atom.SiliconAtom;
import javafx.util.Pair;
import utils.Randomness;

public class DopedCrystal extends Crystal{
	private String dopeRatio;
	private int numberOfDopedOnRow;
	private int dopedRowsType;
	private ArrayList<Pair<Integer, Integer>> dopedPosition = new ArrayList<Pair<Integer,Integer>>();
	private void updateNumberOfDopedOnRows(String dopedRatio) {
		if(dopeRatio.contentEquals("light")) {
			this.numberOfDopedOnRow = 1;
		}
		if(dopeRatio.contentEquals("heavy")) {
			this.numberOfDopedOnRow = 3;
		}
	}
	private void generatedopedRowsType() {
		this.dopedRowsType = new Random().nextInt(2);
	}
	private void generateDopedPosition(String dopedRatio) {
		this.dopeRatio = dopedRatio;
		this.updateNumberOfDopedOnRows(dopedRatio);
		this.generatedopedRowsType();
		for (int y = 0; y < this.crystalHeight; y++) {
			if (y%2==dopedRowsType) {
				ArrayList<Integer> dopedXIndex = Randomness.getNonConsecutiveCombination(this.crystalWidth, numberOfDopedOnRow);
				for (int i = 0; i < dopedXIndex.size(); i++) {
					this.dopedPosition.add(new Pair<Integer, Integer>(dopedXIndex.get(i), y));
				}
				
			}
		}
	}
	protected Atom getDopedTypeAtom(int x, int y) {
		return new SiliconAtom(x, y, this);
	}
	public DopedCrystal(String dopedRatio) {
		this.generateDopedPosition(dopedRatio);
		for (int y = 0; y < this.crystalHeight; y++) {
			for (int x = 0; x < this.crystalWidth; x++) {
				if(this.dopedPosition.contains(new Pair<Integer, Integer>(x,y))) {
					this.atoms[x][y] = getDopedTypeAtom(x, y);
				}
//				else {
//					this.atoms[x][y] = new SiliconAtom(x, y, this);
//				}
			}
		}
	}
}
