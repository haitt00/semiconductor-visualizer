package elements.atom;

import elements.charge.ValenceBandElectron;
import elements.crystal.Crystal;
import elements.view.ElementImage;
import settings.Settings;

public class SiliconAtom extends Atom{

	public SiliconAtom(int indexX, int indexY, Crystal container) {
		super(indexX, indexY, container);
		this.view = ElementImage.getSilicImage();
		this.view.setX(this.getIndexX()*Settings.PanelWidth/Settings.crystalWidth+Settings.PanelWidth/Settings.crystalWidth/2);
		this.view.setY(this.getIndexY()*Settings.PanelHeight/Settings.crystalHeight+Settings.PanelHeight/Settings.crystalHeight/2);
		this.valenceCharges.put("up", new ValenceBandElectron(this, "up")); // maybe initilze using indexX & Y of atom here
		this.valenceCharges.put("down", new ValenceBandElectron(this, "down"));
		this.valenceCharges.put("right", new ValenceBandElectron(this, "right"));
		this.valenceCharges.put("left", new ValenceBandElectron(this, "left"));
		
	}
//	public String toString() {
//		return "SI:"+super.toString();
//	}
}
