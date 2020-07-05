package elements.atom;

import javafx.scene.layout.Pane;

public class WithConductingEAndNoHoleState extends State{

	protected WithConductingEAndNoHoleState(Atom owner) {
		super(owner);
	}

	@Override
	void progress(Pane root) {
		this.owner.atomState.passOnConductingE();
	}

}
