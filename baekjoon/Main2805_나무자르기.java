import java.util.ArrayList;
import java.util.Scanner;

public class Main2805_나무자르기 {
	public static ArrayList<Integer> list = new ArrayList<>();
	public static long n, m, ans;
	
	public static void cut(long min, long max) {
		while(true) {
			long mid = (min + max)/2;
			long sum = 0;
			
			for (int i = 0; i < n ; i++) {
				int tree = list.get(i);
				if(tree>mid) sum += tree-mid;
			}
			if(mid==min || sum==m) { //정답을 찾았거나 끝까지 검사했을 때
				ans = mid;
				return;
			} else if(sum < m) {
				max = mid;
			} else min = mid;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		long max = 0;
		ans = 0;
		
		for (int i = 0; i < n; i++) {
			int tree = sc.nextInt();
			list.add(tree);
			if(tree > max) max = tree;
		}
		
		cut(0, max);
		
		System.out.println(ans);
	}
}
