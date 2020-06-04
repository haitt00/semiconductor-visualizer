package settings;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Settings {

	public Settings() {
	}
	public static int crystalHeight = 5;
	public static int crystalWidth = 6;
	public static int PanelHeight = 500;
	public static int PanelWidth = 600;
	public static int frameBias = 26;
	public static int padding = 16;
	
	public static double maxvoltage = 5;
	public static DoubleProperty voltage = new SimpleDoubleProperty();
	public static IntegerProperty transitionLength = new SimpleIntegerProperty();
	public static SimpleDoubleProperty seperateProb = new SimpleDoubleProperty();

	public static double maxTemperature = 50;
	public static DoubleProperty temperature = new SimpleDoubleProperty();
	public static double chaoticRate = 25;
	public static SimpleDoubleProperty diffuseProb = new SimpleDoubleProperty();
	
	
	
	
}


