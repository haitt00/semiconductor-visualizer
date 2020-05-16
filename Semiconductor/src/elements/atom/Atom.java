package elements.atom;
import java.util.ArrayList;
import java.util.HashMap;

import elements.Crystal;
import elements.charge.ValenceBandCharge;
import elements.charge.ValenceBandHole;
import settings.Settings;
import utilities.Orientation;

public class Atom {

	public Atom() {
		// TODO Auto-generated constructor stub
	}
	private int indexX;
	private int indexY;
	HashMap<String, ValenceBandCharge> valenceCharges = new HashMap<String, ValenceBandCharge>();
	
	protected ValenceBandCharge getValenceCharge(String position) {
		return valenceCharges.get(position);
	}
	protected ValenceBandCharge getExternalCharge(String position) {
		return this.getAdjacentAtom(position).getValenceCharge(Orientation.getOpposite(position));
	}
	protected Atom getAdjacentAtom(String position) {
		if(position.contentEquals("up")) {
			if(this.indexY<Settings.crystalHeight-1) {
				return Crystal.atoms[this.indexX][this.indexY+1];
			}
			else return null;
		}
		if(position.contentEquals("down")) {
			if(this.indexY>0) {
				return Crystal.atoms[this.indexX][this.indexY-1];
			}
			else return null;
		}
		if(position.contentEquals("right")) {
			if(this.indexX<Settings.crystalWidth-1) {
				return Crystal.atoms[this.indexX+1][this.indexY];
			}
			else return null;
		}
		if(this.indexX>0) {
			return Crystal.atoms[this.indexX-1][this.indexY];
		}
		else return null;
	}

	protected ArrayList<ValenceBandCharge> getOtherValenceElectron(String position){
		ArrayList<ValenceBandCharge> otherCharges = (ArrayList<ValenceBandCharge>) valenceCharges.values();
		otherCharges.remove(this.getValenceCharge(position));
		return  otherCharges;
	}
	public String checkForHole() {
		valenceCharges.forEach((positon,valenceCharge) -> System.out.println(positon + " = " + valenceCharge));
		valenceCharges.forEach((positon,valenceCharge) -> {if(valenceCharge.getClass()==ValenceBandHole.class) return ;});
		return "none";
	}
	public void exchangeHoleWithElectron() {
		if (!(this.checkForHole().contentEquals("none"))) {
			
		}
	}
}
