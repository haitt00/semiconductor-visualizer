package elements;
import java.util.ArrayList;

import elements.atom.AluminumAtom;
import elements.atom.Atom;
import elements.atom.PhosphorusAtom;
import elements.atom.SiliconAtom;
import javafx.scene.layout.Pane;
import settings.Settings;

public class Crystal {

	private Atom atoms[][] = new Atom[Settings.crystalHeight][Settings.crystalWidth];
	
	public Crystal() {
		
	}
	public Atom getAtomAt(int x, int y) {
		return atoms[x][y];
	}
	public void initCrystal(String type) {
		if(type.contentEquals("SI")) {
			for (int y = 0; y < Settings.crystalHeight; y++) {
				for (int x = 0; x < Settings.crystalWidth; x++) {
					atoms[x][y] = new SiliconAtom(x, y, this);
				}
			}
		}
		if(type.contentEquals("AL")) {
			for (int y = 0; y < Settings.crystalHeight; y++) {
				for (int x = 0; x < Settings.crystalWidth; x++) {
					if(x==0&y==2) {
						atoms[x][y] = new AluminumAtom(x, y, this);
					}
					else{
						atoms[x][y] = new SiliconAtom(x, y, this);
					}
				}
			}
		}
		if(type.contentEquals("P")) {
			for (int y = 0; y < Settings.crystalHeight; y++) {
				for (int x = 0; x < Settings.crystalWidth; x++) {
					if(x==3&y==2) {
						atoms[x][y] = new PhosphorusAtom(x, y, this);
					}
					else{
						atoms[x][y] = new SiliconAtom(x, y, this);
					}
				}
			}
		}
	}
	
	public void displayCrystal() {
		for (int j = 0; j < Settings.crystalHeight; j++) {
			for (int i = 0; i < Settings.crystalWidth; i++) {
				System.out.print(atoms[i][j].toString()+" ");
			}
			System.out.println();
		}
	}
	public void displayHolePosition() {
		for (int j = 0; j < Settings.crystalHeight; j++) {
			for (int i = 0; i < Settings.crystalWidth; i++) {
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
		for (int i = 0; i < Settings.crystalHeight; i++) {
			for (int j = 0; j < Settings.crystalWidth; j++) {
				if(atoms[i][j].checkForConductingE()&&!(atoms[i][j].checkForHole().contentEquals("none"))) {
					behavior5Atoms.add(atoms[i][j]);
					continue;
				}
				if(atoms[i][j].checkForConductingE()) {
					behavior1Atoms.add(atoms[i][j]);
				}
				if (!(atoms[i][j].checkForHole().contentEquals("none"))) {
					behavior23Atoms.add(atoms[i][j]);
					System.out.println("hole at:"+i+" "+j);
				}
			}
		}
		for (int i = 0; i < behavior1Atoms.size(); i++) {
			behavior1Atoms.get(i).passOnConductingE();
		}
		for (int i = 0; i < behavior23Atoms.size(); i++) {
			behavior23Atoms.get(i).exchangeHoleWithElectron();
		}
		for (int i = 0; i < behavior5Atoms.size(); i++) {
			behavior5Atoms.get(i).recombination(root);
		}
//		for (int i = 0; i < Settings.crystalHeight; i++) {
//			for (int j = 0; j < Settings.crystalWidth; j++) {
//				if(atoms[i][j].checkForConductingE()) {
//					System.out.println("* move at coordinate:"+i+","+j);
//					atoms[i][j].passOnConductingE();
//					return;
//				}
//				if (!(atoms[i][j].checkForHole().contentEquals("none"))) {
//					System.out.println("o move at coordinate:"+i+","+j);
//					atoms[i][j].exchangeHoleWithElectron();
//					return;
//				}
//			}
//		}
	}
}
