package utilities;

public class Orientation {

	public Orientation() {
		// TODO Auto-generated constructor stub
	}
//	public static final String up = "up";
//	public static final String down = "down";
//	public static final String right = "right";
//	public static final String left = "left";
	
	public static String getOpposite(String orientation) {
		if(orientation.contentEquals("up")) return "down";
		if(orientation.contentEquals("down")) return "up";
		if(orientation.contentEquals("right")) return "left";
		return "right";
	}

}
