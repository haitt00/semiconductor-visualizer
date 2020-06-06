package elements.atom;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import elements.charge.ConductionBandElectron;
import elements.charge.ValenceBandCharge;
import elements.charge.ValenceBandElectron;
import elements.charge.ValenceBandHole;
import elements.crystal.Crystal;
import elements.view.ElementImage;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.layout.Pane;
import settings.Settings;

public class Atom {

	private int indexX;
	private int indexY;
	private Crystal container;
	protected ElementImage view;
	protected HashMap<String, ValenceBandCharge> valenceCharges = new HashMap<String, ValenceBandCharge>();
	protected ConductionBandElectron conductingE;
//	public Atom() {
//		
//	}
	
	public Atom(int indexX, int indexY, Crystal container) {
		super();
		this.indexX = indexX;
		this.indexY = indexY;
		this.container = container;
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
		return conductingE;
	}

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

//	public String toString() {
//		String str = this.valenceCharges.get("up").toString()+this.valenceCharges.get("down").toString()+this.valenceCharges.get("right").toString()+this.valenceCharges.get("left").toString();
//		if(this.conductingE!=null) {
//			str+="*";
//		}
//		return str;
//	}
	
	//behavior 1 invoker
	public void passOnConductingE() {
		
		Atom newContainer = this.getAdjacentAtom("right");
		ConductionBandElectron e = this.conductingE;
//		System.out.println("null e check0: "+e);
		newContainer.conductingE = e;
//		System.out.println("null e check1: "+e);
		this.conductingE = null;
//		System.out.println("null e check2: "+e);
		
		//elements.view
		if(this.indexX<Settings.crystalWidth-1) {
		double x = newContainer.getView().getX()-this.getView().getX();
		double y = 0;
//		double x = newContainer.getConductingE().getView().getX();
//		double y = newContainer.getConductingE().getView().getY();
//		System.out.println("null e check: "+e);
//		Transition t = e.getView().moveTranslate(x, y);
		SequentialTransition sqt = new SequentialTransition();
		sqt.getChildren().addAll(e.getView().moveTranslate(x, y), e.getView().moveChaotic());
		sqt.play();
	//	t.play();
		}
		else {//move out of frame
			double x = newContainer.getView().getX()+2*Settings.frameBias+Settings.padding/2;
			double y = newContainer.getView().getY()-Settings.padding/2;
	//		Transition t = e.getView().moveOutFrameAndBack(x, y);
			SequentialTransition sqt = new SequentialTransition();
			sqt.getChildren().addAll(e.getView().moveOutFrameAndBack(x, y), e.getView().moveChaotic());
			sqt.play();
//			e.getView().moveOutFrameAndBack(x, y).play();
//			e.getView().moveChaotic().play();
//			t.play();
		}
	}
	
	//behavior 2 & 3 invoker
	public void exchangeHoleWithElectron() {
		String holePosition = this.checkForHole();
		ValenceBandCharge holeHolder = this.getValenceCharge(holePosition);
//		System.out.println("reference: "+holeHolder.getView().getX()+", "+holeHolder.getView().getY());
		String newHolePosition;
		Atom ExchangingAtom;
		boolean sweepFlag = false;
		
			if(holePosition.contentEquals("left")) {
				newHolePosition = "right";
				ExchangingAtom = this.getAdjacentAtom("left");
				
				ValenceBandCharge eHolder = ExchangingAtom.getValenceCharge(newHolePosition);
				this.valenceCharges.replace(holePosition, eHolder);
//				System.out.println("new e:"+holePosition);
//				System.out.println("this:"+this.checkForHole());
				ExchangingAtom.valenceCharges.replace(newHolePosition, holeHolder);
//				System.out.println("new hole:"+newHolePosition);
//				System.out.println("exchange atom:"+this.checkForHole());
			 
				//elements.view
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
			
			//elements.view
			double x = holeHolder.getView().getX();
			double y = holeHolder.getView().getY();
			double i = eHolder.getView().getX();
			double j = eHolder.getView().getY();
			eHolder.getView().moveArc(x, y, sweepFlag).play();
			holeHolder.getView().appear(i, j).play();
	}
	
	//behavior 4 invoker
	public void diffuse(Pane root) {
		String diffusePosition;
		int n = new Random().nextInt(4);
		
		switch (n) {
		case 1:
			diffusePosition = "up";
			break;
		case 2:
			diffusePosition = "down";
			break;
		case 3:
			diffusePosition = "right";
			break;
		default:
			diffusePosition = "left";
			break;
		}
		ValenceBandElectron valenceE = (ValenceBandElectron) this.valenceCharges.get(diffusePosition);
		ElementImage explosion = ElementImage.getExplosionImage();
		root.getChildren().add(explosion);
		ConductionBandElectron newConductingE = new ConductionBandElectron(this);
		ValenceBandHole newHole = new ValenceBandHole(this, diffusePosition);
		
		double x = valenceE.getView().getX();
		double y = valenceE.getView().getY();
		double i = newConductingE.getView().getX();
		double j = newConductingE.getView().getY();
		
//		System.out.println("moveby: "+(i-x)+", "+(j-y));
		ParallelTransition pt = new ParallelTransition(explosion.appear(x, y), valenceE.getView().moveTranslate((i-x),(j-y)));
		pt.setOnFinished(evt->{
			root.getChildren().removeAll(explosion, valenceE.getView());
			root.getChildren().addAll(newConductingE.getView(), newHole.getView());
			this.valenceCharges.replace(diffusePosition, newHole);
			this.conductingE = newConductingE;
		});
		pt.play();
	}
	//behavior 5 invoker
	public void recombination(Pane root) {
		String holePosition = this.checkForHole();
		ValenceBandHole hole = (ValenceBandHole) this.valenceCharges.get(holePosition);
		ConductionBandElectron conductingE = this.conductingE;
		ValenceBandElectron newValenceE = new ValenceBandElectron(this, this.checkForHole());
		double x = conductingE.getView().getX();
		double y = conductingE.getView().getY();
		double i = hole.getView().getX();
		double j = hole.getView().getY();
		ParallelTransition pt = new ParallelTransition(conductingE.getView().moveTranslate(i-x, j-y), conductingE.getView().spin());
		pt.setOnFinished(evt->{
			this.conductingE = null;
			this.valenceCharges.replace(holePosition, newValenceE);
			root.getChildren().add(newValenceE.getView());
			root.getChildren().removeAll(hole.getView(), conductingE.getView());
		});
		pt.play();
	}
	}
