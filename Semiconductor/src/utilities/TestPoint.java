package utilities;

public class TestPoint {

	public TestPoint() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Vector base = new Vector(0, 0);
		System.out.println("(0, 0):"+Vector.calculateAngle(base, new Vector(0,0)));
		
		System.out.println("(1, 0):"+Vector.calculateAngle(base, new Vector(1,0)));
		System.out.println("(1, 1):"+Vector.calculateAngle(base, new Vector(1,1)));
		System.out.println("(0, 1):"+Vector.calculateAngle(base, new Vector(0,1)));
		System.out.println("(0, 1):"+Vector.calculateAngle(base, new Vector(-1,1)));
		System.out.println("(0, 1):"+Vector.calculateAngle(base, new Vector(-1,0)));
		System.out.println("(0, 1):"+Vector.calculateAngle(base, new Vector(-1,-1)));
		System.out.println("(0, 1):"+Vector.calculateAngle(base, new Vector(0,-1)));
		System.out.println("(0, 1):"+Vector.calculateAngle(base, new Vector(1,-1)));
		

	}

}
