package elements.atom;

import elements.crystal.Crystal;
import javafx.scene.layout.Pane;

public class NoConductingEAndNoHoleState extends State{
	protected NoConductingEAndNoHoleState(Atom owner) {
		super(owner);
	}

	private double getRescaledDiffuseProb() {
		double rescaledDiffuseProb[] = new double[this.owner.getContainer().crystalWidth+1];
		for (int i = 1; i < rescaledDiffuseProb.length; i++) {
			double accumulatedNoDiffuseProb = 1;
			for (int j = 1; j < i; j++) {
				accumulatedNoDiffuseProb*= 1 - rescaledDiffuseProb[j];
			}
			rescaledDiffuseProb[i] = Crystal.getDiffuseProb().doubleValue()/this.owner.getContainer().crystalWidth/accumulatedNoDiffuseProb;
		}

		return rescaledDiffuseProb[this.owner.getIndexX()];
	}

	@Override
	void progress(Pane root) {
		boolean foundConductingEOrHole = false;
		for (int x = 0; x < this.owner.getContainer().crystalWidth; x++) {
			if(x!=this.owner.getIndexX()) {
				Atom investigatedAtom = this.owner.getContainer().getAtomAt(x, this.owner.getIndexY());
				if(investigatedAtom.checkForConductingE()||!(investigatedAtom.checkForHole().contentEquals("none"))) {
					foundConductingEOrHole = true;
					break;
				}
			}
		}
		if (!foundConductingEOrHole) {
			Double diffuseChance = Math.random();
			if(diffuseChance<this.getRescaledDiffuseProb()) {
				this.owner.atomState.diffuse(root);
			}
		}
		
	}
	
}
