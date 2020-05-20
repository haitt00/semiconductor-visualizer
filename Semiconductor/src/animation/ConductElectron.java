package animation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ConductElectron extends Particle {
	
	public ConductElectron() {
		String strElectron = "./src/images/particle-conduct-e.png"; //need to find new images for conduct electron
		FileInputStream inputElectron = null;
		try {
			inputElectron = new FileInputStream(strElectron);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		Image imgElectron = new Image(inputElectron);
		ImageView imgViewElectron = new ImageView(imgElectron);
		this.particle = imgViewElectron;
	}

}
