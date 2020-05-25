package animation;

import java.util.ArrayList;

import javafx.scene.layout.Pane;

public class Crystal {
	
	ArrayList<AtomImage> atomList = new ArrayList<AtomImage>();
	Pane crystal = new Pane();
	public static String P_TYPE = "p";
	public static String N_TYPE = "n";

	public ArrayList<AtomImage> getAtomList() {
		return atomList;
	}

	public void setAtomList(ArrayList<AtomImage> atomList) {
		this.atomList = atomList;
	}

	public Pane getCrystal() {
		return crystal;
	}

	public void setCrystal(Pane crystal) {
		this.crystal = crystal;
	}

	public Crystal(String type) {
		
		//initialize list of atom

		for(int row = 0; row < 5; row++) {
			for(int col = 0; col < 5; col++) {
				if((col%2==1&&row%2==0)||(col%2==0&row%2==1)) {
					atomList.add(new AtomImage(AtomImage.SILIC));
				} else {
					if(type.equalsIgnoreCase("p")) {
						atomList.add(new AtomImage(AtomImage.PHOTPHORUS));
					} else if(type.equalsIgnoreCase("n")) {
						atomList.add(new AtomImage(AtomImage.ALUMINUM));
					}

				}
			}
		}
		
		// arrange the atoms on pane
		int posRow = 0;
		int powCol = 0;
		for(int num = 0; num < atomList.size(); num++) {
			atomList.get(num).getCentralAtom().setX(0);
		}
	}

}
