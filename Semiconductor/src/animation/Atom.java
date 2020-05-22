package animation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Atom {
	ImageView centralAtom;
	ArrayList<Particle> particlesList = new ArrayList<Particle>();
	public static String SILIC = "Silic";
	public static String PHOTPHORUS = "Photphorus";
	public static String INDI = "Indi"; // will change to Aluminum later
	

	public ImageView getCentralAtom() {
		return centralAtom;
	}

	public void setCentralAtom(ImageView centralAtom) {
		this.centralAtom = centralAtom;
	}

	public ArrayList<Particle> getParticles() {
		return particlesList;
	}

	public void setParticles(ArrayList<Particle> particlesList) {
		this.particlesList = particlesList;
	}

	public Atom(String type) {
		String strAtom = "images/atom-" + type + ".png";
		FileInputStream inputAtom = null;
		try {
			inputAtom = new FileInputStream(strAtom);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Image imgAtom = new Image(inputAtom);
		ImageView imgViewAtom = new ImageView(imgAtom);
		this.centralAtom = imgViewAtom;
		
		if(type.equalsIgnoreCase("Silic")==true) {
			for(int i = 0; i < 4; i++) {
				particlesList.add(new ValenceElectron());
			}
		}
		
		if(type.equalsIgnoreCase("Photphorus")==true) {
			for(int i = 0; i < 3; i++) {
				particlesList.add(new ValenceElectron());
			}
			particlesList.add(new ValenceHole());
		}
		
		if(type.equalsIgnoreCase("Indi")==true) {
			for(int i = 0; i < 4; i++) {
				particlesList.add(new ValenceElectron());
			}
			particlesList.add(new ConductElectron());
		}
		
	}

}
