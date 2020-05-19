package elements;
import elements.atom.AluminumAtom;
import elements.atom.Atom;
import elements.atom.PhosphorusAtom;
import elements.atom.SiliconAtom;
import settings.Settings;

public class Crystal {

	public static Atom atoms[][] = new Atom[Settings.crystalHeight][Settings.crystalWidth];
	
	public Crystal() {
		
	}
	public void initCrystal(String type) {
		if(type.contentEquals("SI")) {
			for (int i = 0; i < Settings.crystalHeight; i++) {
				for (int j = 0; j < Settings.crystalWidth; j++) {
					atoms[i][j] = new SiliconAtom(i, j);
				}
			}
		}
		if(type.contentEquals("AL")) {
			for (int i = 0; i < Settings.crystalHeight; i++) {
				for (int j = 0; j < Settings.crystalWidth; j++) {
					if(i==2&j==2) {
						atoms[i][j] = new AluminumAtom(i, j);
					}
					else{
						atoms[i][j] = new SiliconAtom(i, j);
					}
				}
			}
		}
		if(type.contentEquals("P")) {
			for (int i = 0; i < Settings.crystalHeight; i++) {
				for (int j = 0; j < Settings.crystalWidth; j++) {
					if(i==2&j==2) {
						atoms[i][j] = new PhosphorusAtom(i, j);
					}
					else{
						atoms[i][j] = new SiliconAtom(i, j);
					}
				}
			}
		}
	}
	
	public void displayCrystal() {
		for (int i = 0; i < Settings.crystalHeight; i++) {
			for (int j = 0; j < Settings.crystalWidth; j++) {
				System.out.print(atoms[i][j].toString()+" ");
			}
			System.out.println();
		}
	}
	public void displayHolePosition() {
		for (int i = 0; i < Settings.crystalHeight; i++) {
			for (int j = 0; j < Settings.crystalWidth; j++) {
				System.out.print(atoms[i][j].checkForHole()+" ");
			}
			System.out.println();
		}
	}
	public void progress() {
		for (int i = 0; i < Settings.crystalHeight; i++) {
			for (int j = 0; j < Settings.crystalWidth; j++) {
				if(atoms[i][j].checkForConductingE()) {
					System.out.println("* move at coordinate:"+i+","+j);
					atoms[i][j].passOnConductingE();
					return;
				}
				if (!(atoms[i][j].checkForHole().contentEquals("none"))) {
					System.out.println("o move at coordinate:"+i+","+j);
					atoms[i][j].exchangeHoleWithElectron();
					return;
				}
			}
		}
	}
}
