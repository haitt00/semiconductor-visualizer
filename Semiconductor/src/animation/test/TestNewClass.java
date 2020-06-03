package animation.test;

import animation.ElementImage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TestNewClass extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		ElementImage e = ElementImage.getValenceEImage();
		ElementImage o = ElementImage.getValenceHoleImage();
		ElementImage c = ElementImage.getConductingEImage();
		ElementImage Si = ElementImage.getSilicImage();
		ElementImage Al = ElementImage.getAluminumImage();
		ElementImage P = ElementImage.getPhosphorusImage();
		ElementImage comb = ElementImage.getExplosionImage();
		
		Pane root = new Pane();
		root.getChildren().addAll(c);
		Scene scene = new Scene(root, 1000, 1000);
//		c.appear(200, 200).play();
//		o.disappear().play();
		c.moveChaotic().play();
//		c.moveTranslate(250, 200).play();
//		c.spin().play();
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}

}
