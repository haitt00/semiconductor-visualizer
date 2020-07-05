package elements.atom;

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

public abstract class State {
	protected Atom owner;
	
	protected State(Atom owner) {
		this.owner = owner;
	}
	
	abstract void progress(Pane root);
	
	protected void passOnConductingE() {
		
		Atom newContainer = this.owner.getAdjacentAtom("right");
		ConductionBandElectron e = this.owner.getConductingE();
		newContainer.conductingE.add(e);
		this.owner.conductingE.remove(e);
		
		if(this.owner.getIndexX()<this.owner.getContainer().crystalWidth-1) {//if not leftmost
			double distanceX = newContainer.getView().getX()-this.owner.getView().getX();
			double distanceY = 0;
			SequentialTransition sqt = new SequentialTransition();
			sqt.getChildren().addAll(e.moveTranslate(distanceX, distanceY), e.moveChaotic());
			sqt.play();
		}
		else {////if leftmost, move out of frame
			double targetX = newContainer.getView().getX()+2*ElementImage.atomViewRadius+ElementImage.valenceViewPadding/2;
			double targetY = newContainer.getView().getY()-ElementImage.valenceViewPadding/2;
			SequentialTransition sqt = new SequentialTransition();
			sqt.getChildren().addAll(e.moveOutFrameAndBack(targetX, targetY), e.moveChaotic());
			sqt.play();
		}
	}
	
	protected void exchangeHoleWithElectron() {
		String holePosition = this.owner.checkForHole();
		ValenceBandHole holeHolder = (ValenceBandHole) this.owner.getValenceCharge(holePosition);
		String newHolePosition;
		Atom ExchangingAtom;
		boolean sweepFlag = false;
		
			//if hole in left position, exchange with another atom
			if(holePosition.contentEquals("left")) {
				newHolePosition = "right";
				ExchangingAtom = this.owner.getAdjacentAtom("left");
				
				// fixbug at line 60
				ValenceBandCharge eHolder_prev = ExchangingAtom.getValenceCharge(newHolePosition);
				ValenceBandElectron eHolder = null;
				if(eHolder_prev instanceof ValenceBandElectron) {
					eHolder = (ValenceBandElectron)eHolder_prev;
				} else if (eHolder_prev instanceof ValenceBandHole) {
					return;
				}
				
				// ValenceBandElectron eHolder = (ValenceBandElectron) ExchangingAtom.getValenceCharge(newHolePosition);
				this.owner.valenceCharges.replace(holePosition, eHolder);
				ExchangingAtom.valenceCharges.replace(newHolePosition, holeHolder);
			 
				if(this.owner.getIndexX()>0) {//if hole is not rightmost, exchange with e to the right 
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
				ExchangingAtom = this.owner;
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
			
			// fix bug at line 105
			
			ValenceBandCharge eHolder_prev = ExchangingAtom.getValenceCharge(newHolePosition);
			ValenceBandElectron eHolder = null;
			if(eHolder_prev instanceof ValenceBandElectron) {
				eHolder = (ValenceBandElectron)eHolder_prev;
			} else if (eHolder_prev instanceof ValenceBandHole) {
				return;
			}

			// ValenceBandElectron eHolder = (ValenceBandElectron) ExchangingAtom.getValenceCharge(newHolePosition);
			this.owner.valenceCharges.replace(holePosition, eHolder); 
			ExchangingAtom.valenceCharges.replace(newHolePosition, holeHolder);
			
			//elements.view
			double eTargetX = holeHolder.getView().getX();
			double eTargetY = holeHolder.getView().getY();
			double holeTargetX = eHolder.getView().getX();
			double holeTargetY = eHolder.getView().getY();
			eHolder.moveArc(eTargetX, eTargetY, sweepFlag).play();
			holeHolder.appear(holeTargetX, holeTargetY).play();
	}
	
	protected void diffuse(Pane root) {
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
		
		// fix bug at line 145
		
/*		ValenceBandCharge valenceE_prev = this.owner.valenceCharges.get(diffusePosition);
		ValenceBandElectron valenceE = null;
		if(valenceE_prev instanceof ValenceBandElectron) {
			valenceE = (ValenceBandElectron)valenceE_prev;
		} else {
			return;
		} */
		
		ValenceBandElectron valenceE = (ValenceBandElectron) this.owner.valenceCharges.get(diffusePosition);
		ElementImage explosion = ElementImage.getExplosionImage();
		root.getChildren().add(explosion);
		ConductionBandElectron newConductingE = new ConductionBandElectron(this.owner);
		ValenceBandHole newHole = new ValenceBandHole(this.owner, diffusePosition);
		
		double valenceECoordinateX = valenceE.getView().getX();
		double valenceECoordinateY = valenceE.getView().getY();
		double conductingECoordinateX = newConductingE.getView().getX();
		double conductingECoordinateY = newConductingE.getView().getY();
		
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
			this.owner.valenceCharges.replace(diffusePosition, newHole);
			this.owner.conductingE.add(newConductingE);
		});
		pt.play();
	}
	
	protected void recombine(Pane root) {
		String holePosition = this.owner.checkForHole();
		ValenceBandHole hole = (ValenceBandHole) this.owner.valenceCharges.get(holePosition);
		ConductionBandElectron conductingE = this.owner.getConductingE();
		ValenceBandElectron newValenceE = new ValenceBandElectron(this.owner, this.owner.checkForHole());
		double conductingECoordinateX = conductingE.getView().getX();
		double conductingECoordinateY = conductingE.getView().getY();
		double holeCoordinateX = hole.getView().getX();
		double holeCoordinateY = hole.getView().getY();
		ParallelTransition pt = new ParallelTransition(conductingE.moveTranslate(holeCoordinateX-conductingECoordinateX, holeCoordinateY-conductingECoordinateY), conductingE.spin());
		pt.setOnFinished(evt->{
			this.owner.conductingE.remove(conductingE);
			this.owner.valenceCharges.replace(holePosition, newValenceE);
			root.getChildren().add(newValenceE.getView());
			root.getChildren().removeAll(hole.getView(), conductingE.getView());
		});
		pt.play();
	}
}
