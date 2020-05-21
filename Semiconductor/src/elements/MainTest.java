package elements;

public class MainTest {

	public static void main(String[] args) {
//		//test type pure (all Si)
//		Crystal crystalPure = new Crystal();
//		crystalPure.initCrystal("SI");
//		crystalPure.displayCrystal();
//		crystalPure.displayHolePosition();
//		//test behavior 4
//		crystalPure.getAtomAt(2, 2).diffuse();
//		crystalPure.displayCrystal();
//		crystalPure.displayHolePosition();
//		//test behavior 5
//		crystalPure.getAtomAt(2, 2).recombination();
//		crystalPure.displayCrystal();
//		crystalPure.displayHolePosition();
		
		//test type P (1 Al, the rest Si)
		System.out.println();
		Crystal crystalAl = new Crystal();
		System.out.println("t = 0:");
		crystalAl.initCrystal("AL");
		crystalAl.displayCrystal();
		crystalAl.displayHolePosition();
		//test behavior 2 & 3
		for (int i = 1; i <= 10; i++) {
			System.out.println("t = "+i+":");
			crystalAl.progress();
			crystalAl.displayCrystal();
			crystalAl.displayHolePosition();
		}
		System.out.println();
		
		
		//test type N (1 P, the rest Si)
//		System.out.println();
//		Crystal crystalP = new Crystal();
//		System.out.println("t = 0:");
//		crystalP.initCrystal("P");
//		crystalP.displayCrystal();
//		//test behavior 1
//		for (int i = 1; i <= 10; i++) {
//			System.out.println("t = "+i+":");
//			crystalP.progress();
//			crystalP.displayCrystal();
//		}
		
	}

}
