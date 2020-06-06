package environment;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Environment {
	
	public static double maxvoltage = 5;
	public static DoubleProperty externalVoltage = new SimpleDoubleProperty();
	public static IntegerProperty electronCycle = new SimpleIntegerProperty();
	public static SimpleDoubleProperty seperateProb = new SimpleDoubleProperty();

	public static double maxTemperature = 50;
	public static DoubleProperty temperature = new SimpleDoubleProperty();
	public static SimpleDoubleProperty diffuseProb = new SimpleDoubleProperty();
	
	
	
	
}


