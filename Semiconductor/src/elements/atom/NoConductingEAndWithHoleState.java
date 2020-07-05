package elements.atom;

import javafx.scene.layout.Pane;

public class NoConductingEAndWithHoleState extends State{

	protected NoConductingEAndWithHoleState(Atom owner) {
		super(owner);
	}

	@Override
	void progress(Pane root) {
		this.owner.atomState.exchangeHoleWithElectron();	
	}

}
