package elements.atom;

import elements.crystal.Crystal;
import javafx.scene.layout.Pane;

public class WithConductingEAndWithHoleState extends State{

	protected WithConductingEAndWithHoleState(Atom owner) {
		super(owner);
	}

	@Override
	void progress(Pane root) {
		Double separateChance = Math.random();
		if(separateChance<Crystal.getSeperateProb().get()) {
			//e and o will seperate in opposite directions
			this.owner.atomState.passOnConductingE();
			this.owner.atomState.exchangeHoleWithElectron();
		}
		else {
			//e and o will recombine
			this.owner.atomState.recombine(root);
		}
	}

}
