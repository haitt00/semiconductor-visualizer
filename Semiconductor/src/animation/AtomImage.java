package animation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AtomImage {
	ImageView centralAtom;
	ArrayList<ParticleImage> particlesList = new ArrayList<ParticleImage>();
	public static String SILIC = "Silic";
	public static String PHOTPHORUS = "Photphorus";
	public static String ALUMINUM = "Aluminum"; // will change to Aluminum later
	

	public ImageView getCentralAtom() {
		return centralAtom;
	}

	public void setCentralAtom(ImageView centralAtom) {
		this.centralAtom = centralAtom;
	}

	public ArrayList<ParticleImage> getParticles() {
		return particlesList;
	}

	public void setParticles(ArrayList<ParticleImage> particlesList) {
		this.particlesList = particlesList;
	}

	public AtomImage(String type) {
		String strAtom = "./src/images/atom-" + type + ".png";
		FileInputStream inputAtom = null;
		try {
			inputAtom = new FileInputStream(strAtom);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Image imgAtom = new Image(inputAtom);
		ImageView imgViewAtom = new ImageView(imgAtom);
//		imgViewAtom.setScaleX(0.25);
//		imgViewAtom.setScaleY(0.25);
//		imgViewAtom.scaleXProperty();
//		imgViewAtom.scaleYProperty();
		imgViewAtom.setFitHeight(imgViewAtom.getImage().getHeight()*0.25);
		imgViewAtom.setFitWidth(imgViewAtom.getImage().getWidth()*0.25);
		this.centralAtom = imgViewAtom;
		
		if(type.equalsIgnoreCase("Silic")==true) {
			for(int i = 0; i < 4; i++) {
				particlesList.add(new ValenceElectronImage());
			}
		}
		
		if(type.equalsIgnoreCase("Photphorus")==true) {
			for(int i = 0; i < 3; i++) {
				particlesList.add(new ValenceElectronImage());
			}
			particlesList.add(new ValenceHoleImage());
		}
		
		if(type.equalsIgnoreCase("Aluminum")==true) {
			for(int i = 0; i < 4; i++) {
				particlesList.add(new ValenceElectronImage());
			}
			particlesList.add(new ConductElectronImage());
		}
		
	}

}
