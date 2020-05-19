package elements;

public class Main {

	public static void main(String[] args) {
		//test type pure
//		Crystal crystalPure = new Crystal();
//		crystalPure.initCrystal("SI");
//		crystalPure.displayCrystal();
//		crystalPure.displayHolePosition();
//		
		
		//test type P
		System.out.println();
		Crystal crystalAl = new Crystal();
		System.out.println("t = 0:");
		crystalAl.initCrystal("AL");
		crystalAl.displayCrystal();
		crystalAl.displayHolePosition();
		for (int i = 1; i <= 5; i++) {
			System.out.println("t = "+i+":");
			crystalAl.progress();
			crystalAl.displayCrystal();
			crystalAl.displayHolePosition();
		}
		System.out.println();
		
		
		//test type N
//		System.out.println();
//		Crystal crystalP = new Crystal();
//		System.out.println("t = 0:");
//		crystalP.initCrystal("P");
//		crystalP.displayCrystal();
//		for (int i = 1; i <= 10; i++) {
//			System.out.println("t = "+i+":");
//			crystalP.progress();
//			crystalP.displayCrystal();
//		}
		
	}

}
