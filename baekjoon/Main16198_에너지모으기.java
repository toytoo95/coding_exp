import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static ArrayList<Integer> biz = new ArrayList<>();
	public static int max;
	public static int energy;
	
	public static void getEnergy() {
		if(biz.size() == 2) {
			max = Math.max(energy, max);
			return;
		}

		for (int i = 1; i < biz.size()-1; i++) {
			int n = biz.get(i);
			energy += biz.get(i-1)*biz.get(i+1);
			biz.remove(i);
			getEnergy();
			biz.add(i, n);
			energy -= biz.get(i-1)*biz.get(i+1);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] original = new int[n];
		max = 0;
		for (int i = 0; i < n; i++) {
			original[i] = sc.nextInt();
			biz.add(original[i]);
		}
		getEnergy();
		System.out.println(max);
	}
}
