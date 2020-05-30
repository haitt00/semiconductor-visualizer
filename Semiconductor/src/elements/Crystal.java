package elements;
import java.util.ArrayList;

import elements.atom.AluminumAtom;
import elements.atom.Atom;
import elements.atom.PhosphorusAtom;
import elements.atom.SiliconAtom;
import javafx.scene.layout.Pane;
import settings.Settings;

public class Crystal {

	private Atom atoms[][] = new Atom[Settings.crystalWidth][Settings.crystalHeight];
	
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
					if((x%2==0&&y%2==1)||(x%2==1&&y%2==0)) {
						atoms[x][y] = new AluminumAtom(x, y, this);
					}
					else {
						atoms[x][y] = new SiliconAtom(x, y, this);
					}
				}
			}
		}
		if(type.contentEquals("P")) {
			for (int y = 0; y < Settings.crystalHeight; y++) {
				for (int x = 0; x < Settings.crystalWidth; x++) {
					if((x%2==0&&y%2==1)||(x%2==1&&y%2==0)) {
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
		for (int y = 0; y < Settings.crystalHeight; y++) {
			for (int x = 0; x < Settings.crystalWidth; x++) {
				if(atoms[x][y].checkForConductingE()&&!(atoms[x][y].checkForHole().contentEquals("none"))) {
					behavior5Atoms.add(atoms[x][y]);
					continue;
				}
				if(atoms[x][y].checkForConductingE()) {
					behavior1Atoms.add(atoms[x][y]);
				}
				if (!(atoms[x][y].checkForHole().contentEquals("none"))) {
					behavior23Atoms.add(atoms[x][y]);
//					System.out.println("hole at:"+x+" "+y);
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
		for (int i = 0; i < behavior4Atoms.size(); i++) {
			behavior4Atoms.get(i).diffuse(root);
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
