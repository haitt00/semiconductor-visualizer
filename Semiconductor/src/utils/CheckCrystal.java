package utils;

import java.util.ArrayList;

import elements.crystal.Crystal;
import elements.crystal.NDopedCrystal;
import elements.crystal.PDopedCrystal;

public class CheckCrystal {

	public static Crystal get(String choice, String dope) {
		ArrayList<Crystal> crystalTypeList = new ArrayList<Crystal>();
		crystalTypeList.add(new PDopedCrystal(dope));
		crystalTypeList.add(new NDopedCrystal(dope));
		Crystal temp = new Crystal();
		for(int i = 0; i<crystalTypeList.size(); i++) {
			Crystal obj = crystalTypeList.get(i);
			System.out.println(obj.getClass().toString());
			if(obj.getClass().toString().contains(choice))
			temp = obj;
		}
		return temp;
	}
}


