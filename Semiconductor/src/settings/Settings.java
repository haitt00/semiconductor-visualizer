package settings;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Settings {

	public Settings() {
		// TODO Auto-generated constructor stub
	}
	public static int crystalHeight = 5;
	public static int crystalWidth = 6;
	public static int PanelHeight = 500;
	public static int PanelWidth = 600;
	public static int frameBias = 26;
	public static int padding = 16;
	public static DoubleProperty voltage = new SimpleDoubleProperty(1);
	public static DoubleProperty temperature = new SimpleDoubleProperty(25);
	// doi tu int sang double
	public static IntegerProperty transitionLength = new SimpleIntegerProperty((int)(1000/voltage.get()));
	public static double chaoticRate = 25;
}


