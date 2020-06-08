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
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

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
		
		if(this.indexX<this.container.crystalWidth-1) {//if not leftmost
			double distanceX = newContainer.getView().getX()-this.getView().getX();
			double distanceY = 0;
	//		double x = newContainer.getConductingE().getView().getX();
	//		double y = newContainer.getConductingE().getView().getY();
	//		System.out.println("null e check: "+e);
	//		Transition t = e.getView().moveTranslate(x, y);
			SequentialTransition sqt = new SequentialTransition();
			sqt.getChildren().addAll(e.moveTranslate(distanceX, distanceY), e.moveChaotic());
			sqt.play();
		//	t.play();
		}
		else {////if leftmost, move out of frame
			double targetX = newContainer.getView().getX()+2*ElementImage.atomViewRadius+ElementImage.valenceViewPadding/2;
			double targetY = newContainer.getView().getY()-ElementImage.valenceViewPadding/2;
	//		Transition t = e.getView().moveOutFrameAndBack(x, y);
			SequentialTransition sqt = new SequentialTransition();
			sqt.getChildren().addAll(e.moveOutFrameAndBack(targetX, targetY), e.moveChaotic());
			sqt.play();
//			e.getView().moveOutFrameAndBack(x, y).play();
//			e.getView().moveChaotic().play();
//			t.play();
		}
	}
	
	//behavior 2 & 3 invoker
	public void exchangeHoleWithElectron() {
		String holePosition = this.checkForHole();
		ValenceBandHole holeHolder = (ValenceBandHole) this.getValenceCharge(holePosition);
//		System.out.println("reference: "+holeHolder.getView().getX()+", "+holeHolder.getView().getY());
		String newHolePosition;
		Atom ExchangingAtom;
		boolean sweepFlag = false;
		
			//if hole in left position, exchange with another atom
			if(holePosition.contentEquals("left")) {
				newHolePosition = "right";
				ExchangingAtom = this.getAdjacentAtom("left");
				
				ValenceBandElectron eHolder = (ValenceBandElectron) ExchangingAtom.getValenceCharge(newHolePosition);
				this.valenceCharges.replace(holePosition, eHolder);
//				System.out.println("new e:"+holePosition);
//				System.out.println("this:"+this.checkForHole());
				ExchangingAtom.valenceCharges.replace(newHolePosition, holeHolder);
//				System.out.println("new hole:"+newHolePosition);
//				System.out.println("exchange atom:"+this.checkForHole());
			 
				if(this.getIndexX()>0) {//if hole is not rightmost, exchange with e to the right 
					double x = holeHolder.getView().getX() - eHolder.getView().getX();
					double y = holeHolder.getView().getY() - eHolder.getView().getY();
					double i = eHolder.getView().getX();
					double j = eHolder.getView().getY();
					eHolder.moveTranslate(x, y).play();
					holeHolder.appear(i, j).play();
				}
				else {//if hole is rightmost, exchange with e to the leftmost by moving out frame 
					double x = holeHolder.getView().getX();
					double y = holeHolder.getView().getY();
					double i = eHolder.getView().getX();
					double j = eHolder.getView().getY();
					eHolder.moveOutFrameAndBack(x, y).play();
					holeHolder.appear(i, j).play();
				}
				return;
				
			}
			else {//if hole not in left position, exchange with self
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
			ValenceBandElectron eHolder = (ValenceBandElectron) ExchangingAtom.getValenceCharge(newHolePosition);
			this.valenceCharges.replace(holePosition, eHolder); 
			ExchangingAtom.valenceCharges.replace(newHolePosition, holeHolder);
			
			//elements.view
			double eTargetX = holeHolder.getView().getX();
			double eTargetY = holeHolder.getView().getY();
			double holeTargetX = eHolder.getView().getX();
			double holeTargetY = eHolder.getView().getY();
			eHolder.moveArc(eTargetX, eTargetY, sweepFlag).play();
			holeHolder.appear(holeTargetX, holeTargetY).play();
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
		
		double valenceECoordinateX = valenceE.getView().getX();
		double valenceECoordinateY = valenceE.getView().getY();
		double conductingECoordinateX = newConductingE.getView().getX();
		double conductingECoordinateY = newConductingE.getView().getY();
		
//		System.out.println("moveby: "+(i-x)+", "+(j-y));
		explosion.setX(valenceECoordinateX);
		explosion.setY(valenceECoordinateY);
		FadeTransition explosionFadeIn = new FadeTransition(Duration.millis(Crystal.getElectronCycle().doubleValue()), explosion);
		explosionFadeIn.setFromValue(0);
		explosionFadeIn.setToValue(1);
		explosionFadeIn.setOnFinished(evt->{
			explosion.setTranslateX(0);
			explosion.setTranslateY(0);
		});
		ParallelTransition pt = new ParallelTransition(explosionFadeIn, valenceE.moveTranslate((conductingECoordinateX-valenceECoordinateX),(conductingECoordinateY-valenceECoordinateY)));
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
		double conductingECoordinateX = conductingE.getView().getX();
		double conductingECoordinateY = conductingE.getView().getY();
		double holeCoordinateX = hole.getView().getX();
		double holeCoordinateY = hole.getView().getY();
		ParallelTransition pt = new ParallelTransition(conductingE.moveTranslate(holeCoordinateX-conductingECoordinateX, holeCoordinateY-conductingECoordinateY), conductingE.spin());
		pt.setOnFinished(evt->{
			this.conductingE = null;
			this.valenceCharges.replace(holePosition, newValenceE);
			root.getChildren().add(newValenceE.getView());
			root.getChildren().removeAll(hole.getView(), conductingE.getView());
		});
		pt.play();
	}
	}
