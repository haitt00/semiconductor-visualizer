package elements.atom;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import elements.Crystal;
import elements.charge.ConductionBandElectron;
import elements.charge.ValenceBandCharge;
import elements.charge.ValenceBandHole;
import settings.Settings;

public class Atom {

	private int indexX;
	private int indexY;
	private Crystal container;
	protected HashMap<String, ValenceBandCharge> valenceCharges = new HashMap<String, ValenceBandCharge>();
	protected ConductionBandElectron conductingE;
	public Atom() {
		
	}
	
	public Atom(int indexX, int indexY, Crystal container) {
		super();
		this.indexX = indexX;
		this.indexY = indexY;
		this.container = container;
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

	protected void setContainer(Crystal container) {
		this.container = container;
	}

	protected ValenceBandCharge getValenceCharge(String position) {
		return valenceCharges.get(position);
	}
//	protected ValenceBandCharge getExternalCharge(String position) {
//		return this.getAdjacentAtom(position).getValenceCharge(Orientation.getOpposite(position));
//	}
	protected Atom getAdjacentAtom(String position) {
		if(position.contentEquals("up")) {
			if(this.indexX>0) {
				return this.getContainer().getAtomAt(this.indexX-1, this.indexY);
			}
			else return null;
		}
		if(position.contentEquals("down")) {
			if(this.indexX<Settings.crystalHeight-1) {
				return this.getContainer().getAtomAt(this.indexX+1, this.indexY);
			}
			else return null;
		}
		if(position.contentEquals("right")) {
			return this.getContainer().getAtomAt(this.indexX, (this.indexY+1)%Settings.crystalWidth);
		}
		
		return this.getContainer().getAtomAt(this.indexX, (this.indexY-1)%Settings.crystalWidth);
		
	}

//	protected ArrayList<ValenceBandCharge> getOtherValenceElectron(String position){
//		ArrayList<ValenceBandCharge> otherCharges = (ArrayList<ValenceBandCharge>) valenceCharges.values();
//		otherCharges.remove(this.getValenceCharge(position));
//		return  otherCharges;
//	}
	public String checkForHole() {
		Iterator<Entry<String, ValenceBandCharge>> it = this.valenceCharges.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, ValenceBandCharge> pair = (Map.Entry<String, ValenceBandCharge>) it.next();
//			System.out.println(pair.getKey() + " = " + pair.getValue());
			if(pair.getValue().getClass()==ValenceBandHole.class) {
				return pair.getKey();
			}
		}		
		return "none";
	}
	public boolean checkForConductingE() {
		return (this.conductingE!=null);
	}

	public String toString() {
		String str = this.valenceCharges.get("up").toString()+this.valenceCharges.get("down").toString()+this.valenceCharges.get("right").toString()+this.valenceCharges.get("left").toString();
		if(this.conductingE!=null) {
			str+="*";
		}
		return str;
	}
	
	//behavior 1 invoker
	public void passOnConductingE() {
		System.out.println("Old pos:"+this.indexX+", "+this.indexY);
		System.out.println("New pos:"+this.getAdjacentAtom("right").indexX+", "+this.getAdjacentAtom("right").indexY);
		this.getAdjacentAtom("right").conductingE = this.conductingE;
		this.conductingE = null;
		//TO-DO: call the animation
	}
	
	//behavior 2 & 3 invoker
	public void exchangeHoleWithElectron() {
		String Holeposition = this.checkForHole();
		ValenceBandCharge holeHolder = this.getValenceCharge(Holeposition);
		String newHolePosition;
		Atom ExchangingAtom;
		
			if(Holeposition=="left") {
				newHolePosition = "right";
				ExchangingAtom = this.getAdjacentAtom("left");
			}
			else {
				ExchangingAtom = this;
				if(Holeposition=="right") {
					if(new Random().nextInt(2)==0) {
						newHolePosition = "up";
					}
					else {
						newHolePosition = "down";
					}
				}
				else {
					newHolePosition = "left";
				}
			}
			this.valenceCharges.replace(Holeposition, ExchangingAtom.getValenceCharge(newHolePosition)); 
			ExchangingAtom.valenceCharges.replace(newHolePosition, holeHolder);
			//TO-DO: call the animation
	}
	
	//behavior 4 invoker
	public void diffuse() {
		
	}
	//behavior 5 invoker
	public void recombination() {
		
	}
	}
