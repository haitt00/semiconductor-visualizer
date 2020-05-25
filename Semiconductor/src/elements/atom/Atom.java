package elements.atom;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import animation.AtomImage;
import animation.ElementImage;
import animation.ParticleImage;
import animation.ValenceElectronImage;
import animation.ValenceHoleImage;
import elements.Crystal;
import elements.charge.ConductionBandElectron;
import elements.charge.ValenceBandCharge;
import elements.charge.ValenceBandElectron;
import elements.charge.ValenceBandHole;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import settings.Settings;

public class Atom {

	private int indexX;
	private int indexY;
	private Crystal container;
	protected ElementImage view;
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
	
//	public getCooridinate(String chargeType) {
//		if(chargeType.contentEquals("up")) {
//			
//		}
//		if(chargeType.contentEquals("down")) {
//			
//		}
//		if(chargeType.contentEquals("right")) {
//			
//		}
//		if(chargeType.contentEquals("left")) {
//			
//		}
//	}
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

	protected void setContainer(Crystal container) {
		this.container = container;
	}

	public ValenceBandCharge getValenceCharge(String position) {
		return valenceCharges.get(position);
	}
	
	public ConductionBandElectron getConductingE() {
		return conductingE;
	}

	//	protected ValenceBandCharge getExternalCharge(String position) {
//		return this.getAdjacentAtom(position).getValenceCharge(Orientation.getOpposite(position));
//	}
	protected Atom getAdjacentAtom(String position) {
		if(position.contentEquals("up")) {
			if(this.indexY>0) {
				return this.getContainer().getAtomAt(this.indexX, this.indexY-1);
			}
			else return null;
		}
		if(position.contentEquals("down")) {
			if(this.indexY<Settings.crystalHeight-1) {
				return this.getContainer().getAtomAt(this.indexX, this.indexY+1);
			}
			else return null;
		}
		if(position.contentEquals("right")) {
			return this.getContainer().getAtomAt((this.indexX+1)%Settings.crystalWidth, this.indexY);
		}
		if(this.getIndexX()<1) {
			return this.getContainer().getAtomAt((this.indexX+Settings.crystalWidth-1), this.indexY);
		}
		return this.getContainer().getAtomAt((this.indexX-1), this.indexY);
	
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
		
		Atom newContainer = this.getAdjacentAtom("right");
		ConductionBandElectron e = this.conductingE;
		newContainer.conductingE = e;
		this.conductingE = null;
		if(this.indexX<Settings.crystalWidth-1) {
		double x = newContainer.getView().getX()-this.getView().getX();
		double y = 0;
//		double x = newContainer.getConductingE().getView().getX();
//		double y = newContainer.getConductingE().getView().getY();
		Transition t = e.getView().moveTranslate(x, y);
		t.play();
		}
		else {//move out of frame
			double x = newContainer.getView().getX()+2*Settings.frameBias+Settings.padding/2;
			double y = newContainer.getView().getY()-Settings.padding/2;
			Transition t = e.getView().moveOutFrameAndBack(x, y);
			t.play();
		}
	}
	
	//behavior 2 & 3 invoker
	public void exchangeHoleWithElectron() {
		String holePosition = this.checkForHole();
		ValenceBandCharge holeHolder = this.getValenceCharge(holePosition);
		System.out.println("reference: "+holeHolder.getView().getX()+", "+holeHolder.getView().getY());
		String newHolePosition;
		Atom ExchangingAtom;
		boolean sweepFlag = false;
		
			if(holePosition.contentEquals("left")) {
				newHolePosition = "right";
				ExchangingAtom = this.getAdjacentAtom("left");
				
				ValenceBandCharge eHolder = ExchangingAtom.getValenceCharge(newHolePosition);
				this.valenceCharges.replace(holePosition, eHolder);
				System.out.println("new e:"+holePosition);
				System.out.println("this:"+this.checkForHole());
				ExchangingAtom.valenceCharges.replace(newHolePosition, holeHolder);
				System.out.println("new hole:"+newHolePosition);
				System.out.println("exchange atom:"+this.checkForHole());
				
				if(this.getIndexX()>0) {
					double x = holeHolder.getView().getX() - eHolder.getView().getX();
					double y = holeHolder.getView().getY() - eHolder.getView().getY();
					double i = eHolder.getView().getX();
					double j = eHolder.getView().getY();
					eHolder.getView().moveTranslate(x, y).play();
					holeHolder.getView().appear(i, j).play();
				}
				else {
					double x = holeHolder.getView().getX();
					double y = holeHolder.getView().getY();
					double i = eHolder.getView().getX();
					double j = eHolder.getView().getY();
					eHolder.getView().moveOutFrameAndBack(x, y).play();
					holeHolder.getView().appear(i, j).play();
				}
				return;
				
			}
			else {
				ExchangingAtom = this;
				if(holePosition.contentEquals("right")) {
					if(new Random().nextInt(2)==0) {
						newHolePosition = "up";
						sweepFlag = true;
					}
					else {
						newHolePosition = "down";
						sweepFlag = false;
					}
				}
				else {
					newHolePosition = "left";
					if(holePosition.contentEquals("up")) {
						sweepFlag = true;
					}
					else {
						sweepFlag = false;
					}
				}
			}
			ValenceBandCharge eHolder = ExchangingAtom.getValenceCharge(newHolePosition);
			this.valenceCharges.replace(holePosition, eHolder); 
			ExchangingAtom.valenceCharges.replace(newHolePosition, holeHolder);
			double x = holeHolder.getView().getX();
			double y = holeHolder.getView().getY();
			double i = eHolder.getView().getX();
			double j = eHolder.getView().getY();
			eHolder.getView().moveArc(x, y, sweepFlag).play();
			holeHolder.getView().appear(i, j).play();
	}
	
	//behavior 4 invoker
	public void diffuse() {
		String diffusePosition;
		int n = new Random().nextInt(4);
		diffusePosition = "left";
		if(n == 0) diffusePosition = "up";
		if(n == 1) diffusePosition = "down";
		if(n == 2) diffusePosition = "right";
		this.valenceCharges.replace(diffusePosition, new ValenceBandHole(this, diffusePosition));
		this.conductingE = new ConductionBandElectron(this);
		//TO-DO: call the animation
	}
	//behavior 5 invoker
	public void recombination() {
		String holePosition = this.checkForHole();
		this.conductingE = null;
		this.valenceCharges.replace(holePosition, new ValenceBandElectron(this, holePosition));
		//TO-DO: call the animation
	}
	}
