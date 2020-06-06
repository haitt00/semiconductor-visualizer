package elements.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ElementImage extends ImageView{

	//constructors
//	public ElementImage(Image elements.view) {
//		super(elements.view); 
//		
//	}

	public ElementImage(String fileName, double scaleRatio) {
		String strName = "./src/images/"+fileName;
		FileInputStream inputFile = null;
		try {
			inputFile = new FileInputStream(strName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Image img = new Image(inputFile);
		this.setImage(img);
		this.setFitHeight(img.getHeight()*scaleRatio);
		this.setFitWidth(img.getWidth()*scaleRatio);
	}
	public static ElementImage getSilicImage() {
		return new ElementImage("atom-Silic.png", 0.25);
	}
	public static ElementImage getAluminumImage() {
		return new ElementImage("atom-Aluminum.png", 0.25);
	}
	public static ElementImage getPhosphorusImage() {
		return new ElementImage("atom-Phosphorus.png", 0.25);
	}
	public static ElementImage getValenceEImage() {
		return new ElementImage("particle-valence-e.png", 0.2);
	}
	public static ElementImage getValenceHoleImage() {
		return new ElementImage("particle-hole.png", 0.2);
	}
	public static ElementImage getConductingEImage() {
		return new ElementImage("particle-conduct-e.png", 0.2);
	}
	public static ElementImage getExplosionImage() {
		return new ElementImage("particle-explosion.png", 0.2);
	}

}
