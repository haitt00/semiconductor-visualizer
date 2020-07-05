package elements.atom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import elements.charge.ConductionBandElectron;
import elements.charge.ValenceBandCharge;
import elements.charge.ValenceBandHole;
import elements.crystal.Crystal;
import elements.view.ElementImage;
import javafx.scene.layout.Pane;

public class Atom {
	
	private int indexX;
	private int indexY;
	private Crystal container;
	protected ElementImage view;
	protected HashMap<String, ValenceBandCharge> valenceCharges = new HashMap<String, ValenceBandCharge>();
	protected ArrayList<ConductionBandElectron> conductingE = new ArrayList<ConductionBandElectron>();
	protected State atomState;
//	public Atom() {
//		
//	}
	
	public Atom(int indexX, int indexY, Crystal container) {
		super();
		this.indexX = indexX;
		this.indexY = indexY;
		this.container = container;
		this.valenceCharges= new HashMap<String, ValenceBandCharge>();
		this.conductingE = new ArrayList<ConductionBandElectron>();
		this.addCharges();
	}
	
	public ElementImage getView() {
		return view;
	}

	public int getIndexX() {
		return indexX;
	}

	public int getIndexY() {
		return indexY;
	}

	protected Crystal getContainer() {
		return container;
	}

	public ValenceBandCharge getValenceCharge(String position) {
		return valenceCharges.get(position);
	}
	
	public ConductionBandElectron getConductingE() {
		if(this.conductingE.isEmpty()) {
			return null;
		}
		return conductingE.get(0);
	}
	protected void addCharges() {
	}
	protected Atom getAdjacentAtom(String position) {
		if(position.contentEquals("up")) {
			if(this.indexY>0) {
				return this.getContainer().getAtomAt(this.indexX, this.indexY-1);
			}
			else return null;
		}
		if(position.contentEquals("down")) {
			if(this.indexY<this.container.crystalHeight-1) {
				return this.getContainer().getAtomAt(this.indexX, this.indexY+1);
			}
			else return null;
		}
		if(position.contentEquals("right")) {
			return this.getContainer().getAtomAt((this.indexX+1)%this.container.crystalWidth, this.indexY);
		}
		if(this.getIndexX()<1) {
			return this.getContainer().getAtomAt((this.indexX+this.container.crystalWidth-1), this.indexY);
		}
		return this.getContainer().getAtomAt((this.indexX-1), this.indexY);
	}

	public String checkForHole() {
		Iterator<Entry<String, ValenceBandCharge>> it = this.valenceCharges.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, ValenceBandCharge> pair = (Map.Entry<String, ValenceBandCharge>) it.next();
			if(pair.getValue().getClass()==ValenceBandHole.class) {
				return pair.getKey();
			}
		}		
		return "none";
	}
	public boolean checkForConductingE() {
		return (!this.conductingE.isEmpty());
	}
	
	public void updateState() {
		if(this.checkForConductingE()&&!(this.checkForHole().contentEquals("none"))) {
			this.atomState = new WithConductingEAndWithHoleState(this);
		}
		if(this.checkForConductingE()&&(this.checkForHole().contentEquals("none"))) {
			this.atomState = new WithConductingEAndNoHoleState(this);
		}
		if(!this.checkForConductingE()&&!(this.checkForHole().contentEquals("none"))) {
			this.atomState = new NoConductingEAndWithHoleState(this);
								 
		}
		if(!this.checkForConductingE()&&(this.checkForHole().contentEquals("none"))) {
			this.atomState = new NoConductingEAndNoHoleState(this);
		}
	}
	public void update(Pane root) {
		this.atomState.progress(root);
	}
}
