package elements.crystal;
import java.util.ArrayList;
import java.util.Random;

import elements.atom.AluminumAtom;
import elements.atom.Atom;
import elements.atom.PhosphorusAtom;
import elements.atom.SiliconAtom;
import environment.Environment;
import javafx.scene.layout.Pane;
import utils.Randomness;

public class Crystal {

	public final int crystalHeight = 5;
	public final int crystalWidth = 6;
	private Atom atoms[][] = new Atom[this.crystalWidth][this.crystalHeight];
	
	public Crystal() {
		
	}
	public Atom getAtomAt(int x, int y) {
		return atoms[x][y];
	}
	public void initCrystal(String type, String dopeRatio) {
		int numberOfDopedOnRow = 0;
		if(type.contentEquals("SI")) {
			for (int y = 0; y < this.crystalHeight; y++) {
				for (int x = 0; x < this.crystalWidth; x++) {
					atoms[x][y] = new SiliconAtom(x, y, this);
				}
			}
		}
		else {
			if(dopeRatio.contentEquals("light")) {
				numberOfDopedOnRow = 1;
			}
			if(dopeRatio.contentEquals("heavy")) {
				numberOfDopedOnRow = 3;
			}
			int dopedRowsType = new Random().nextInt(2); 
			for (int y = 0; y < this.crystalHeight; y++) {
				ArrayList<Integer> dopedXIndex = new ArrayList<Integer>();
				if (y%2==dopedRowsType) {
					dopedXIndex = Randomness.getNonConsecutiveCombination(this.crystalWidth, numberOfDopedOnRow);
				}
				for (int x = 0; x < this.crystalWidth; x++) {
					if(dopedXIndex.contains(x)) {
						if(type.contentEquals("AL")) {
							atoms[x][y] = new AluminumAtom(x, y, this);
						}
						else {
							atoms[x][y] = new PhosphorusAtom(x, y, this);
						}
					}
					else {
						atoms[x][y] = new SiliconAtom(x, y, this);
					}
				}
			}
		}
	}
	
	public void displayCrystal() {
		for (int j = 0; j < this.crystalHeight; j++) {
			for (int i = 0; i < this.crystalWidth; i++) {
				System.out.print(atoms[i][j].toString()+" ");
			}
			System.out.println();
		}
	}
	public void displayHolePosition() {
		for (int j = 0; j < this.crystalHeight; j++) {
			for (int i = 0; i < this.crystalWidth; i++) {
				System.out.print(atoms[i][j].checkForHole()+" ");
			}
			System.out.println();
		}
	}
	public void progress(Pane root) {
		ArrayList<Atom> behavior23Atoms = new ArrayList<>();
		ArrayList<Atom> behavior1Atoms = new ArrayList<>();
		ArrayList<Atom> behavior4Atoms = new ArrayList<>();
		ArrayList<Atom> behavior5Atoms = new ArrayList<>();
		for (int y = 0; y < this.crystalHeight; y++) {
			ArrayList<Integer> diffuseCandidateIndex = new ArrayList<>();
			for (int x = 0; x < this.crystalWidth; x++) {
				//if atom has both e and o, either seperate (1-2-3) or recombination (5)
				if(atoms[x][y].checkForConductingE()&&!(atoms[x][y].checkForHole().contentEquals("none"))) {
					Double separateChance = Math.random();
					if(separateChance<Environment.seperateProb.get()) {
						behavior1Atoms.add(atoms[x][y]);
						behavior23Atoms.add(atoms[x][y]);
					}
					else {
						behavior5Atoms.add(atoms[x][y]);
					}
				}
				//if atom has e and no o, e will move (1)
				if(atoms[x][y].checkForConductingE()&&(atoms[x][y].checkForHole().contentEquals("none"))) {
					behavior1Atoms.add(atoms[x][y]);
				}
				//if atom has o and no e, o will move (2-3)
				if ((!(atoms[x][y].checkForConductingE()))&&!(atoms[x][y].checkForHole().contentEquals("none"))) {
					behavior23Atoms.add(atoms[x][y]);
//					System.out.println("hole at:"+x+" "+y);
				}
				//if atom has neither o or e, it might diffuse (diffuse selection will be done after entire row is updated)
				if((!(atoms[x][y].checkForConductingE()))&&(atoms[x][y].checkForHole().contentEquals("none"))) {
					diffuseCandidateIndex.add(x);
				}
			}
			if(diffuseCandidateIndex.size()==this.crystalWidth) {
				Double diffuseChance = Math.random();
				if(diffuseChance<Environment.diffuseProb.get()) {
					int roulett = new Random().nextInt(diffuseCandidateIndex.size());
					behavior4Atoms.add(atoms[roulett][y]);
				}
			}
			
		}
//		System.out.println("\nlist1:");
		for (int i = 0; i < behavior1Atoms.size(); i++) {
			behavior1Atoms.get(i).passOnConductingE();
//			System.out.print("("+behavior1Atoms.get(i).getIndexX()+","+behavior1Atoms.get(i).getIndexY()+")");
		}
//		System.out.println("\nlist23:");
		for (int i = 0; i < behavior23Atoms.size(); i++) {
			behavior23Atoms.get(i).exchangeHoleWithElectron();
//			System.out.print("("+behavior23Atoms.get(i).getIndexX()+","+behavior23Atoms.get(i).getIndexY()+")");
		}
//		System.out.println("\nlist5:");
		for (int i = 0; i < behavior5Atoms.size(); i++) {
			behavior5Atoms.get(i).recombination(root);
//			System.out.print("("+behavior5Atoms.get(i).getIndexX()+","+behavior5Atoms.get(i).getIndexY()+")");
		}
//		System.out.println("\nlist4:");
		for (int i = 0; i < behavior4Atoms.size(); i++) {
			behavior4Atoms.get(i).diffuse(root);
//			System.out.print("("+behavior4Atoms.get(i).getIndexX()+","+behavior4Atoms.get(i).getIndexY()+")");
		}
	}
}
