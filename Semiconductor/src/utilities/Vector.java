package utilities;

public class Vector {

	public Vector() {
	}
	
	public Vector(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	private int x;
	private int y;
	public static double calculateAngle(Vector baseCoordinate, Vector extendCoordiante){
		return Math.abs(Math.atan2(extendCoordiante.y - baseCoordinate.y, extendCoordiante.x - baseCoordinate.x));
	}

}
