package utils;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Randomness {
	public static ArrayList<Integer> getNonConsecutiveCombination(int n, int k){
		ArrayList<Integer> answer = new ArrayList<Integer>();
		ArrayList<Integer> base = Randomness.getCombination(n-k+1, k);
		base.sort(null);
		for (int i = 0; i < k; i++) {
			answer.add(base.get(i)+i);
		}
		return answer;
	}
	public static ArrayList<Integer> getCombination(int n, int k) {
		return (ArrayList<Integer>) ThreadLocalRandom.current().ints(0, n).distinct().limit(k).boxed().collect(Collectors.toList());
	}
}
