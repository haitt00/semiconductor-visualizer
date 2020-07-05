package elements.crystal;
import elements.atom.Atom;
import elements.atom.SiliconAtom;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.layout.Pane;

public class Crystal {
	//environment attribute (static)
	public static final double maxvoltage = 5;
	private static DoubleProperty externalVoltage = new SimpleDoubleProperty();
	private static IntegerProperty electronCycle = new SimpleIntegerProperty();
	private static SimpleDoubleProperty seperateProb = new SimpleDoubleProperty();

	public static final double maxTemperature = 50;
	private static DoubleProperty temperature = new SimpleDoubleProperty();
	private static SimpleDoubleProperty diffuseProb = new SimpleDoubleProperty();
	private static SimpleDoubleProperty vibrationRange = new SimpleDoubleProperty();
	
	public final int crystalHeight = 5;
	public final int crystalWidth = 6;
	protected Atom atoms[][] = new Atom[this.crystalWidth][this.crystalHeight];
	
	public Crystal() {
		Crystal.externalVoltage.set(1);
		Crystal.temperature.set(25);
		for (int y = 0; y < this.crystalHeight; y++) {
			for (int x = 0; x < this.crystalWidth; x++) {
				atoms[x][y] = new SiliconAtom(x, y, this);
			}
		}
	}
	public static void bindWithController(DoubleProperty voltage, DoubleProperty temperature) {
		Crystal.externalVoltage.bindBidirectional(voltage);
		Crystal.electronCycle.bind((new SimpleIntegerProperty(1000)).divide(voltage));
		Crystal.seperateProb.bind(voltage.divide(Crystal.maxvoltage).multiply(0.5));
		
		Crystal.temperature.bindBidirectional(temperature);
		Crystal.diffuseProb.bind(temperature.divide(Crystal.maxTemperature).multiply(0.75));
		Crystal.vibrationRange.bind(temperature.multiply(0.3));
	}

	public static ReadOnlyIntegerProperty getElectronCycle() {
		return ReadOnlyIntegerProperty.readOnlyIntegerProperty(Crystal.electronCycle);
	}
	public static ReadOnlyDoubleProperty getSeperateProb() {
		return ReadOnlyDoubleProperty.readOnlyDoubleProperty(Crystal.seperateProb);
	}
	public static ReadOnlyDoubleProperty getDiffuseProb() {
		return ReadOnlyDoubleProperty.readOnlyDoubleProperty(Crystal.diffuseProb);
	}
	public static ReadOnlyDoubleProperty getVibrationRange() {
		return ReadOnlyDoubleProperty.readOnlyDoubleProperty(Crystal.vibrationRange);
	}
	public Atom getAtomAt(int x, int y) {
		return atoms[x][y];
	}
	public void progress(Pane root) {
		for (int y = 0; y < this.crystalHeight; y++) {
			for (int x = 0; x < this.crystalWidth; x++) {
				atoms[x][y].updateState();
			}
		}
		for (int y = 0; y < this.crystalHeight; y++) {
			for (int x = 0; x < this.crystalWidth; x++) {
				atoms[x][y].update(root);
			}
		}
	}
}
